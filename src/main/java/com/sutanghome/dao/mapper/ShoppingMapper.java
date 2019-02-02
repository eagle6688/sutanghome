package com.sutanghome.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sutanghome.dao.entities.Shopping;
import com.sutanghome.dao.model.shopping.ShoppingDO;
import com.sutanghome.dao.model.shopping.ShoppingQueryModel;

@Mapper
public interface ShoppingMapper {
	void insert(Shopping entity);

	int count(ShoppingQueryModel model);

	ShoppingDO get(ShoppingQueryModel model);

	List<ShoppingDO> list(ShoppingQueryModel model);

	int update(Shopping entity);
}