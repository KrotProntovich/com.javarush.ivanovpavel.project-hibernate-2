package org.example.service;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.example.enums.Feature;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class FeatureConverter implements AttributeConverter<Set<Feature>, String> {

    @Override
    public String convertToDatabaseColumn(Set<Feature> attribute) {
        return String.join(",", attribute.stream().map(Feature::getValue).collect(Collectors.toSet()));
    }

    @Override
    public Set<Feature> convertToEntityAttribute(String dbData) {
        Set<Feature> result = new HashSet<>();
        Stream.of(dbData.split(",")).collect(Collectors.toSet()).forEach(s -> {
            for(Feature feature : Feature.values()) {
                if(feature.getValue().equals(s)) {
                    result.add(feature);
                }
            }
        });
        return result;
    }
}
