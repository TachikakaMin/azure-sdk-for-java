/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */

package com.microsoft.azure.management.website.implementation.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Defines values for CertificateProductType.
 */
public enum CertificateProductType {
    /** Enum value StandardDomainValidatedSsl. */
    STANDARD_DOMAIN_VALIDATED_SSL("StandardDomainValidatedSsl"),

    /** Enum value StandardDomainValidatedWildCardSsl. */
    STANDARD_DOMAIN_VALIDATED_WILD_CARD_SSL("StandardDomainValidatedWildCardSsl");

    /** The actual serialized value for a CertificateProductType instance. */
    private String value;

    CertificateProductType(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a CertificateProductType instance.
     *
     * @param value the serialized value to parse.
     * @return the parsed CertificateProductType object, or null if unable to parse.
     */
    @JsonCreator
    public static CertificateProductType fromString(String value) {
        CertificateProductType[] items = CertificateProductType.values();
        for (CertificateProductType item : items) {
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
