package com.example.general;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.Test;

public class AnnotationsTest {

	private static final String DEFAULT_PROVIDER = DefaultProvider.class.getName();
	
	@Test
	public void containsAnnotation() {
		assertTrue(containsDefaultProvider(Example.class));
		assertFalse(containsDefaultProvider(AnnotationsTest.class));
	}

    private boolean containsDefaultProvider(Class<?> clazz) {
    	Annotation[] annotations = clazz.getDeclaredAnnotations();
    	for (Annotation annotation : annotations) {
    		if (DEFAULT_PROVIDER.equals(annotation.annotationType().getName())) {
    			return true;
    		}
    	}
    	return false;
    }

	@DefaultProvider
	private static class Example {}
	
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	public static @interface DefaultProvider {
	}
}
