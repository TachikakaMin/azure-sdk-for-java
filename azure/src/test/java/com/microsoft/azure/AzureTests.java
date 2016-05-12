package com.microsoft.azure;

import com.microsoft.azure.credentials.ApplicationTokenCredentials;
import com.microsoft.azure.credentials.AzureEnvironment;
import com.microsoft.azure.implementation.Azure;
import com.microsoft.azure.management.network.PublicIpAddress;
import com.microsoft.azure.management.resources.Subscriptions;
import com.microsoft.azure.management.resources.fluentcore.arm.Region;
import com.microsoft.azure.management.storage.StorageAccount;
import com.microsoft.azure.management.storage.implementation.api.AccountType;
import com.microsoft.rest.credentials.ServiceClientCredentials;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AzureTests {
    private static final ServiceClientCredentials credentials = new ApplicationTokenCredentials(
            System.getenv("client-id"),
            System.getenv("domain"),
            System.getenv("secret"),
            AzureEnvironment.AZURE);
    private static final String subscriptionId = System.getenv("resourceClient-id");
    private Subscriptions subscriptions;
    private Azure azure, azure2;

    public static void main(String[] args) throws IOException, CloudException {
    	Azure azure = Azure.authenticate(new File("my.auth"))
    		.withSubscription("9657ab5d-4a4a-4fd2-ae7a-4cd9fbd030ef");
    	
    	System.out.println(String.valueOf(azure.resourceGroups().list().size()));
    	
    	Azure.configure().withLogLevel(Level.BASIC).authenticate(new File("my.auth"));
    	System.out.println(String.valueOf(azure.resourceGroups().list().size()));
    }
    
    @Before
    public void setup() throws Exception {
        // Authenticate based on credentials instance
    	Azure.Authenticated azureAuthed = Azure.configure()
                .withLogLevel(HttpLoggingInterceptor.Level.BASIC)
                .withUserAgent("AzureTests")
                .authenticate(credentials);

        subscriptions = azureAuthed.subscriptions();
        azure = azureAuthed.withSubscription(subscriptionId);
        
        // Authenticate based on file
    	this.azure2 = Azure.authenticate(new File("my.auth"))
        	.withSubscription("9657ab5d-4a4a-4fd2-ae7a-4cd9fbd030ef");
        
    }

    @Test public void listPublicIpAddresses() throws Exception {
    	// Create a new public IP address the minimal way
    	String suffix = String.valueOf(System.currentTimeMillis());
    	String newPipName = "pip" + suffix;
    	PublicIpAddress pip = azure2.publicIpAddresses().define(newPipName)
    		.withRegion(Region.US_WEST)
    		.withNewGroup()
    		.provision();
    	
    	// Verify list
    	int publicIpAddressCount = azure2.publicIpAddresses().list().size();
    	System.out.println(publicIpAddressCount);
    	Assert.assertTrue(0 < publicIpAddressCount);
    	String resourceGroupName = pip.group();
    	pip = azure2.publicIpAddresses().get(resourceGroupName, newPipName);
    	Assert.assertTrue(pip.name().equalsIgnoreCase(newPipName));
    	
    	// Verify delete
    	azure.publicIpAddresses().delete(pip.id());
    }
    
    @Test
    public void listSubscriptions() throws Exception {
        Assert.assertTrue(0 < subscriptions.list().size());
    }

    @Test
    public void listResourceGroups() throws Exception {
        Assert.assertTrue(0 < azure.resourceGroups().list().size());
    }

    @Test
    public void listStorageAccounts() throws Exception {
        Assert.assertTrue(0 < azure.storageAccounts().list().size());
    }
    
    @Test
    public void createStorageAccount() throws Exception {
        StorageAccount storageAccount = azure.storageAccounts().define("my-stg1")
                .withRegion(Region.ASIA_EAST)
                .withNewGroup()
                .withAccountType(AccountType.PREMIUM_LRS)
                .provision();

        Assert.assertSame(storageAccount.name(), "my-stg1");
    }

    @Test
    public void createStorageAccountInResourceGroupContext() throws Exception {
        StorageAccount storageAccount = azure.resourceGroups().get("my-grp")
                .storageAccounts()
                .define("my-stg2")
                .withAccountType(AccountType.PREMIUM_LRS)
                .provision();

        Assert.assertSame(storageAccount.name(), "my-stg2");
    }
}
