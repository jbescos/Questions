package com.example.general;

import java.io.Closeable;
import java.io.IOException;
import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PhantomRefTest {
    
    private final List<String> deletedInOrder = Collections.synchronizedList(new ArrayList<>());

    @Before
    public void before() {
        deletedInOrder.clear();
    }
    
    @Test
    public void verify() throws InterruptedException {
        Object obj = new String("original");
        CloserTask task = new CloserTask(obj);
        Object other1 = new Object();
        PhantomReference<Object> refOther1 = task.create(() -> System.out.println("Closing other1"), other1);
        Object other2 = new Object();
        PhantomReference<Object> refOther2 = task.create(() -> System.out.println("Closing other2"), other2);
        other1 = null;
        other2 = null;
        obj = null;
        System.gc();
        Thread.sleep(1000L);
        assertEquals(Arrays.asList("PhantomRefCloseableWithRef", "PhantomRefCloseableWithRef"), deletedInOrder);
        refOther1 = null;
        refOther2 = null;
        System.gc();
        Thread.sleep(1000L);
        assertEquals(Arrays.asList("PhantomRefCloseableWithRef", "PhantomRefCloseableWithRef", "PhantomRefCloseable"), deletedInOrder);
    }
    
    private class CloserTask {

        private final ReferenceQueue<Object> queue = new ReferenceQueue<>();
        private final PhantomReference<Object> phantomRef;
        private final WeakReference<Object> weakRef;
        private final ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        
        public CloserTask(Object obj) {
            ScheduledFuture<?> task = this.ses.scheduleAtFixedRate(() -> {
                Reference<?> ref = null;
                while ((ref = queue.poll()) != null) {
                    System.out.println("Garbage collected: " + ref.getClass().getName());
                    try {
                        ((Closeable)ref).close();
                        deletedInOrder.add(ref.getClass().getSimpleName());
                    } catch (Exception e) {
                        e.printStackTrace();
                        deletedInOrder.add(e.getClass().getSimpleName());
                    }
                }
            }, 0, 25, TimeUnit.MILLISECONDS);
            this.weakRef = new WeakReference<>(obj);
            this.phantomRef = new PhantomRefCloseable<>(obj, queue, () -> {
                System.out.println("Cancelling task");
                task.cancel(false);
            });
        }
        
        public PhantomReference<Object> create(Closeable closeable, Object other){
            PhantomRefCloseableWithRef<Object> phantom = new PhantomRefCloseableWithRef<>(other, queue, closeable, weakRef);
            return phantom;
        }
    }
    
    private static class PhantomRefCloseable<T> extends PhantomReference<T> implements Closeable {

        private final Closeable closeable;
        
        public PhantomRefCloseable(T referent, ReferenceQueue<? super T> q, Closeable closeable) {
            super(referent, q);
            this.closeable = closeable;
        }

        @Override
        public void close() throws IOException {
            System.out.println("Closing " + closeable);
            closeable.close();
        } 
    }
    
    private static class PhantomRefCloseableWithRef<T> extends PhantomRefCloseable<T> {
        
        private final Object obj;

        public PhantomRefCloseableWithRef(T referent, ReferenceQueue<? super T> q, Closeable closeable, Reference<Object> obj) {
            super(referent, q, closeable);
            this.obj = obj.get();
        }
        
    }
    
}
