package io.github.yajanth.clutch.convertor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.yajanth.clutch.entity.Payload;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class PayloadConverter implements AttributeConverter<Payload, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
    public String convertToDatabaseColumn(Payload payload) {
        try {
            return objectMapper.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Failed to serialize payload", e);
        }
    }
	@Override
    public Payload convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, Payload.class);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to deserialize payload", e);
        }
    }
}


