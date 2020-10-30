package com.example.general;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class NIOSocketTest {

    private final int PORT = 9000;
    private final int BUFFER_SIZE = 20;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();

    @Test
    public void example() throws IOException, InterruptedException {
        boolean running = true;
        try (ServerSocketChannel serverChannel = ServerSocketChannel.open()) {
            serverChannel.socket().bind(new InetSocketAddress(PORT));
            startClient();
            try (SocketChannel socketChannel = serverChannel.accept()) {
                socketChannel.configureBlocking(false);
                System.out.println("Connection Set:  " + socketChannel.getRemoteAddress());
                ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
                StringBuilder response = new StringBuilder();
                while (running) {
                    if (socketChannel.read(buffer) > 0) {
                        buffer.flip();
                        String read = new String(buffer.array(), StandardCharsets.UTF_8);
                        response.append(read);
                        System.out.println(read);
                        buffer.clear();
                        // FIXME Find way to detect when the request is completed. Currently it is not ready all the data.
                        if (response.toString().contains("\r\n\r\nn")) {
                            running = false;
                            socketChannel.write(ByteBuffer.wrap(new String("HTTP/1.1 200 OK\r\n\r\n").getBytes(StandardCharsets.UTF_8)));
                        }
                    }
                }
                executor.shutdown();
            }
        } finally {
            assertTrue(executor.awaitTermination(30, TimeUnit.SECONDS));
        }
    }

    private void startClient() {
        executor.execute(() -> {
            int code = sendPost(generateData(10));
            System.out.println("Returned code: " + code);
        });
    }

    private int sendPost(String data) {
        HttpRequest request = HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString(data))
                .uri(URI.create("http://localhost:" + PORT + "/example"))
                .header("Content-Type", "text/plain")
                .header("Transfer-Encoding", "chunked").build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return response.statusCode();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return -1;
        }
        
    }

    private String generateData(int lines) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < lines; i++) {
            builder.append("Line ").append(i);
        }
        builder.append("\r\n");
        return builder.toString();
    }

}
