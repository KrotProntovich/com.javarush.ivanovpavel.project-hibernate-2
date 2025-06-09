package org.example.service;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.example.enums.Rating;

@Converter(autoApply = true)
public class RatingConverter implements AttributeConverter<Rating, String> {
    @Override
    public String convertToDatabaseColumn(Rating attribute) {
        if(attribute == null){
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public Rating convertToEntityAttribute(String dbData) {
        Rating rating = null;
        Rating[] values = Rating.values();
        for (Rating value : values) {
            if(value.getValue().equals(dbData)){
                rating = value;
            }
        }
        return rating;
    }
}
