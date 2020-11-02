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
import java.util.logging.Logger;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class NIOSocketTest {

    private static final Logger LOGGER = Logger.getLogger(NIOSocketTest.class.getName());
    private static final int PORT = 9000;
    private static final int BUFFER_SIZE = 10;
    private static final String NEW_LINE = "\r\n";
    private static final String END_REQUEST = NEW_LINE + NEW_LINE;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();

    @Test
    public void example() throws IOException, InterruptedException {
        boolean running = true;
        try (ServerSocketChannel serverChannel = ServerSocketChannel.open()) {
            serverChannel.socket().bind(new InetSocketAddress(PORT));
            startClient();
            try (SocketChannel socketChannel = serverChannel.accept()) {
                socketChannel.configureBlocking(false);
                LOGGER.info("Connection Set:  " + socketChannel.getRemoteAddress());
                ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
                Request request = new Request();
                StringBuilder data = new StringBuilder();
                while (running) {
                    if (socketChannel.read(buffer) > 0) {
                        buffer.flip();
                        String read = new String(buffer.array(), StandardCharsets.UTF_8);
                        buffer.clear();
                        LOGGER.info(read);
                        data.append(read);
                        if (data.lastIndexOf(END_REQUEST) > -1) {
                            String[] headersBody = data.toString().split(END_REQUEST);
                            String[] lines = headersBody[0].split(NEW_LINE);
                            // First line is ignored, it contains the method
                            request.setRequest(lines[0]);
                            for (int i = 1; i < lines.length; i++) {
                                LOGGER.info("Adding header: " + lines[i]);
                                String[] pair = lines[i].split(": ", 2);
                                request.getHeaders().put(pair[0], pair[1]);
                            }
                            data = new StringBuilder(headersBody[1]);
                            if (!request.getHeaders().containsKey("Transfer-Encoding")) {
                                running = false;
                            }
                        }
                        if (request.getHeaders().containsKey("Content-Length")) {
                            int length = Integer.parseInt(request.getHeaders().get("Content-Length"));
                            if (data.length() >= length) {
                                // It is reading data that was already read for some reason.
                                data.delete(length, data.length());
                                request.setBody(data.toString());
                                running = false;
                            }
                        }
                        if (!running) {
                            socketChannel.write(ByteBuffer
                                    .wrap(new String("HTTP/1.1 200 OK\r\n\r\n").getBytes(StandardCharsets.UTF_8)));
                        }
                    }
                }
                executor.shutdown();
                LOGGER.info("Request: " + request);
            }
        } finally {
            assertTrue(executor.awaitTermination(30, TimeUnit.SECONDS));
        }
    }

    private void startClient() {
        executor.execute(() -> {
            int code = sendPost(generateData(10));
            LOGGER.info("Returned code: " + code);
        });
    }

    private int sendPost(String data) {
        LOGGER.info("Sending: " + data);
        HttpRequest request = HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString(data))
                .uri(URI.create("http://localhost:" + PORT + "/example")).header("Content-Type", "text/plain")
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
        return builder.toString();
    }

}
