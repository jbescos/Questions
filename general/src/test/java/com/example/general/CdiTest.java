package com.example.general;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

import org.junit.Test;

public class CdiTest {

    @Test
    public void checkBean() {
        SeContainerInitializer cdiInitializer = SeContainerInitializer.newInstance()
                .disableDiscovery()
                .addBeanClasses(ExampleClass.class);
        SeContainer container = cdiInitializer.initialize();
        ExampleClass example = container.select(ExampleClass.class).get();
        assertNotNull(example);
        assertEquals(300, example.getValue());
        assertEquals(example, container.select(ExampleClass.class).get());
        container.close();
    }
    
}
