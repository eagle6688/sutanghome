package com.sutanghome.common.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Order(2)
@Component
public class LoginAspect {
	@Around(value = "com.sutanghome.common.aop.Pointcuts.pointcutForAction()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();

		if (session == null) {
			System.out.println("No session!");
			return joinPoint.proceed();
		}

		System.out.println(session.getId());
		return joinPoint.proceed();
	}
}