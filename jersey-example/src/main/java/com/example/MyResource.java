package com.example;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("test")
public class MyResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> get() {
        Map<String, String> json = new HashMap<>();
        json.put("message", "It works");
        printClassLoader(json, "com.fasterxml.jackson.databind.Module");
        printClassLoader(json, "com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule");
        return json;
    }

    private void printClassLoader(Map<String, String> json, String className) {
        try {
            Class<?> clazz = Class.forName(className);
            json.put(className, clazz.getClassLoader().toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
