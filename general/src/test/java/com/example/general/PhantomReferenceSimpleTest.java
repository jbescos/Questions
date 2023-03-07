package com.example.general;

import static org.junit.Assert.assertEquals;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

import org.junit.Test;

public class PhantomReferenceSimpleTest {

    @Test
    public void verify() throws InterruptedException {
        Object obj = new String("original");
        ReferenceQueue<Object> rq = new ReferenceQueue<>();
        PhantomReference<Object> phantomRef = new PhantomReference<>(obj, rq);
        assertEquals(null, rq.poll());
        obj = null;
        System.gc();
        Thread.sleep(1000);
        assertEquals(phantomRef, rq.poll());
    }
}
