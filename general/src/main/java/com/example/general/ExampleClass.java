package com.example.general;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ExampleClass {

    private final int value = 300;

    public int getValue() {
        return value;
    } 
    
}
