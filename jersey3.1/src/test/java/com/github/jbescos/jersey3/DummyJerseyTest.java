package com.github.jbescos.jersey3;

import jakarta.ws.rs.core.Application;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DummyJerseyTest extends JerseyTest {

    @Override
    protected Application configure() {
        var app = new ResourceConfig();
        app.register(new Object());
        return app;
    }

    @Test
    void myTest() {
        // NOOP
    }
}