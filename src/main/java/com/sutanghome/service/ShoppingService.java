package com.sutanghome.service;

import java.util.List;

import com.sutanghome.dao.model.shopping.ShoppingDO;
import com.sutanghome.model.shopping.AddShoppingParam;
import com.sutanghome.model.shopping.EditShoppingParam;
import com.sutanghome.model.shopping.SearchShoppingParam;

public interface ShoppingService {
	void add(AddShoppingParam param);

	List<ShoppingDO> pageData(SearchShoppingParam param);

	void update(EditShoppingParam param);
}