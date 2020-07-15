package com.example.general;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class CompletableFutureTest {

    @Test
    public void runAync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread() + ": supplyAsync invoked countDown");
            latch.countDown();
        });
        latch.await(1000, TimeUnit.MILLISECONDS);
    }

    @Test
    public void supplyAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + ": supplyAsync invoked countDown");
            latch.countDown();
            return null;
        });
        latch.await(1000, TimeUnit.MILLISECONDS);
    }

}
