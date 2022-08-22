package com.example.general;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

public class YassonTest {

    private static final Jsonb JSONB = JsonbBuilder.create();

    @Test
    public void testSimpleSerialize() {
        StringWrapper wrapper = new StringWrapper();
        wrapper.setValue("abc");
        String val = JSONB.toJson(wrapper);
        assertEquals("{\"value\":\"abc\"}", val);
    }

    @Test
    public void testSimpleDeserializer() {
        StringWrapper stringWrapper = JSONB.fromJson("{\"value\":\"abc\"}", StringWrapper.class);
        assertEquals("abc", stringWrapper.value);
    }

    public static class StringWrapper {

        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
