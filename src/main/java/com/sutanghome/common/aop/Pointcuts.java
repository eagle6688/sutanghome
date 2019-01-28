package com.sutanghome.common.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {
	@Pointcut("execution(public * com.sutanghome.controller..*.*(..))")
	public void pointcutForAction() {

	}

	@Pointcut("execution(public * com.sutanghome.common.aop.ValidationAspect..*.*(..))")
	public void pointcutForValidationAspect() {

	}
}