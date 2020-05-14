package com.example.general;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

public class StreamsTest {

    @Test
    public void count() {
        Map<String, List<String>> map = new HashMap<>();
        map.put("1", IntStream.range(0, 2000).mapToObj(i -> Integer.toString(i)).collect(Collectors.toList()));
        map.put("2", IntStream.range(0, 2).mapToObj(i -> Integer.toString(i)).collect(Collectors.toList()));
        assertEquals(2002, currentNoAck(map.values()));
    }
    
    @Test
    public void empty() {
        Map<String, List<String>> map = new HashMap<>();
        assertEquals(0, currentNoAck(map.values()));
    }
    
    private int currentNoAck(Collection<List<String>> collection) {
        return collection.stream().map(list -> list.size()).reduce((a, b) -> a + b).orElse(0);
    }
    
}
