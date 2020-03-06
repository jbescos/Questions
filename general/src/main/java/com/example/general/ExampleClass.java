package com.example.general;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ExampleClass {

    private final int value = 300;

    public int getValue() {
        return value;
    } 
    
}
