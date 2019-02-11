package com.sutanghome.service;

import com.sutanghome.dao.model.transfer.TransferDO;
import com.sutanghome.model.transfer.AddTransferParam;
import com.sutanghome.model.transfer.EditTransferParam;
import com.sutanghome.model.transfer.SearchTransferParam;

import devutility.internal.models.BaseListResponse;
import devutility.internal.models.BaseResponse;
import devutility.internal.models.OperationResult;

public interface TransferService {
	OperationResult add(AddTransferParam param);

	BaseListResponse<TransferDO> pageData(SearchTransferParam param);

	BaseResponse<TransferDO> detail(int id);

	OperationResult update(EditTransferParam param);
}