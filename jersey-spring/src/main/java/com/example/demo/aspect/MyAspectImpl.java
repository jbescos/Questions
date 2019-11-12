package com.example.demo.aspect;

import javax.ws.rs.core.Response;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspectImpl {
    
    @Around("@annotation(MyAspect)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        joinPoint.proceed();
        return Response.ok("@MyAspect modified the response").build();
    }
    
}
