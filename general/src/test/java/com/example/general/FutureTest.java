package com.example.general;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
    
    @Test
    public void parallelAndWait() throws InterruptedException, ExecutionException {
        ExecutorService service = Executors.newFixedThreadPool(100);
        int requests = 1000;
        List<Future<String>> pendings = new ArrayList<>();
        for (int i= 0;i<requests;i++) {
            final int index = i;
            pendings.add(service.submit(() -> "test " + index));
        }
        for (Future<String> pending : pendings) {
            System.out.println(pending.get());
        }
    }
    
}
