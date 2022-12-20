package com.clevertec.cashregister.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;

public class TestMapper {

    public static Object map(MvcResult mvcResult) {
        try {
            return new ObjectMapper().readValue(
                    mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
                    }
            );
        } catch (JsonProcessingException | UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String map(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }
}
