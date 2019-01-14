package com.sutanghome.model.user;

import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sutanghome.dao.entities.User;

import devutility.external.javax.validation.annotation.Validation;

@Validation
@JsonIgnoreProperties(ignoreUnknown = true)
public class EditUserParam extends AddUserParam {
	@Min(value = 1, message = "请输入正确的id！")
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public User toEntity() {
		User entity = super.toEntity();
		entity.setId(id);
		return entity;
	}

	@Override
	public User toCountEntity() {
		User entity = super.toCountEntity();
		entity.setId(id);
		return entity;
	}
}