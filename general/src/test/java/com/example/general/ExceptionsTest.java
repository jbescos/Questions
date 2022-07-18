package com.example.general;

import static org.junit.Assert.fail;

import org.junit.Test;

public class ExceptionsTest {

	@Test(expected = RuntimeException.class)
	public void catchException() {
		try {
			Object obj = null;
			obj.toString();
		} catch (NullPointerException e) {
			throw new RuntimeException(e);
		} catch (RuntimeException e) {
			// Do not catch the rethrown
			fail("Should not catch previous thrown");
		}
	}
	
	@Test
    public void printException() {
	    System.out.println(new Exception("test").toString());
	}
	
}
