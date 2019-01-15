package com.sutanghome.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sutanghome.dao.entities.Fund;
import com.sutanghome.dao.model.fund.FundDO;
import com.sutanghome.dao.model.fund.FundQueryModel;

@Mapper
public interface FundMapper {
	void insert(Fund entity);

	int count(Fund entity);

	List<FundDO> list(FundQueryModel model);

	int update(Fund entity);
}