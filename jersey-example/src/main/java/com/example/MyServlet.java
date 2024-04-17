package com.example;

import org.glassfish.jersey.server.ResourceConfig;

public class MyServlet extends org.glassfish.jersey.servlet.ServletContainer {

    public MyServlet() {
        super();
        printClassLoader("com.fasterxml.jackson.databind.Module");
        printClassLoader("com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule");
    }

    public MyServlet(ResourceConfig resourceConfig) {
        super(resourceConfig);
        printClassLoader("com.fasterxml.jackson.databind.Module");
        printClassLoader("com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule");
    }

    private void printClassLoader(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            System.out.println(className + ": " + clazz.getClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
}
