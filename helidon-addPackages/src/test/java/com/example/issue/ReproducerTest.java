package com.example.issue;


import static org.junit.Assert.assertNotNull;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import org.junit.Test;


public class ReproducerTest {

    @Test
    public void packages() {
        try (SeContainer container = SeContainerInitializer
                .newInstance()
                .addPackages(true, Car.class)
                .initialize();) {
            Car car = container.select(Car.class).get();
            assertNotNull(car);
        }
    }

    @Test
    public void bean() {
        try (SeContainer container = SeContainerInitializer
                .newInstance()
                .addBeanClasses(Car.class)
                .initialize();) {
            Car car = container.select(Car.class).get();
            assertNotNull(car);
        }
    }
}
