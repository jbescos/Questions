package com.example;

import java.io.IOException;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.security.auth.Subject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.client.ClientConfig;

@Path("test")
public class MyResource {

    private static final String URL = "http://localhost:7001/weblogic-auth/test/response";
    private final Client client;
    
    public MyResource() {
        ClientConfig config = new ClientConfig();
        config.register(RequestClientFilter.class);
        this.client = ClientBuilder.newClient(config);
    }
    
    // curl --noproxy "*" http://weblogic:weblogic123@127.0.1.1:7001/weblogic-auth/test/sync
    @GET
    @Path("sync")
    public String sync() {
        printSubject("---- In /sync ----");
        Response response = client.target(URL).request().get();
        System.out.println("Response code is: " + response.getStatus());
        return response.readEntity(String.class);
    }
    
    // curl --noproxy "*" http://weblogic:weblogic123@127.0.1.1:7001/weblogic-auth/test/async
    @GET
    @Path("async")
    public String async() throws InterruptedException, ExecutionException {
        printSubject("---- In /async ----");
        Future<Response> future = client.target(URL).request().async().get();
        Response response = future.get();
        System.out.println("Response code is: " + response.getStatus());
        return response.readEntity(String.class);
    }
    
    // curl --noproxy "*" http://weblogic:weblogic123@127.0.1.1:7001/weblogic-auth/test/response
    @GET
    @Path("response")
    public String response() {
        printSubject("---- In /response ----");
        return "ok";
    }
    
    private static void printSubject(String message) {
        System.out.println(message);
        System.out.println("Thread: " + Thread.currentThread());
        AccessControlContext context = AccessController.getContext();
        System.out.println("AccessControlContext: " + context);
        Subject subject = Subject.getSubject(context);
        System.out.println("Subject: " + subject);
    }

    @Provider
    public static class RequestClientFilter implements ClientRequestFilter {

        @Override
        public void filter(ClientRequestContext requestContext) throws IOException {
            printSubject("---- In RequestClientFilter ----");
        }
    }
}
