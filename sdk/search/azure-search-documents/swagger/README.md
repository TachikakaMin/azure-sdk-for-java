# Azure Search Documents for Java
    
> see https://aka.ms/autorest

This is the AutoRest configuration file for SearchServiceClient and SearchIndexClient.
---
## Getting Started 

To build the SDK for SearchServiceClient and SearchIndexClient, simply [Install AutoRest](https://aka.ms/autorest) and in this folder, run:

> `autorest`

To see additional help and options, run:

> `autorest --help`

### Setup
```ps
Fork and clone https://github.com/Azure/autorest.java 
git checkout v4
git submodule update --init --recursive
mvn package -Dlocal -DskipTests
npm install
npm install -g autorest
```

### Generation

There are two swaggers for Azure Search, `searchindex` and `searchservice`. They always under same package version, e.g. 
`--tag=package-2020-06-searchindex` and `--tag=package-2020-06-searchservice`.

```ps
cd <swagger-folder>
autorest --use=C:/work/autorest.java
```

e.g.
```ps
cd <swagger-folder>
autorest --use=C:/work/autorest.java --tag=package-2020-06-searchindex
autorest --use=C:/work/autorest.java --tag=package-2020-06-searchservice
```
## Configuration

### Basic Information 
These are the global settings for SearchServiceClient and SearchIndexClient.

``` yaml
opt-in-extensible-enums: true
openapi-type: data-plane
```

### Tag: package-2020-06-searchindex

These settings apply only when `--tag=package-2020-06-searchindex` is specified on the command line.

``` yaml $(tag) == 'package-2020-06-searchindex'
namespace: com.azure.search.documents
input-file:
- https://raw.githubusercontent.com/Azure/azure-rest-api-specs/56348ad232409e3266eaef220e88ae5d64f49ad7/specification/search/data-plane/Azure.Search/preview/2020-06-30/searchindex.json
models-subpackage: implementation.models
custom-types-subpackage: models
custom-types: AutocompleteItem,AutocompleteMode,AutocompleteResult,FacetResult,IndexActionType,QueryType,ScoringStatistics,SearchMode
customization-class: src/main/java/SearchIndexCustomizations.java
```

### Tag: package-2020-06-searchservice

These settings apply only when `--tag=package-2020-06-searchservice` is specified on the command line.

``` yaml $(tag) == 'package-2020-06-searchservice'
namespace: com.azure.search.documents.indexes
input-file:
- https://raw.githubusercontent.com/Azure/azure-rest-api-specs/56348ad232409e3266eaef220e88ae5d64f49ad7/specification/search/data-plane/Azure.Search/preview/2020-06-30/searchservice.json
models-subpackage: implementation.models
custom-types-subpackage: models
custom-types: AnalyzedTokenInfo,BlobIndexerDataToExtract,BlobIndexerImageAction,BlobIndexerPdfTextRotationAlgorithm,BlobIndexerParsingMode,BM25SimilarityAlgorithm,CharFilter,CharFilterName,CjkBigramTokenFilterScripts,ClassicSimilarityAlgorithm,CognitiveServicesAccount,CognitiveServicesAccountKey,CorsOptions,DataChangeDetectionPolicy,DataDeletionDetectionPolicy,DefaultCognitiveServicesAccount,DistanceScoringFunction,DistanceScoringParameters,EdgeNGramTokenFilterSide,EntityCategory,EntityRecognitionSkillLanguage,FieldMapping,FieldMappingFunction,FreshnessScoringFunction,FreshnessScoringParameters,HighWaterMarkChangeDetectionPolicy,ImageAnalysisSkillLanguage,ImageDetail,IndexerExecutionEnvironment,IndexerExecutionResult,IndexerExecutionStatus,IndexerStatus,IndexingParametersConfiguration,IndexingSchedule,InputFieldMappingEntry,KeyPhraseExtractionSkillLanguage,LexicalAnalyzerName,LexicalTokenizerName,MagnitudeScoringFunction,MagnitudeScoringParameters,MappingCharFilter,MicrosoftStemmingTokenizerLanguage,MicrosoftTokenizerLanguage,OcrSkillLanguage,OutputFieldMappingEntry,PatternReplaceCharFilter,PhoneticEncoder,RegexFlags,ResourceCounter,ScoringFunction,ScoringFunctionAggregation,ScoringFunctionInterpolation,ScoringProfile,SearchFieldDataType,SearchIndexerDataContainer,SearchIndexerDataSourceType,SearchIndexerError,SearchIndexerLimits,SearchIndexerStatus,SearchIndexerWarning,SearchIndexStatistics,SearchServiceCounters,SearchServiceLimits,SearchServiceStatistics,SentimentSkillLanguage,SimilarityAlgorithm,SnowballTokenFilterLanguage,SoftDeleteColumnDeletionDetectionPolicy,SplitSkillLanguage,SqlIntegratedChangeTrackingPolicy,StemmerTokenFilterLanguage,StopwordsList,TagScoringFunction,TagScoringParameters,TextSplitMode,TextTranslationSkillLanguage,TextWeights,TokenCharacterKind,TokenFilterName,VisualFeature
customization-class: src/main/java/SearchServiceCustomizations.java
directive:
    - rename-model:
        from: ClassicSimilarity
        to: ClassicSimilarityAlgorithm
    - rename-model:
        from: BM25Similarity
        to: BM25SimilarityAlgorithm
    - rename-model:
        from: Similarity
        to: SimilarityAlgorithm
    - rename-model:
        from: GetIndexStatisticsResult
        to: SearchIndexStatistics
```

---
# Code Generation

!!! READ THIS !!!
This swagger is ready for C# and Java.
!!! READ THIS !!!

## Java

``` yaml
output-folder: ../
java: true
sync-methods: none
generate-client-interfaces: false
context-client-method-parameter: true
generate-client-as-impl: true
service-interface-as-public: true
required-fields-as-ctor-args: true
license-header: |-
  Copyright (c) Microsoft Corporation. All rights reserved.
  Licensed under the MIT License.
  Code generated by Microsoft (R) AutoRest Code Generator.
  Changes may cause incorrect behavior and will be lost if the code is regenerated.
```

### Set odata.metadata Accept header in operations

searchindex.json needs odata.metadata=none and searchservice.json needs odata.metadata=minimal as the Accept header.

``` yaml $(java)
directive:
  - from: swagger-document
    where: $.paths
    transform: >
      for (var path in $) {
        for (var opName in $[path]) {
          var accept = "application/json; odata.metadata=";
          accept += path.startsWith("/docs") ? "none" : "minimal";

          var op = $[path][opName];
          var param = op.parameters.find(p => p.name === "Accept");
          if (param === null) {
            param.enum = [ accept ];
          } else {
            op.parameters.push({
              name: "Accept",
              "in": "header",
              required: true,
              type: "string",
              enum: [ accept ],
              "x-ms-parameter-location": "method"
            });
          }
        }
      }

      return $;
```

### Remove required from properties that are optional

``` yaml $(java)
directive:
  - from: swagger-document
    where: $.definitions
    transform: >
      delete $.SearchIndex.required;
      delete $.SearchIndexer.required;
      delete $.SearchIndexerDataSource.required;
      delete $.SearchIndexerSkillset.required;
      delete $.SynonymMap.required;
```

### Renames
``` yaml $(java)
directive:
  - from: swagger-document
    where: $.definitions
    transform: >
      $.ServiceCounters["x-ms-client-name"] = "SearchServiceCounters";
      $.ServiceLimits["x-ms-client-name"] = "SearchServiceLimits";
      $.ServiceStatistics["x-ms-client-name"] = "SearchServiceStatistics";
```

``` yaml $(java)
directive:
  - from: swagger-document
    where: $.definitions.PdfTextRotationAlgorithm
    transform: >
      $["x-ms-enum"].name = "BlobIndexerPdfTextRotationAlgorithm";
```
