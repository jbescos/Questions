package com.example.general;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class SocketTest {
    
    private static final int PORT = 13333;
    private static final int TIMEOUT = 100;
    private static final String CLIENT_MESSAGE = "hello client";
    private static final String SERVER_MESSAGE = "hello server";

    @Test
    public void socketClientReadTimeout() throws IOException, ClassNotFoundException, InterruptedException, ExecutionException {
        try(ServerSocket server = new ServerSocket(PORT)) {
            ExecutorService service = Executors.newSingleThreadExecutor();
            Future<?> future = service.submit(() -> {
                try (Socket client = new Socket()) {
                    client.setSoTimeout(TIMEOUT);
                    client.connect(new InetSocketAddress("localhost", PORT));
                    ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
                    oos.writeObject(CLIENT_MESSAGE);
                    ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
                    // Read will timeout because server is not writing
                    ois.readObject();
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println("Client -> " + e.getMessage());
                }
            });
            try (Socket socket = server.accept() ){
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                String message = (String) ois.readObject();
                assertEquals(CLIENT_MESSAGE, message);
                // Wait till client times out
                future.get();
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(SERVER_MESSAGE);
                fail("Unreachable");
            } catch (IOException e) {
                assertEquals(SocketException.class, e.getClass());
            }
        }
    }
    
}
