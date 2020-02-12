package com.example;

import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.NameBinding;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    public static final String ATTRIBUTE = "test";

    @Auth
    @GET
    @Path("/auth")
    @Produces(MediaType.TEXT_PLAIN)
    public String auth(@Context HttpServletRequest request) {
        StringBuilder results = new StringBuilder();
        results.append(request.getAttribute(ATTRIBUTE));
        results.append(" auth");
        return results.toString();
    }
    
    @InnerAuth
    @GET
    @Path("/innerAuth")
    @Produces(MediaType.TEXT_PLAIN)
    public String innerAuth(@Context HttpServletRequest request) {
        StringBuilder results = new StringBuilder();
        results.append(request.getAttribute(ATTRIBUTE));
        results.append(" innerAuth");
        return results.toString();
    }
    
    @NameBinding
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE, ElementType.METHOD})
    public @interface InnerAuth {
    }
    
    @Provider
    @Priority(100)
    @InnerAuth
    public static class InnerAuthenticationFilter implements ContainerRequestFilter {

        @Override
        public void filter(ContainerRequestContext requestContext) throws IOException {
            List<String> obj = (List<String>) requestContext.getProperty(MyResource.ATTRIBUTE);
            if (obj == null) {
                obj = new ArrayList<>();
                requestContext.setProperty(MyResource.ATTRIBUTE, obj);
            }
            obj.add("InnerAuthenticationFilter working");
        }

    }
    
    @Provider
    @Priority(0)
    @InnerAuth
    public static class InnerAuthorizationFilter implements ContainerRequestFilter {

        @Override
        public void filter(ContainerRequestContext requestContext) throws IOException {
            List<String> obj = (List<String>) requestContext.getProperty(MyResource.ATTRIBUTE);
            if (obj == null) {
                obj = new ArrayList<>();
                requestContext.setProperty(MyResource.ATTRIBUTE, obj);
            }
            obj.add("InnerAuthorizationFilter working");
        }

    }
    
}
