package com.example.general;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TryFinallyTest {

    private boolean flag;
    
    @Test
    public void withReturnExecutesFinally() {
        assertFalse(flag);
        withReturn();
        assertTrue(flag);
    }
    
    private void withReturn() {
        try {
            flag = false;
            return;
        } finally {
            flag = true;
        }
    }
    
}
