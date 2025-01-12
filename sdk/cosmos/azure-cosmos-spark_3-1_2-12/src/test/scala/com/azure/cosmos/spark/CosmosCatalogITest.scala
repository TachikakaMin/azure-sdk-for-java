// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
package com.azure.cosmos.spark

import com.azure.cosmos.implementation.TestConfigurations
import org.apache.commons.lang3.RandomStringUtils
import org.apache.spark.sql.SparkSession
// scalastyle:off underscore.import
import scala.collection.JavaConverters._
// scalastyle:on underscore.import

class CosmosCatalogITest extends IntegrationSpec with CosmosClient {
  //scalastyle:off multiple.string.literals
  //scalastyle:off magic.number

  var spark : SparkSession = _

  override def beforeAll(): Unit = {
    super.beforeAll()
    val cosmosEndpoint = TestConfigurations.HOST
    val cosmosMasterKey = TestConfigurations.MASTER_KEY

    spark = SparkSession.builder()
      .appName("spark connector sample")
      .master("local")
      .enableHiveSupport()
      .getOrCreate()

    spark.conf.set(s"spark.sql.catalog.testCatalog", "com.azure.cosmos.spark.CosmosCatalog")
    spark.conf.set(s"spark.sql.catalog.testCatalog.spark.cosmos.accountEndpoint", cosmosEndpoint)
    spark.conf.set(s"spark.sql.catalog.testCatalog.spark.cosmos.accountKey", cosmosMasterKey)
  }

  override def afterAll(): Unit = {
    try spark.close()
    finally super.afterAll()
  }

  "Cosmos Catalog" can "create a database with shared throughput" in {
    val databaseName = getAutoCleanableDatabaseName()

    spark.sql(s"CREATE DATABASE testCatalog.$databaseName WITH DBPROPERTIES ('manualThroughput' = '1000');")

    cosmosClient.getDatabase(databaseName).read().block()
    val throughput = cosmosClient.getDatabase(databaseName).readThroughput().block()

    throughput.getProperties.getManualThroughput shouldEqual 1000
  }

  // TODO: moderakh spark on windows has issue with this test.
  // java.lang.RuntimeException: java.io.IOException: (null) entry in command string: null chmod 0733 D:\tmp\hive;
  // once we move Linux CI re-enable the test:
  it can "drops a database" in {
    assume(!Platform.isWindows())

    val databaseName = getAutoCleanableDatabaseName()
    spark.catalog.databaseExists(databaseName) shouldEqual false

    createDatabase(spark, databaseName)
    databaseExists(databaseName) shouldEqual true

    dropDatabase(spark, databaseName)
    spark.catalog.databaseExists(databaseName) shouldEqual false
  }

  it can "create a table with defaults" in {
    val databaseName = getAutoCleanableDatabaseName()
    val containerName = RandomStringUtils.randomAlphabetic(6).toLowerCase + System.currentTimeMillis()
    cleanupDatabaseLater(databaseName)

    spark.sql(s"CREATE DATABASE testCatalog.$databaseName;")
    spark.sql(s"CREATE TABLE testCatalog.$databaseName.$containerName (word STRING, number INT) using cosmos.items;")

    val containerProperties = cosmosClient.getDatabase(databaseName).getContainer(containerName).read().block().getProperties

    // verify default partition key path is used
    containerProperties.getPartitionKeyDefinition.getPaths.asScala.toArray should equal(Array("/id"))

    // validate throughput
    val throughput = cosmosClient.getDatabase(databaseName).getContainer(containerName).readThroughput().block().getProperties
    throughput.getManualThroughput shouldEqual 400
  }

  it can "create a table with customized properties" in {
    val databaseName = getAutoCleanableDatabaseName()
    val containerName = RandomStringUtils.randomAlphabetic(6).toLowerCase + System.currentTimeMillis()

    spark.sql(s"CREATE DATABASE testCatalog.$databaseName;")
    spark.sql(s"CREATE TABLE testCatalog.$databaseName.$containerName (word STRING, number INT) using cosmos.items " +
      s"TBLPROPERTIES(partitionKeyPath = '/mypk', manualThroughput = '1100')")

    val containerProperties = cosmosClient.getDatabase(databaseName).getContainer(containerName).read().block().getProperties
    containerProperties.getPartitionKeyDefinition.getPaths.asScala.toArray should equal(Array("/mypk"))
    // scalastyle:off null
    containerProperties.getDefaultTimeToLiveInSeconds shouldEqual null
    // scalastyle:on null

    // validate throughput
    val throughput = cosmosClient.getDatabase(databaseName).getContainer(containerName).readThroughput().block().getProperties
    throughput.getManualThroughput shouldEqual 1100
  }

  it can "create a table with well known indexing policy 'AllProperties'" in {
    val databaseName = getAutoCleanableDatabaseName()
    val containerName = RandomStringUtils.randomAlphabetic(6).toLowerCase + System.currentTimeMillis()

    spark.sql(s"CREATE DATABASE testCatalog.$databaseName;")
    spark.sql(s"CREATE TABLE testCatalog.$databaseName.$containerName (word STRING, number INT) using cosmos.items " +
      s"TBLPROPERTIES(partitionKeyPath = '/mypk', manualThroughput = '1100', indexingPolicy = 'AllProperties')")

    val containerProperties = cosmosClient.getDatabase(databaseName).getContainer(containerName).read().block().getProperties
    containerProperties.getPartitionKeyDefinition.getPaths.asScala.toArray should equal(Array("/mypk"))
    containerProperties
      .getIndexingPolicy
      .getIncludedPaths
      .asScala
      .map(p => p.getPath)
      .toArray should equal(Array("/*"))
    containerProperties
      .getIndexingPolicy
      .getExcludedPaths
      .asScala
      .map(p => p.getPath)
      .toArray should equal(Array(raw"""/"_etag"/?"""))

    // validate throughput
    val throughput = cosmosClient.getDatabase(databaseName).getContainer(containerName).readThroughput().block().getProperties
    throughput.getManualThroughput shouldEqual 1100
  }

  it can "create a table with well known indexing policy 'OnlySystemProperties'" in {
    val databaseName = getAutoCleanableDatabaseName()
    val containerName = RandomStringUtils.randomAlphabetic(6).toLowerCase + System.currentTimeMillis()

    spark.sql(s"CREATE DATABASE testCatalog.$databaseName;")
    spark.sql(s"CREATE TABLE testCatalog.$databaseName.$containerName (word STRING, number INT) using cosmos.items " +
      s"TBLPROPERTIES(partitionKeyPath = '/mypk', manualThroughput = '1100', indexingPolicy = 'ONLYSystemproperties')")

    val containerProperties = cosmosClient.getDatabase(databaseName).getContainer(containerName).read().block().getProperties
    containerProperties.getPartitionKeyDefinition.getPaths.toArray should equal(Array("/mypk"))
    containerProperties
      .getIndexingPolicy
      .getIncludedPaths
      .asScala.map(p => p.getPath)
      .toArray.length shouldEqual 0
    containerProperties
      .getIndexingPolicy
      .getExcludedPaths
      .asScala
      .map(p => p.getPath)
      .toArray should equal(Array("/*", raw"""/"_etag"/?"""))

    // validate throughput
    val throughput = cosmosClient.getDatabase(databaseName).getContainer(containerName).readThroughput().block().getProperties
    throughput.getManualThroughput shouldEqual 1100
  }

  it can "create a table with custom indexing policy" in {
    val databaseName = getAutoCleanableDatabaseName()
    val containerName = RandomStringUtils.randomAlphabetic(6).toLowerCase + System.currentTimeMillis()

    val indexPolicyJson = raw"""{"indexingMode":"consistent","automatic":true,"includedPaths":""" +
      raw"""[{"path":"\/helloWorld\/?"},{"path":"\/mypk\/?"}],"excludedPaths":[{"path":"\/*"}]}"""

    spark.sql(s"CREATE DATABASE testCatalog.$databaseName;")
    spark.sql(s"CREATE TABLE testCatalog.$databaseName.$containerName (word STRING, number INT) using cosmos.items " +
      s"TBLPROPERTIES(partitionKeyPath = '/mypk', manualThroughput = '1100', indexingPolicy = '$indexPolicyJson')")

    val containerProperties = cosmosClient.getDatabase(databaseName).getContainer(containerName).read().block().getProperties
    containerProperties.getPartitionKeyDefinition.getPaths.asScala.toArray should equal(Array("/mypk"))
    containerProperties
      .getIndexingPolicy
      .getIncludedPaths
      .asScala
      .map(p => p.getPath)
      .toArray should equal(Array("/helloWorld/?", "/mypk/?"))
    containerProperties
      .getIndexingPolicy
      .getExcludedPaths
      .asScala
      .map(p => p.getPath)
      .toArray should equal(Array("/*", raw"""/"_etag"/?"""))

    // validate throughput
    val throughput = cosmosClient.getDatabase(databaseName).getContainer(containerName).readThroughput().block().getProperties
    throughput.getManualThroughput shouldEqual 1100
  }

  it can "create a table with TTL -1" in {
    val databaseName = getAutoCleanableDatabaseName()
    val containerName = RandomStringUtils.randomAlphabetic(6).toLowerCase + System.currentTimeMillis()

    spark.sql(s"CREATE DATABASE testCatalog.$databaseName;")
    spark.sql(s"CREATE TABLE testCatalog.$databaseName.$containerName (word STRING, number INT) using cosmos.items " +
      s"TBLPROPERTIES(partitionKeyPath = '/mypk', defaultTtlInSeconds = '-1')")

    val containerProperties = cosmosClient.getDatabase(databaseName).getContainer(containerName).read().block().getProperties
    containerProperties.getPartitionKeyDefinition.getPaths.asScala.toArray should equal(Array("/mypk"))
    containerProperties.getDefaultTimeToLiveInSeconds shouldEqual -1
  }

  it can "create a table with positive TTL" in {
    val databaseName = getAutoCleanableDatabaseName()
    val containerName = RandomStringUtils.randomAlphabetic(6).toLowerCase + System.currentTimeMillis()

    spark.sql(s"CREATE DATABASE testCatalog.$databaseName;")
    spark.sql(s"CREATE TABLE testCatalog.$databaseName.$containerName (word STRING, number INT) using cosmos.items " +
      s"TBLPROPERTIES(partitionKeyPath = '/mypk', defaultTtlInSeconds = '5')")

    val containerProperties = cosmosClient.getDatabase(databaseName).getContainer(containerName).read().block().getProperties
    containerProperties.getPartitionKeyDefinition.getPaths.asScala.toArray should equal(Array("/mypk"))
    containerProperties.getDefaultTimeToLiveInSeconds shouldEqual 5
  }

  private def createDatabase(spark: SparkSession, databaseName: String) = {
    spark.sql(s"CREATE DATABASE testCatalog.$databaseName;")
  }

  private def dropDatabase(spark: SparkSession, databaseName: String) = {
    spark.sql(s"DROP DATABASE testCatalog.$databaseName;")
  }

  //scalastyle:on magic.number
  //scalastyle:on multiple.string.literals
}
