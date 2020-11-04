package com.example.general;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Logger;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SelectorTest {

    private static final Logger LOGGER = Logger.getLogger(SelectorTest.class.getName());
    private final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();

    @Test
    public void example() throws InterruptedException {
        Server server = new Server();
        server.start();
        int response = sendPost(generateData(10));
        assertEquals(200, response);
        server.stop();
    }

    private int sendPost(String data) {
        LOGGER.info("Sending: " + data);
        HttpRequest request = HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString(data))
                .uri(URI.create("http://localhost:" + Server.PORT + "/example")).header("Content-Type", "text/plain")
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
