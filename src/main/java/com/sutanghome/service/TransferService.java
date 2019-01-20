package com.sutanghome.service;

import java.util.List;

import com.sutanghome.dao.model.transfer.TransferDO;
import com.sutanghome.model.transfer.AddTransferParam;
import com.sutanghome.model.transfer.EditTransferParam;
import com.sutanghome.model.transfer.SearchTransferParam;

public interface TransferService {
	void add(AddTransferParam param);

	List<TransferDO> pageData(SearchTransferParam param);

	void update(EditTransferParam param);
}