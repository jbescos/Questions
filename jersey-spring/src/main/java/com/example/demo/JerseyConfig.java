package com.example.demo;

import com.example.demo.controller.MyResource;

import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {
    
    private final static Logger log = LoggerFactory.getLogger(JerseyConfig.class);
    
    public JerseyConfig() {
        register(MyResource.class);
        log.info("JerseyConfig loaded");
    }
    
}
