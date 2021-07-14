package com.example;

import java.io.IOException;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.Principal;
import java.security.PrivilegedAction;
import java.util.Arrays;
import java.util.Base64;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Supplier;

import javax.inject.Inject;
import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.spi.ExecutorServiceProvider;
import org.glassfish.jersey.spi.ThreadPoolExecutorProvider;

@Path("test")
public class MyResource {

    public static final String REPONSE_PATH = "response";
    private static final String SUBJECT_PARAM = "subject";
    private static final String URL = "http://localhost:7001/weblogic-auth/test/" + REPONSE_PATH;
    private static final ThreadPoolExecutorProvider PROVIDER = new ThreadPoolExecutorProvider("jersey-server-managed-async-executor") {};
    private final Client client;
    
    @Inject
    public MyResource(ExecutorServiceProvider provider) {
        ExecutorService executorService = provider.getExecutorService();
        System.out.println("ExecutorServiceProvider is " + provider + " and provided " + executorService);
        ClientConfig config = new ClientConfig();
        config.register(RequestClientFilter.class);
        config.executorService(executorService);
        this.client = ClientBuilder.newClient(config);
    }
    
    // curl --noproxy "*" http://weblogic:weblogic123@localhost:7001/weblogic-auth/test/sync
    @GET
    @Path("sync")
    public String sync(@Context HttpServletRequest request) {
        printSubject("---- In /sync ----");
        return request(request, () -> client.target(URL).request().get().readEntity(String.class));
    }
    
    // curl --noproxy "*" http://weblogic:weblogic123@localhost:7001/weblogic-auth/test/async
    @GET
    @Path("async")
    public String async(@Context HttpServletRequest request) throws InterruptedException, ExecutionException {
        printSubject("---- In /async ----");
        return request(request, () -> {
            try {
                Builder builder = client.target(URL).request();
                AsyncInvoker invoker = builder.async();
                Future<Response> future = invoker.get();
                return future.get().readEntity(String.class);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
    
    // curl --noproxy "*" http://weblogic:weblogic123@localhost:7001/weblogic-auth/test/async2
    @GET
    @Path("async2")
    public String async2(@Context HttpServletRequest request) throws InterruptedException, ExecutionException {
        printSubject("---- In /async2 ----");
        return request(request, () -> {
            try {
                String response = Executors.newSingleThreadExecutor().submit(() -> client.target(URL).request().get().readEntity(String.class)).get();
                return response;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
    
    // curl --noproxy "*" http://weblogic:weblogic123@localhost:7001/weblogic-auth/test/async3
    @GET
    @Path("async3")
    public String async3(@Context HttpServletRequest request) throws InterruptedException, ExecutionException {
        printSubject("---- In /async3 ----");
        Subject subject = new Subject();
        subject.getPrincipals().add(request.getUserPrincipal());
        return client.target(URL).request().property(SUBJECT_PARAM, subject).async().get().get().readEntity(String.class);
    }
    
    // curl --noproxy "*" http://weblogic:weblogic123@localhost:7001/weblogic-auth/test/response
    @GET
    @Path(REPONSE_PATH)
    public String response(@Context HttpServletRequest request) {
        printSubject("---- In /response ----");
        return request.getUserPrincipal().getName();
    }
    
    private String request(HttpServletRequest request, Supplier<String> supplier) {
        Subject subject = new Subject();
        subject.getPrincipals().add(request.getUserPrincipal());
        return Subject.doAs(subject, new PrivilegedAction<String>(){    
            @Override
            public String run() {
                return supplier.get();
            }
        });
    }

    public static void printSubject(String message) {
        System.out.println(message);
        AccessControlContext context = AccessController.getContext();
        System.out.println("Thread: " + Thread.currentThread() + "AccessControlContext: " + context + ", DomainCombiner: " + context.getDomainCombiner() + ", Subject: " + Subject.getSubject(context));
    }

    @Provider
    public static class RequestClientFilter implements ClientRequestFilter {

        @Override
        public void filter(ClientRequestContext requestContext) throws IOException {
            printSubject("---- In RequestClientFilter ----");
            Subject subject = (Subject) requestContext.getProperty(SUBJECT_PARAM);
            if (subject == null) {
                AccessControlContext context = AccessController.getContext();
                subject = Subject.getSubject(context);
                if (subject == null) {
                    subject = weblogic.security.Security.getCurrentSubject();
                    if (subject == null) {
                        throw new IllegalArgumentException("Subject is null");
                    }
                }
            }
            Principal principal = subject.getPrincipals().iterator().next();
            String encode = principal.getName() + ":" + "weblogic123";
            String encoded = new String(Base64.getEncoder().encode(encode.getBytes()));
            System.out.println(encoded);
            requestContext.getHeaders().put("Authorization", Arrays.asList("Basic " + encoded));
        }
    }
}
