/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */

package com.microsoft.azure.management.website.implementation.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Defines values for FrequencyUnit.
 */
public enum FrequencyUnit {
    /** Enum value Day. */
    DAY("Day"),

    /** Enum value Hour. */
    HOUR("Hour");

    /** The actual serialized value for a FrequencyUnit instance. */
    private String value;

    FrequencyUnit(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a FrequencyUnit instance.
     *
     * @param value the serialized value to parse.
     * @return the parsed FrequencyUnit object, or null if unable to parse.
     */
    @JsonCreator
    public static FrequencyUnit fromString(String value) {
        FrequencyUnit[] items = FrequencyUnit.values();
        for (FrequencyUnit item : items) {
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
