package com.sutanghome.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(3)
@Component
public class ValidationAspect {
	@Around(value = "com.sutanghome.common.aop.Pointcuts.pointCutForAction()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		return null;
	}
}