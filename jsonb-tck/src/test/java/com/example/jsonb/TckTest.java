package com.example.jsonb;

import static org.junit.Assert.assertTrue;

import javax.json.bind.JsonbBuilder;

import org.junit.Test;

public class TckTest {
    
    @Test
    public void testJsonbDateFormat() {
        String jsonString = JsonbBuilder.create().toJson(new SimpleAnnotatedDateContainer());
        String regExp = "\\{\\s*\"instance\"\\s*:\\s*\"Do, 01 Jan 1970\"\\s*}";
        assertTrue(jsonString + " doesn't match with " + regExp, jsonString.matches("\\{\\s*\"instance\"\\s*:\\s*\"Do, 01 Jan 1970\"\\s*}"));
    }
}