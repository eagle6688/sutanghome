package com.sutanghome.model.sign;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sutanghome.dao.entities.User;

import devutility.external.javax.validation.annotation.Validation;
import devutility.internal.security.SHA256Utils;

@Validation
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignInParam {
	@Pattern(regexp = "^1(3|4|5|7|8)\\d{9}$", message = "请输入正确格式的手机号码！")
	private String cellphone;

	@Size(min = 6, max = 36, message = "请输入6-36位密码！")
	private String password;

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

	public User toUser() {
		User entity = new User();
		entity.setCellphone(cellphone);
		entity.setPassword(SHA256Utils.encipherToHex(password));
		return entity;
	}
}