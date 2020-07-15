package com.example.general;

import org.junit.Test;

public class GarbageCollectorTest {

    @Test
    public void threadFinalize() throws InterruptedException {
        new Finalized();
        System.gc();
        Thread.sleep(1000);
    }
    
    private static class Finalized {

        public Finalized() {
            System.out.println("Created by thread " + Thread.currentThread());
        }
        
        @Override
        protected void finalize() throws Throwable {
            System.out.println("Finalized by thread " + Thread.currentThread());
        }
        
    }
    
}
