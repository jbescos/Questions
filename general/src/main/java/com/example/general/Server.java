package com.example.general;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;

public class Server {

    private static final Logger LOGGER = Logger.getLogger(Server.class.getName());
    private static final int BUFFER_SIZE = 10;
    public static final int PORT = 5454;
    private static final String NEW_LINE = "\r\n";
    private static final String END_REQUEST = NEW_LINE + NEW_LINE;
    private ExecutorService executor;
    private AtomicBoolean running;

    public void start() throws InterruptedException {
        running = new AtomicBoolean(true);
        final CountDownLatch latch = new CountDownLatch(1);
        executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            Map<String, ClientContent> currentRequests = new HashMap<>();
            try (Selector selector = Selector.open(); ServerSocketChannel serverSocket = ServerSocketChannel.open();) {
                serverSocket.bind(new InetSocketAddress(PORT));
                serverSocket.configureBlocking(false);
                serverSocket.register(selector, SelectionKey.OP_ACCEPT);
                ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
                while (running.get()) {
                    latch.countDown();
                    if (selector.select() > 0) {
                        Set<SelectionKey> selectedKeys = selector.selectedKeys();
                        Iterator<SelectionKey> iter = selectedKeys.iterator();
                        while (iter.hasNext()) {
                            SelectionKey key = iter.next();
                            if (key.isAcceptable()) {
                                register(selector, serverSocket);
                            }
                            if (key.isReadable()) {
                                read(buffer, key, currentRequests);
                            }
                            iter.remove();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                stop();
            }
        });
        latch.await();
    }

    private void read(ByteBuffer buffer, SelectionKey key, Map<String, ClientContent> currentRequests) throws IOException {
        ClientContent content;
        if (key.attachment() == null) {
            String id = UUID.randomUUID().toString();
            key.attach(id);
            content = new ClientContent();
            currentRequests.put(id, content);
        } else {
            content = currentRequests.get(key.attachment());
        }
        SocketChannel socketChannel = (SocketChannel) key.channel();
        if (socketChannel.read(buffer) > 0) {
            buffer.flip();
            String read = new String(buffer.array(), StandardCharsets.UTF_8);
            LOGGER.info("Read: " + read);
            buffer.clear();
            content.data.append(read);
            if (content.data.lastIndexOf(END_REQUEST) > -1) {
                String[] headersBody = content.data.toString().split(END_REQUEST);
                String[] lines = headersBody[0].split(NEW_LINE);
                // First line is ignored, it contains the method
                content.request.setRequest(lines[0]);
                for (int i = 1; i < lines.length; i++) {
                    String[] pair = lines[i].split(": ", 2);
                    content.request.getHeaders().put(pair[0], pair[1]);
                }
                content.data = new StringBuilder(headersBody[1]);
                if (!content.request.getHeaders().containsKey("Transfer-Encoding")) {
                    content.finished = true;
                }
            }
            if (content.request.getHeaders().containsKey("Content-Length")) {
                int length = Integer.parseInt(content.request.getHeaders().get("Content-Length"));
                if (content.data.length() >= length) {
                    // It is reading data that was already read for some reason.
                    content.data.delete(length, content.data.length());
                    content.request.setBody(content.data.toString());
                    content.finished = true;
                }
            }
            if (content.finished) {
                LOGGER.info("Request " + key.attachment() + " has finished");
                currentRequests.remove(key.attachment());
                socketChannel
                        .write(ByteBuffer.wrap(new String("HTTP/1.1 200 OK\r\n\r\n").getBytes(StandardCharsets.UTF_8)));
                socketChannel.close();
            }
        }
    }

    private void register(Selector selector, ServerSocketChannel serverSocket) throws IOException {
        SocketChannel client = serverSocket.accept();
        client.configureBlocking(false);
        client.register(selector, SelectionKey.OP_READ);
        LOGGER.info("Client registered: " + client.getRemoteAddress());
    }

    public void stop() {
        running.set(false);
        executor.shutdown();
        LOGGER.info("Server stopped");
    }
    
    private static class ClientContent {
        private final Request request = new Request();
        private StringBuilder data = new StringBuilder();
        private boolean finished = false;
    }

}
