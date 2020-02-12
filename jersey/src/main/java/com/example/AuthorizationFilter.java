package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

@Provider
@Priority(0)
@Auth
public class AuthorizationFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        List<String> obj = (List<String>) requestContext.getProperty(MyResource.ATTRIBUTE);
        if (obj == null) {
            obj = new ArrayList<>();
            requestContext.setProperty(MyResource.ATTRIBUTE, obj);
        }
        obj.add("AuthorizationFilter working");
    }

}