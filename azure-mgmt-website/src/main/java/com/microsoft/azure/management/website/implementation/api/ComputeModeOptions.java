/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */

package com.microsoft.azure.management.website.implementation.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Defines values for ComputeModeOptions.
 */
public enum ComputeModeOptions {
    /** Enum value Shared. */
    SHARED("Shared"),

    /** Enum value Dedicated. */
    DEDICATED("Dedicated"),

    /** Enum value Dynamic. */
    DYNAMIC("Dynamic");

    /** The actual serialized value for a ComputeModeOptions instance. */
    private String value;

    ComputeModeOptions(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a ComputeModeOptions instance.
     *
     * @param value the serialized value to parse.
     * @return the parsed ComputeModeOptions object, or null if unable to parse.
     */
    @JsonCreator
    public static ComputeModeOptions fromString(String value) {
        ComputeModeOptions[] items = ComputeModeOptions.values();
        for (ComputeModeOptions item : items) {
            if (item.toString().equalsIgnoreCase(value)) {
                return item;
            }
        }
        return null;
    }

    @JsonValue
    @Override
    public String toString() {
        return this.value;
    }
}
