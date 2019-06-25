/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.machinelearningservices.v2019_05_01.implementation;

import com.microsoft.azure.management.machinelearningservices.v2019_05_01.ComputeSecrets;
import com.microsoft.azure.arm.model.implementation.WrapperImpl;

class ComputeSecretsImpl extends WrapperImpl<ComputeSecretsInner> implements ComputeSecrets {
    private final MachineLearningServicesManager manager;
    ComputeSecretsImpl(ComputeSecretsInner inner, MachineLearningServicesManager manager) {
        super(inner);
        this.manager = manager;
    }

    @Override
    public MachineLearningServicesManager manager() {
        return this.manager;
    }

}
