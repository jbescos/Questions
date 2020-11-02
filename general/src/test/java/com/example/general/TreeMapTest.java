package com.example.general;

import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TreeMapTest {
    
    @Test
    public void values() {
        Map<String, String> map = new TreeMap<>();
        map.put("key", "value");
        assertTrue(map.values() == map.values());
    }
}
