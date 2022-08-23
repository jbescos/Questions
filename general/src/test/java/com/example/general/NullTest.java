package com.example.general;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NullTest {

    @Test
    public void typeNull() {
        String str = null;
        assertEquals(false, check(str));
        str = "";
        assertEquals(true, check(str));
    }
    
    private boolean check(Object obj) {
        if (obj instanceof String) {
            return true;
        } else {
            return false;
        }
    }
}
