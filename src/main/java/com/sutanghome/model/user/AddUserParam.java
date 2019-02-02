package com.sutanghome.model.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sutanghome.dao.entities.User;

import devutility.external.javax.validation.annotation.Validation;
import devutility.internal.security.SHA256Utils;

@Validation
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddUserParam {
	@NotNull(message = "姓名不能为空！")
	private String name;

	@Pattern(regexp = "^1(3|4|5|7|8)\\d{9}$", message = "手机号码格式错误！")
	private String cellphone;

	@Size(max = 128, message = "请输入密码！")
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

	public User toEntity() {
		User entity = new User();
		entity.setName(name);
		entity.setCellphone(cellphone);
		entity.setPassword(SHA256Utils.encipherToHex(password));
		return entity;
	}

	public User toCountEntity() {
		User entity = new User();
		entity.setCellphone(cellphone);
		return entity;
	}
}