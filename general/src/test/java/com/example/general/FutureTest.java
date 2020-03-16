package com.example.general;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

public class FutureTest {

    @Test
    public void exception() throws InterruptedException {
        Future<Integer> result = Executors.newSingleThreadExecutor().submit(() -> 1/0);
        try {
            result.get();
        } catch (ExecutionException e) {
            assertEquals(ArithmeticException.class, e.getCause().getClass());
        }
    }
    
}
