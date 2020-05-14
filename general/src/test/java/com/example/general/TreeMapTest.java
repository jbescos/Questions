package com.example.general;

import static org.junit.Assert.assertTrue;

import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

public class TreeMapTest {
    
    @Test
    public void values() {
        Map<String, String> map = new TreeMap<>();
        map.put("key", "value");
        assertTrue(map.values() == map.values());
    }
}
