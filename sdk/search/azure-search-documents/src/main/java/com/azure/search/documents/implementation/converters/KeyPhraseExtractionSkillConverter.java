// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.search.documents.implementation.converters;

import com.azure.search.documents.indexes.models.KeyPhraseExtractionSkill;

/**
 * A converter between {@link com.azure.search.documents.indexes.implementation.models.KeyPhraseExtractionSkill} and
 * {@link KeyPhraseExtractionSkill}.
 */
public final class KeyPhraseExtractionSkillConverter {
    /**
     * Maps from {@link com.azure.search.documents.indexes.implementation.models.KeyPhraseExtractionSkill} to
     * {@link KeyPhraseExtractionSkill}.
     */
    public static KeyPhraseExtractionSkill map(com.azure.search.documents.indexes.implementation.models.KeyPhraseExtractionSkill obj) {
        if (obj == null) {
            return null;
        }
        KeyPhraseExtractionSkill keyPhraseExtractionSkill = new KeyPhraseExtractionSkill(obj.getInputs(),
            obj.getOutputs());

        String name = obj.getName();
        keyPhraseExtractionSkill.setName(name);

        String context = obj.getContext();
        keyPhraseExtractionSkill.setContext(context);

        String description = obj.getDescription();
        keyPhraseExtractionSkill.setDescription(description);

        Integer maxKeyPhraseCount = obj.getMaxKeyPhraseCount();
        keyPhraseExtractionSkill.setMaxKeyPhraseCount(maxKeyPhraseCount);

        if (obj.getDefaultLanguageCode() != null) {
            keyPhraseExtractionSkill.setDefaultLanguageCode(obj.getDefaultLanguageCode());
        }
        return keyPhraseExtractionSkill;
    }

    /**
     * Maps from {@link KeyPhraseExtractionSkill} to
     * {@link com.azure.search.documents.indexes.implementation.models.KeyPhraseExtractionSkill}.
     */
    public static com.azure.search.documents.indexes.implementation.models.KeyPhraseExtractionSkill map(KeyPhraseExtractionSkill obj) {
        if (obj == null) {
            return null;
        }

        com.azure.search.documents.indexes.implementation.models.KeyPhraseExtractionSkill keyPhraseExtractionSkill =
            new com.azure.search.documents.indexes.implementation.models.KeyPhraseExtractionSkill(obj.getInputs(),
                obj.getOutputs());

        String name = obj.getName();
        keyPhraseExtractionSkill.setName(name);

        String context = obj.getContext();
        keyPhraseExtractionSkill.setContext(context);

        String description = obj.getDescription();
        keyPhraseExtractionSkill.setDescription(description);

        Integer maxKeyPhraseCount = obj.getMaxKeyPhraseCount();
        keyPhraseExtractionSkill.setMaxKeyPhraseCount(maxKeyPhraseCount);

        if (obj.getDefaultLanguageCode() != null) {
            keyPhraseExtractionSkill.setDefaultLanguageCode(obj.getDefaultLanguageCode());
        }

        return keyPhraseExtractionSkill;
    }

    private KeyPhraseExtractionSkillConverter() {
    }
}
