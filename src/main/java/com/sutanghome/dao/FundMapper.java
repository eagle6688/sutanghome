package com.sutanghome.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sutanghome.dao.entities.Fund;

@Mapper
public interface FundMapper {
	/**
	 * Insert a new Fund object in database.
	 * @param entity: Fund object.
	 */
	void insert(Fund entity);
	
	/**
	 * List a page of data from Fund table.
	 * @param skip: Skip number of records.
	 * @param take: Take number of records.
	 * @return List<Fund>
	 */
	List<Fund> list(int skip, int take);
}