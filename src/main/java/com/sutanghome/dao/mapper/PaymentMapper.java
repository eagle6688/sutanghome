package com.sutanghome.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sutanghome.dao.entities.Payment;
import com.sutanghome.dao.model.payment.PaymentDO;
import com.sutanghome.dao.model.payment.PaymentQueryModel;

@Mapper
public interface PaymentMapper {
	void insert(Payment entity);

	int count(Payment entity);

	PaymentDO get(PaymentQueryModel model);

	List<PaymentDO> list(PaymentQueryModel model);

	int update(Payment entity);
}