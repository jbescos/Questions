package com.example.general;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

public class ResourcesTest {

    @Test
    public void test1() throws IOException {
        try (InputStream in = ResourcesTest.class.getResourceAsStream("test.txt")) {
            assertNull(in);
        }
    }

    @Test
    public void test2() throws IOException {
        try (InputStream in = ResourcesTest.class.getResourceAsStream("test2.txt")) {
            assertNotNull(in);
        }
    }

    @Test
    public void test3() throws IOException {
        try (InputStream in = ResourcesTest.class.getResourceAsStream("/test.txt")) {
            assertNotNull(in);
        }
    }
}
