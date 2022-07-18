package com.example.general;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StringsTest {

    @Test
    public void startsWith() {
        String test = "jdk.xml.maxGeneralEntitySizeLimit";
        assertTrue(test.startsWith("jdk.xml."));
    }
}
