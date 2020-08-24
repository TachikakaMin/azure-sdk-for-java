// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.resourcemanager.network.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.JsonFlatten;
import com.azure.core.management.SubResource;
import com.azure.core.util.logging.ClientLogger;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The ApplicationGatewayFrontendIpConfiguration model. */
@JsonFlatten
@Fluent
public class ApplicationGatewayFrontendIpConfiguration extends SubResource {
    @JsonIgnore private final ClientLogger logger = new ClientLogger(ApplicationGatewayFrontendIpConfiguration.class);

    /*
     * Name of the frontend IP configuration that is unique within an
     * Application Gateway.
     */
    @JsonProperty(value = "name")
    private String name;

    /*
     * A unique read-only string that changes whenever the resource is updated.
     */
    @JsonProperty(value = "etag", access = JsonProperty.Access.WRITE_ONLY)
    private String etag;

    /*
     * Type of the resource.
     */
    @JsonProperty(value = "type", access = JsonProperty.Access.WRITE_ONLY)
    private String type;

    /*
     * PrivateIPAddress of the network interface IP Configuration.
     */
    @JsonProperty(value = "properties.privateIPAddress")
    private String privateIpAddress;

    /*
     * The private IP address allocation method.
     */
    @JsonProperty(value = "properties.privateIPAllocationMethod")
    private IpAllocationMethod privateIpAllocationMethod;

    /*
     * Reference to the subnet resource.
     */
    @JsonProperty(value = "properties.subnet")
    private SubResource subnet;

    /*
     * Reference to the PublicIP resource.
     */
    @JsonProperty(value = "properties.publicIPAddress")
    private SubResource publicIpAddress;

    /*
     * The provisioning state of the frontend IP configuration resource.
     */
    @JsonProperty(value = "properties.provisioningState", access = JsonProperty.Access.WRITE_ONLY)
    private ProvisioningState provisioningState;

    /**
     * Get the name property: Name of the frontend IP configuration that is unique within an Application Gateway.
     *
     * @return the name value.
     */
    public String name() {
        return this.name;
    }

    /**
     * Set the name property: Name of the frontend IP configuration that is unique within an Application Gateway.
     *
     * @param name the name value to set.
     * @return the ApplicationGatewayFrontendIpConfiguration object itself.
     */
    public ApplicationGatewayFrontendIpConfiguration withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get the etag property: A unique read-only string that changes whenever the resource is updated.
     *
     * @return the etag value.
     */
    public String etag() {
        return this.etag;
    }

    /**
     * Get the type property: Type of the resource.
     *
     * @return the type value.
     */
    public String type() {
        return this.type;
    }

    /**
     * Get the privateIpAddress property: PrivateIPAddress of the network interface IP Configuration.
     *
     * @return the privateIpAddress value.
     */
    public String privateIpAddress() {
        return this.privateIpAddress;
    }

    /**
     * Set the privateIpAddress property: PrivateIPAddress of the network interface IP Configuration.
     *
     * @param privateIpAddress the privateIpAddress value to set.
     * @return the ApplicationGatewayFrontendIpConfiguration object itself.
     */
    public ApplicationGatewayFrontendIpConfiguration withPrivateIpAddress(String privateIpAddress) {
        this.privateIpAddress = privateIpAddress;
        return this;
    }

    /**
     * Get the privateIpAllocationMethod property: The private IP address allocation method.
     *
     * @return the privateIpAllocationMethod value.
     */
    public IpAllocationMethod privateIpAllocationMethod() {
        return this.privateIpAllocationMethod;
    }

    /**
     * Set the privateIpAllocationMethod property: The private IP address allocation method.
     *
     * @param privateIpAllocationMethod the privateIpAllocationMethod value to set.
     * @return the ApplicationGatewayFrontendIpConfiguration object itself.
     */
    public ApplicationGatewayFrontendIpConfiguration withPrivateIpAllocationMethod(
        IpAllocationMethod privateIpAllocationMethod) {
        this.privateIpAllocationMethod = privateIpAllocationMethod;
        return this;
    }

    /**
     * Get the subnet property: Reference to the subnet resource.
     *
     * @return the subnet value.
     */
    public SubResource subnet() {
        return this.subnet;
    }

    /**
     * Set the subnet property: Reference to the subnet resource.
     *
     * @param subnet the subnet value to set.
     * @return the ApplicationGatewayFrontendIpConfiguration object itself.
     */
    public ApplicationGatewayFrontendIpConfiguration withSubnet(SubResource subnet) {
        this.subnet = subnet;
        return this;
    }

    /**
     * Get the publicIpAddress property: Reference to the PublicIP resource.
     *
     * @return the publicIpAddress value.
     */
    public SubResource publicIpAddress() {
        return this.publicIpAddress;
    }

    /**
     * Set the publicIpAddress property: Reference to the PublicIP resource.
     *
     * @param publicIpAddress the publicIpAddress value to set.
     * @return the ApplicationGatewayFrontendIpConfiguration object itself.
     */
    public ApplicationGatewayFrontendIpConfiguration withPublicIpAddress(SubResource publicIpAddress) {
        this.publicIpAddress = publicIpAddress;
        return this;
    }

    /**
     * Get the provisioningState property: The provisioning state of the frontend IP configuration resource.
     *
     * @return the provisioningState value.
     */
    public ProvisioningState provisioningState() {
        return this.provisioningState;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
    }
}