package com.example.demo.controller;

import com.example.demo.aspect.MyAspect;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

@Path("/test")
@Component
public class MyResource {
    
    @GET
    @Produces("application/json")
    @MyAspect
    public Response get() {
        return Response.ok("Response").build();
    }
}