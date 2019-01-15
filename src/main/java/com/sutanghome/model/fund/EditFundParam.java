package com.sutanghome.model.fund;

import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import devutility.external.javax.validation.annotation.Validation;

@Validation
@JsonIgnoreProperties(ignoreUnknown = true)
public class EditFundParam extends AddFundParam {
	@Min(value = 1, message = "请输入正确的id！")
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}