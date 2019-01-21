package com.sutanghome.model.shopping;

import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sutanghome.dao.entities.Payment;
import com.sutanghome.dao.entities.Shopping;

import devutility.external.javax.validation.annotation.Validation;

@Validation
@JsonIgnoreProperties(ignoreUnknown = true)
public class EditShoppingParam extends AddShoppingParam {
	@Min(value = 1, message = "请输入正确的id！")
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public Payment toPayment() {
		Payment entity = super.toPayment();
		entity.setId(id);
		return entity;
	}

	@Override
	public Shopping toShopping() {
		Shopping entity = super.toShopping();
		entity.setPaymentId(id);
		return entity;
	}
}