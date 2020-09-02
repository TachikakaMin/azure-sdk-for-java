// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.resourcemanager.appplatform.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.logging.ClientLogger;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The ConfigServerProperties model. */
@Fluent
public final class ConfigServerProperties {
    @JsonIgnore private final ClientLogger logger = new ClientLogger(ConfigServerProperties.class);

    /*
     * State of the config server.
     */
    @JsonProperty(value = "provisioningState", access = JsonProperty.Access.WRITE_ONLY)
    private ConfigServerState provisioningState;

    /*
     * Error when apply config server settings.
     */
    @JsonProperty(value = "error")
    private Error error;

    /*
     * Settings of config server.
     */
    @JsonProperty(value = "configServer")
    private ConfigServerSettings configServer;

    /**
     * Get the provisioningState property: State of the config server.
     *
     * @return the provisioningState value.
     */
    public ConfigServerState provisioningState() {
        return this.provisioningState;
    }

    /**
     * Get the error property: Error when apply config server settings.
     *
     * @return the error value.
     */
    public Error error() {
        return this.error;
    }

    /**
     * Set the error property: Error when apply config server settings.
     *
     * @param error the error value to set.
     * @return the ConfigServerProperties object itself.
     */
    public ConfigServerProperties withError(Error error) {
        this.error = error;
        return this;
    }

    /**
     * Get the configServer property: Settings of config server.
     *
     * @return the configServer value.
     */
    public ConfigServerSettings configServer() {
        return this.configServer;
    }

    /**
     * Set the configServer property: Settings of config server.
     *
     * @param configServer the configServer value to set.
     * @return the ConfigServerProperties object itself.
     */
    public ConfigServerProperties withConfigServer(ConfigServerSettings configServer) {
        this.configServer = configServer;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (error() != null) {
            error().validate();
        }
        if (configServer() != null) {
            configServer().validate();
        }
    }
}