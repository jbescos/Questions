package com.example.general;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import jdk.jfr.Event;
import jdk.jfr.Name;
import jdk.jfr.consumer.RecordingStream;
import org.junit.Test;

public class JFRTest {

    @Test
    public void pinnedVirtualThreads() throws InterruptedException {
        CountDownLatch stopListening = new CountDownLatch(1);
        CountDownLatch startListening = new CountDownLatch(1);
        try (RecordingStream recordingStream = new RecordingStream()) {
            // Start the recording stream
            startRecordingStream(recordingStream, startListening, stopListening);
    
            // Make sure JFR listener is running before continue
            while (true) {
                new StartEvent().commit();
                if (startListening.await(1, TimeUnit.SECONDS)) {
                    break;
                }
            }
    
            // Simulate blocking operation
            Thread.ofVirtual().start(() -> {
                synchronized (this) {
                    System.out.println("Virtual Thread is pinned");
                    try {
                        // Ensure the block takes enough time to pin the thread
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }).join();
            // Flush pending events
            recordingStream.stop();
        }
        // Wait till the jdk.VirtualThreadPinned triggers
        assertTrue("jdk.VirtualThreadPinned was not sent", stopListening.await(10, TimeUnit.SECONDS));
    }
    
    private void startRecordingStream(RecordingStream recordingStream, CountDownLatch startListening, CountDownLatch stopListening) {
        // Enable the jdk.VirtualThreadPinned event
        recordingStream.enable("jdk.VirtualThreadPinned").withStackTrace();

        // Notify listener is running after receiving the StartEvent
        recordingStream.onEvent("StartEvent", event -> {
            System.out.println("Received " + event);
            startListening.countDown();
        });

        // Set an event handler
        recordingStream.onEvent("jdk.VirtualThreadPinned", event -> {
            System.out.println("VirtualThreadPinned event detected!");
            System.out.println("Timestamp: " + event);
            stopListening.countDown();
        });

        // Start the recording stream
        recordingStream.startAsync();
    }

    @Name("StartEvent")
    private static class StartEvent extends Event {
    }
}
