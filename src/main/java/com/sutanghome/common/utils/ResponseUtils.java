package com.sutanghome.common.utils;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;

import devutility.external.json.JsonUtils;
import devutility.external.servlet.http.HttpServletUtils;
import devutility.internal.models.OperationResult;

public class ResponseUtils {
	public static void unauthorizedResponse(HttpServletResponse response) throws JsonProcessingException {
		OperationResult result = new OperationResult();
		result.setErrorMessage("请先登录！");
		HttpServletUtils.unauthorizedResponse(response, JsonUtils.serialize(result));
	}

	public static void forbiddenResponse(HttpServletResponse response) throws JsonProcessingException {
		OperationResult result = new OperationResult();
		result.setErrorMessage("您的权限不足！");
		HttpServletUtils.forbiddenResponse(response, JsonUtils.serialize(result));
	}
}