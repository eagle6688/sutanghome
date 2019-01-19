package com.sutanghome.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sutanghome.dao.entities.Purchase;
import com.sutanghome.dao.model.purchase.PurchaseDO;
import com.sutanghome.dao.model.purchase.PurchaseQueryModel;

@Mapper
public interface PurchaseMapper {
	void insert(Purchase entity);

	int count(Purchase entity);

	PurchaseDO get(Purchase entity);

	List<PurchaseDO> list(PurchaseQueryModel model);

	int update(Purchase entity);
}