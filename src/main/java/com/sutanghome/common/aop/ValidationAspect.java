package com.sutanghome.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import devutility.external.javax.validation.ValidationUtils;
import devutility.external.javax.validation.annotation.Validation;
import devutility.external.javax.validation.model.ValidationResult;
import devutility.internal.models.OperationResult;

@Aspect
@Order(3)
@Component
public class ValidationAspect {
	@Around(value = "com.sutanghome.common.aop.Pointcuts.pointCutForAction()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		Object[] args = joinPoint.getArgs();

		for (Object param : args) {
			if (param != null && param.getClass().isAnnotationPresent(Validation.class)) {
				ValidationResult validationResult = ValidationUtils.validate(param);

				if (validationResult.isFailed()) {
					OperationResult result = new OperationResult();
					result.setErrorMessage(validationResult.getFirstErrorMessage());
					return result;
				}
			}
		}

		return joinPoint.proceed();
	}
}