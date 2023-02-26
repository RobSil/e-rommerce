package com.robsil.erommerce.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robsil.erommerce.model.ERole;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
@Converter(autoApply = true)
@RequiredArgsConstructor
//deprecated, to be deleted.
@Deprecated(forRemoval = true)
public class RoleToJsonConverter implements AttributeConverter<List<ERole>, String> {

    private final ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(List<ERole> attribute) {

        if (attribute == null) {
            return "{}";
        }

        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error occurred during converting ERoles to JSON", e);
        }
    }

    @Override
    public List<ERole> convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return Collections.emptyList();
        }

        try {
            return objectMapper.readValue(dbData, new TypeReference<List<ERole>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error occurred during converting JSON to ERoles", e);
        }
    }
}
