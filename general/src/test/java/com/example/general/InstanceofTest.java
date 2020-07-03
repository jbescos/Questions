package com.example.general;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class InstanceofTest {

    @Test
    public void hierarchy() {
        B b = new B();
        assertTrue(b instanceof A);
        assertTrue(b instanceof B);
        A a = new A();
        assertTrue(a instanceof A);
        assertFalse(a instanceof B);
    }
    
    private static class A {}
    private static class B extends A {}
}
