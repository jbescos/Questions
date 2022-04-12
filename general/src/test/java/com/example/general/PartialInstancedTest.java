package com.example.general;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Test;

public class PartialInstancedTest {

	@Test
	public void test() {
		try {
			new MyObj();
			fail("Should fail");
		} catch (MyException e) {
			assertEquals("val1", e.val1);
			assertNull(e.val2);
			assertEquals(0, e.val3);
			assertNull(e.val4);
		}
	}
	
	private static class MyObj {
		
		private final String val1;
		private final Integer val2;
		private final int val3;
		private final Object val4;
		
		public MyObj() {
			val1 = "val1";
			val2 = parse(null);
			val3 = 4;
			val4 = new Object();
		}
		
		private int parse(String value) {
			try {
				return Integer.parseInt(value);
			} catch (NumberFormatException e) {
				throw new MyException(val1, val2, val3, val4);
			}
		}
	}
	
	private static class MyException extends RuntimeException {
		private static final long serialVersionUID = 1L;
		private final String val1;
		private final Integer val2;
		private final int val3;
		private final Object val4;
		public MyException(String val1, Integer val2, int val3, Object val4) {
			this.val1 = val1;
			this.val2 = val2;
			this.val3 = val3;
			this.val4 = val4;
		}
	}
}
