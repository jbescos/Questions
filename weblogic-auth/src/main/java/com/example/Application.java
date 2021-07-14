package com.example;

import org.glassfish.jersey.server.ResourceConfig;

public class Application extends ResourceConfig {

    public Application() {
        packages(MyResource.class.getPackage().getName());
    }
}
