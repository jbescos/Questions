package com.example.general;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Request {

    private String request;
    private String body;
    private Map<String, String> headers = new HashMap<>();

    public String getRequest() {
        return request;
    }
    public void setRequest(String request) {
        this.request = request;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public Map<String, String> getHeaders() {
        return headers;
    }
    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
    @Override
    public String toString() {
        StringBuilder content = new StringBuilder(request).append("\n");
        for (Entry<String, String> entry : headers.entrySet()) {
            content.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        content.append(body);
        return content.toString();
    }
    
}
