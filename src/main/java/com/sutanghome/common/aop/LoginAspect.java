package com.sutanghome.common.aop;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sutanghome.common.config.SystemConfig;

import devutility.external.servlet.http.CookieUtils;
import devutility.external.servlet.http.HttpServletUtils;

@Aspect
@Order(2)
@Component
public class LoginAspect {
	@Around(value = "com.sutanghome.common.aop.Pointcuts.pointcutForAction()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
		String sessionId = CookieUtils.getValue(request, SystemConfig.COOKIE_JSESSIONID);

		if (StringUtils.isBlank(sessionId)) {
			sessionId = UUID.randomUUID().toString();
			CookieUtils.set(response, SystemConfig.COOKIE_JSESSIONID, sessionId, null, "/", SystemConfig.COOKIE_JSESSIONID_EXPIRESECONDS);
			response.sendRedirect(HttpServletUtils.getRequestUrl(request));
			return null;
		}

		return joinPoint.proceed();
	}
}