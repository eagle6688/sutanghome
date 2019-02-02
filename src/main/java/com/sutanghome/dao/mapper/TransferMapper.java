package com.sutanghome.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sutanghome.dao.entities.Transfer;
import com.sutanghome.dao.model.transfer.TransferDO;
import com.sutanghome.dao.model.transfer.TransferQueryModel;

@Mapper
public interface TransferMapper {
	void insert(Transfer entity);

	int count(TransferQueryModel model);

	TransferDO get(TransferQueryModel model);

	List<TransferDO> list(TransferQueryModel model);

	int update(Transfer entity);
}