package com.example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.glassfish.jersey.server.internal.monitoring.core.ReservoirConstants;

@Path("test")
public class MyResource {

    @GET
    public int collisionBuffer() {
        return ReservoirConstants.COLLISION_BUFFER_POWER;
    }

}
