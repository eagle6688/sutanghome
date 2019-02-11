package com.sutanghome.model.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sutanghome.dao.entities.User;

import devutility.external.javax.validation.annotation.PatternIfNotNull;
import devutility.external.javax.validation.annotation.SizeIfNotNull;
import devutility.external.javax.validation.annotation.Validation;
import devutility.internal.security.SHA256Utils;

@Validation
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddUserParam {
	@NotNull(message = "请输入姓名！")
	@Size(max = 10, message = "姓名长度不能超过10个字符！")
	private String name;

	@PatternIfNotNull(regexp = "^1(3|4|5|7|8)\\d{9}$", message = "请输入正确格式的手机号码！")
	private String cellphone;

	@SizeIfNotNull(min = 6, max = 36, message = "请输入6-36位密码！")
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User toCountEntity() {
		User entity = new User();
		entity.setName(name);
		entity.setCellphone(cellphone);
		return entity;
	}

	public User toEntity() {
		User entity = new User();
		entity.setName(name);
		entity.setCellphone(cellphone);

		if (StringUtils.isNotBlank(password)) {
			entity.setPassword(SHA256Utils.encipherToHex(password));
		}

		return entity;
	}
}