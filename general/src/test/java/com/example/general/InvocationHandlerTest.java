package com.example.general;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.function.Supplier;

import org.junit.Test;

public class InvocationHandlerTest {
	
	@Test
	public void test() {
		Supplier<Integer> userObject = (Supplier<Integer>) ProxyFactory.newInstance(new UserObject());
		assertEquals(-1, userObject.get().intValue());
		assertEquals(-2, userObject.get().intValue());
		Supplier<Integer> userObject2 = (Supplier<Integer>) ProxyFactory.newInstance(new UserObject());
		assertNotEquals(userObject, userObject2);
	}

	public static class NegativeNumberHandler implements InvocationHandler {

		private final Object target;
		
		public NegativeNumberHandler(Object target) {
			this.target = target;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			Object result = method.invoke(target, args);
			if (result instanceof Number) {
				Number number = (Number) result;
				int value = number.intValue();
				return value * -1;
			}
			return result;
		}
		
	}
	
	public static class ProxyFactory {
		public static Object newInstance(Object obj) {
			return Proxy.newProxyInstance(obj.getClass().getClassLoader(), new Class<?>[] { Supplier.class }, new NegativeNumberHandler(obj));
		}
	}
	
	public static class UserObject implements Supplier<Integer> {
		
		private int value = 0;

		@Override
		public Integer get() {
			value++;
			return value;
		}
	}
}
