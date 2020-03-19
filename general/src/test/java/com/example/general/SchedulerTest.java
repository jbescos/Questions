package com.example.general;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

public class SchedulerTest {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(100);
    private final AtomicInteger counter = new AtomicInteger(0);
    
    @Test
    public void noOverlap() throws InterruptedException {
        scheduler.scheduleAtFixedRate(() -> {
            try {
                counter.incrementAndGet();
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }, 0, 1, TimeUnit.NANOSECONDS);
        scheduler.awaitTermination(1000, TimeUnit.MILLISECONDS);
        int value = counter.get();
        System.out.println("Counter value " + value);
        assertTrue("Counter is " + value, value <= 20);
    }
    
}
