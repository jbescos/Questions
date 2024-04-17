package com.github.jbescos.springboot;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/test")
public class RestResource {
    
    @Inject
    private Repository repository;
    
    @GET
    public String test() {
        return repository.test();
    }

}
