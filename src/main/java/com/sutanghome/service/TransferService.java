package com.sutanghome.service;

import com.sutanghome.dao.model.transfer.TransferDO;
import com.sutanghome.model.transfer.AddTransferParam;
import com.sutanghome.model.transfer.EditTransferParam;
import com.sutanghome.model.transfer.SearchTransferParam;

import devutility.internal.models.BaseListResponse;
import devutility.internal.models.BaseResponse;

public interface TransferService {
	void add(AddTransferParam param);

	BaseListResponse<TransferDO> pageData(SearchTransferParam param);

	BaseResponse<TransferDO> detail(int id);

	void update(EditTransferParam param);
}