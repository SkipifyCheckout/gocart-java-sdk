package com.gocart.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gocart.exceptions.GoCartException;

import java.io.IOException;
import java.util.Optional;

/**
 * Provides util methods for mapping strings to objects and objects to strings
 */
public final class JsonUtil {
    private static final String OBJECT_TO_STRING_ERROR_MESSAGE = "Could not map object to String";

    //convert a string to a dto
    public static <T> T fromJson(String json, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(json, clazz);
    }

    //convert a dto to a string
    public static Optional<String> toString(Object object) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return Optional.ofNullable(mapper.writeValueAsString(object));
        } catch (IOException e) {
            throw new GoCartException(OBJECT_TO_STRING_ERROR_MESSAGE, e);
        }
    }
}
