package com.sutanghome.dao.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sutanghome.dao.FundDao;
import com.sutanghome.dao.entities.Fund;
import com.sutanghome.dao.mappers.FundMapper;

@Repository
public class FundDaoImpl implements FundDao {
	@Autowired
	private FundMapper fundMapper;

	@Override
	public int add(Fund entity) {
		fundMapper.insert(entity);
		return entity.getId();
	}

	@Override
	public List<Fund> list(int pageIndex, int pageSize) {
		int skip = (pageIndex - 1) * pageSize;
		return fundMapper.list(skip, pageSize);
	}
}