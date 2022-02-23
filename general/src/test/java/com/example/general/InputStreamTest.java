package com.example.general;

import static org.junit.Assert.assertEquals;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.junit.Test;

public class InputStreamTest {

    @Test
    public void readTwiceWrong() throws IOException {
        InputStream in = InputStreamTest.class.getResourceAsStream("/test.txt");
        String contents = new String(in.readAllBytes(), StandardCharsets.UTF_8);
        assertEquals("test", contents);
        contents = new String(in.readAllBytes(), StandardCharsets.UTF_8);
        assertEquals("", contents);
    }
    
    @Test
    public void readTwice() throws IOException {
        InputStream in = InputStreamTest.class.getResourceAsStream("/test.txt");
        in = read(in);
        in = read(in);
    }
    
    private InputStream read(InputStream in) throws IOException {
        byte[] bytes = in.readAllBytes();
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        String contents = new String(bais.readAllBytes(), StandardCharsets.UTF_8);
        assertEquals("test", contents);
        return new ByteArrayInputStream(bytes);
    }
}
