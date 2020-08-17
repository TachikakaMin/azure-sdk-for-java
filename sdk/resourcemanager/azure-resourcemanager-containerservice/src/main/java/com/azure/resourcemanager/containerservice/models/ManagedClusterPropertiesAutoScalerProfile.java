// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.resourcemanager.containerservice.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.logging.ClientLogger;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The ManagedClusterPropertiesAutoScalerProfile model. */
@Fluent
public final class ManagedClusterPropertiesAutoScalerProfile {
    @JsonIgnore private final ClientLogger logger = new ClientLogger(ManagedClusterPropertiesAutoScalerProfile.class);

    /*
     * The balance-similar-node-groups property.
     */
    @JsonProperty(value = "balance-similar-node-groups")
    private String balanceSimilarNodeGroups;

    /*
     * The scan-interval property.
     */
    @JsonProperty(value = "scan-interval")
    private String scanInterval;

    /*
     * The scale-down-delay-after-add property.
     */
    @JsonProperty(value = "scale-down-delay-after-add")
    private String scaleDownDelayAfterAdd;

    /*
     * The scale-down-delay-after-delete property.
     */
    @JsonProperty(value = "scale-down-delay-after-delete")
    private String scaleDownDelayAfterDelete;

    /*
     * The scale-down-delay-after-failure property.
     */
    @JsonProperty(value = "scale-down-delay-after-failure")
    private String scaleDownDelayAfterFailure;

    /*
     * The scale-down-unneeded-time property.
     */
    @JsonProperty(value = "scale-down-unneeded-time")
    private String scaleDownUnneededTime;

    /*
     * The scale-down-unready-time property.
     */
    @JsonProperty(value = "scale-down-unready-time")
    private String scaleDownUnreadyTime;

    /*
     * The scale-down-utilization-threshold property.
     */
    @JsonProperty(value = "scale-down-utilization-threshold")
    private String scaleDownUtilizationThreshold;

    /*
     * The max-graceful-termination-sec property.
     */
    @JsonProperty(value = "max-graceful-termination-sec")
    private String maxGracefulTerminationSec;

    /**
     * Get the balanceSimilarNodeGroups property: The balance-similar-node-groups property.
     *
     * @return the balanceSimilarNodeGroups value.
     */
    public String balanceSimilarNodeGroups() {
        return this.balanceSimilarNodeGroups;
    }

    /**
     * Set the balanceSimilarNodeGroups property: The balance-similar-node-groups property.
     *
     * @param balanceSimilarNodeGroups the balanceSimilarNodeGroups value to set.
     * @return the ManagedClusterPropertiesAutoScalerProfile object itself.
     */
    public ManagedClusterPropertiesAutoScalerProfile withBalanceSimilarNodeGroups(String balanceSimilarNodeGroups) {
        this.balanceSimilarNodeGroups = balanceSimilarNodeGroups;
        return this;
    }

    /**
     * Get the scanInterval property: The scan-interval property.
     *
     * @return the scanInterval value.
     */
    public String scanInterval() {
        return this.scanInterval;
    }

    /**
     * Set the scanInterval property: The scan-interval property.
     *
     * @param scanInterval the scanInterval value to set.
     * @return the ManagedClusterPropertiesAutoScalerProfile object itself.
     */
    public ManagedClusterPropertiesAutoScalerProfile withScanInterval(String scanInterval) {
        this.scanInterval = scanInterval;
        return this;
    }

    /**
     * Get the scaleDownDelayAfterAdd property: The scale-down-delay-after-add property.
     *
     * @return the scaleDownDelayAfterAdd value.
     */
    public String scaleDownDelayAfterAdd() {
        return this.scaleDownDelayAfterAdd;
    }

    /**
     * Set the scaleDownDelayAfterAdd property: The scale-down-delay-after-add property.
     *
     * @param scaleDownDelayAfterAdd the scaleDownDelayAfterAdd value to set.
     * @return the ManagedClusterPropertiesAutoScalerProfile object itself.
     */
    public ManagedClusterPropertiesAutoScalerProfile withScaleDownDelayAfterAdd(String scaleDownDelayAfterAdd) {
        this.scaleDownDelayAfterAdd = scaleDownDelayAfterAdd;
        return this;
    }

    /**
     * Get the scaleDownDelayAfterDelete property: The scale-down-delay-after-delete property.
     *
     * @return the scaleDownDelayAfterDelete value.
     */
    public String scaleDownDelayAfterDelete() {
        return this.scaleDownDelayAfterDelete;
    }

    /**
     * Set the scaleDownDelayAfterDelete property: The scale-down-delay-after-delete property.
     *
     * @param scaleDownDelayAfterDelete the scaleDownDelayAfterDelete value to set.
     * @return the ManagedClusterPropertiesAutoScalerProfile object itself.
     */
    public ManagedClusterPropertiesAutoScalerProfile withScaleDownDelayAfterDelete(String scaleDownDelayAfterDelete) {
        this.scaleDownDelayAfterDelete = scaleDownDelayAfterDelete;
        return this;
    }

    /**
     * Get the scaleDownDelayAfterFailure property: The scale-down-delay-after-failure property.
     *
     * @return the scaleDownDelayAfterFailure value.
     */
    public String scaleDownDelayAfterFailure() {
        return this.scaleDownDelayAfterFailure;
    }

    /**
     * Set the scaleDownDelayAfterFailure property: The scale-down-delay-after-failure property.
     *
     * @param scaleDownDelayAfterFailure the scaleDownDelayAfterFailure value to set.
     * @return the ManagedClusterPropertiesAutoScalerProfile object itself.
     */
    public ManagedClusterPropertiesAutoScalerProfile withScaleDownDelayAfterFailure(String scaleDownDelayAfterFailure) {
        this.scaleDownDelayAfterFailure = scaleDownDelayAfterFailure;
        return this;
    }

    /**
     * Get the scaleDownUnneededTime property: The scale-down-unneeded-time property.
     *
     * @return the scaleDownUnneededTime value.
     */
    public String scaleDownUnneededTime() {
        return this.scaleDownUnneededTime;
    }

    /**
     * Set the scaleDownUnneededTime property: The scale-down-unneeded-time property.
     *
     * @param scaleDownUnneededTime the scaleDownUnneededTime value to set.
     * @return the ManagedClusterPropertiesAutoScalerProfile object itself.
     */
    public ManagedClusterPropertiesAutoScalerProfile withScaleDownUnneededTime(String scaleDownUnneededTime) {
        this.scaleDownUnneededTime = scaleDownUnneededTime;
        return this;
    }

    /**
     * Get the scaleDownUnreadyTime property: The scale-down-unready-time property.
     *
     * @return the scaleDownUnreadyTime value.
     */
    public String scaleDownUnreadyTime() {
        return this.scaleDownUnreadyTime;
    }

    /**
     * Set the scaleDownUnreadyTime property: The scale-down-unready-time property.
     *
     * @param scaleDownUnreadyTime the scaleDownUnreadyTime value to set.
     * @return the ManagedClusterPropertiesAutoScalerProfile object itself.
     */
    public ManagedClusterPropertiesAutoScalerProfile withScaleDownUnreadyTime(String scaleDownUnreadyTime) {
        this.scaleDownUnreadyTime = scaleDownUnreadyTime;
        return this;
    }

    /**
     * Get the scaleDownUtilizationThreshold property: The scale-down-utilization-threshold property.
     *
     * @return the scaleDownUtilizationThreshold value.
     */
    public String scaleDownUtilizationThreshold() {
        return this.scaleDownUtilizationThreshold;
    }

    /**
     * Set the scaleDownUtilizationThreshold property: The scale-down-utilization-threshold property.
     *
     * @param scaleDownUtilizationThreshold the scaleDownUtilizationThreshold value to set.
     * @return the ManagedClusterPropertiesAutoScalerProfile object itself.
     */
    public ManagedClusterPropertiesAutoScalerProfile withScaleDownUtilizationThreshold(
        String scaleDownUtilizationThreshold) {
        this.scaleDownUtilizationThreshold = scaleDownUtilizationThreshold;
        return this;
    }

    /**
     * Get the maxGracefulTerminationSec property: The max-graceful-termination-sec property.
     *
     * @return the maxGracefulTerminationSec value.
     */
    public String maxGracefulTerminationSec() {
        return this.maxGracefulTerminationSec;
    }

    /**
     * Set the maxGracefulTerminationSec property: The max-graceful-termination-sec property.
     *
     * @param maxGracefulTerminationSec the maxGracefulTerminationSec value to set.
     * @return the ManagedClusterPropertiesAutoScalerProfile object itself.
     */
    public ManagedClusterPropertiesAutoScalerProfile withMaxGracefulTerminationSec(String maxGracefulTerminationSec) {
        this.maxGracefulTerminationSec = maxGracefulTerminationSec;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
    }
}