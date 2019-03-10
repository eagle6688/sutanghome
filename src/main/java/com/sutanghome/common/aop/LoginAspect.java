package com.sutanghome.common.aop;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sutanghome.cache.AccountCache;
import com.sutanghome.common.config.SystemConfig;
import com.sutanghome.common.utils.ProceedingJoinPointUtils;
import com.sutanghome.model.Account;
import com.sutanghome.service.SignService;

import devutility.external.json.JsonUtils;
import devutility.external.servlet.http.CookieUtils;
import devutility.external.servlet.http.HttpServletUtils;
import devutility.internal.annotations.PublicAction;
import devutility.internal.models.OperationResult;

@Aspect
@Order(2)
@Component
public class LoginAspect {
	@Autowired
	private AccountCache accountCache;

	@Autowired
	private SignService signService;

	@Around(value = "com.sutanghome.common.aop.Pointcuts.pointcutForAction()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		if (ProceedingJoinPointUtils.isAnnotation(joinPoint, PublicAction.class)) {
			return joinPoint.proceed();
		}

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

		String sessionId = CookieUtils.getValue(request, SystemConfig.COOKIE_JSESSIONID);
		String requestUrl = HttpServletUtils.getRequestUrl(request);

		if (StringUtils.isBlank(sessionId)) {
			CookieUtils.set(response, SystemConfig.COOKIE_JSESSIONID, UUID.randomUUID().toString(), null, "/", SystemConfig.COOKIE_JSESSIONID_EXPIRESECONDS);
			response.sendRedirect(requestUrl);
			return null;
		}

		Account account = accountCache.get(sessionId);

		if (account == null) {
			if (ProceedingJoinPointUtils.isAnnotation(joinPoint, ResponseBody.class)) {
				unauthorizedResponse(response);
				return null;
			}

			response.sendRedirect(SystemConfig.URL_LOGIN);
			return null;
		}

		signService.refreshCache(response, account);
		return joinPoint.proceed();
	}

	private void unauthorizedResponse(HttpServletResponse httpServletResponse) throws JsonProcessingException {
		OperationResult result = new OperationResult();
		result.setErrorMessage("Please login first");
		HttpServletUtils.response(httpServletResponse, JsonUtils.serialize(result), HttpServletResponse.SC_UNAUTHORIZED);
	}
}