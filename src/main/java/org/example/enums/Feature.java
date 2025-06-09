package org.example.enums;

import static java.util.Objects.isNull;

public enum Feature {
    TRAILERS("Trailers"),
    COMMENTARIES("Commentaries"),
    DELETED_SCENES("Deleted Scenes"),
    BEHIND_THE_SCENES("Behind the Scenes");

    private final String value;

    Feature(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Feature getFeatureByValue(String value) {
        Feature feature = null;
        if(isNull(value) || value.isEmpty()) {
            return  null;
        }
        for (Feature values : Feature.values()) {
            if(values.value.equals(value)) {
                feature = values;
            }
        }
        return feature;
    }
}
