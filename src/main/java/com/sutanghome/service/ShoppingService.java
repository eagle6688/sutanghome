package com.sutanghome.service;

import com.sutanghome.dao.model.shopping.ShoppingDO;
import com.sutanghome.model.shopping.AddShoppingParam;
import com.sutanghome.model.shopping.EditShoppingParam;
import com.sutanghome.model.shopping.SearchShoppingParam;

import devutility.internal.models.BaseListResponse;
import devutility.internal.models.BaseResponse;

public interface ShoppingService {
	void add(AddShoppingParam param);

	BaseListResponse<ShoppingDO> pageData(SearchShoppingParam param);

	BaseResponse<ShoppingDO> detail(int id);

	void edit(EditShoppingParam param);
}