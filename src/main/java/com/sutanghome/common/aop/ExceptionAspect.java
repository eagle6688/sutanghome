package com.sutanghome.common.aop;

import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;

import devutility.external.json.JsonUtils;
import devutility.external.servlet.http.HttpServletUtils;
import devutility.internal.lang.ExceptionUtils;
import devutility.internal.models.OperationResult;

@Aspect
@Order(1)
@Component
public class ExceptionAspect {
	@AfterThrowing(pointcut = "com.sutanghome.common.aop.Pointcuts.pointcutForAction() || com.sutanghome.common.aop.Pointcuts.pointcutForValidationAspect()", throwing = "exception")
	public void doException(JoinPoint joinPoint, Exception exception) {
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
		OperationResult result = new OperationResult();
		result.setErrorMessage(exception.getMessage());
		result.setData(ExceptionUtils.toString(exception));

		try {
			HttpServletUtils.response(response, JsonUtils.serialize(result));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}