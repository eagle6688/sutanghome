package com.sutanghome.dao;

import java.util.List;

import com.sutanghome.dao.entities.Fund;

public interface FundDao {
	int add(Fund entity);
	
	List<Fund> list(int pageIndex, int pageSize);
}