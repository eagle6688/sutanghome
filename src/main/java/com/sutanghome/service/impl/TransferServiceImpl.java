package com.sutanghome.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sutanghome.dao.entities.Payment;
import com.sutanghome.dao.entities.Transfer;
import com.sutanghome.dao.mapper.PaymentMapper;
import com.sutanghome.dao.mapper.TransferMapper;
import com.sutanghome.dao.model.transfer.TransferDO;
import com.sutanghome.dao.model.transfer.TransferQueryModel;
import com.sutanghome.model.transfer.AddTransferParam;
import com.sutanghome.model.transfer.EditTransferParam;
import com.sutanghome.model.transfer.SearchTransferParam;
import com.sutanghome.service.TransferService;

import devutility.internal.models.BaseListResponse;
import devutility.internal.models.BaseResponse;

@Service
public class TransferServiceImpl implements TransferService {
	@Autowired
	private PaymentMapper paymentMapper;

	@Autowired
	private TransferMapper transferMapper;

	@Transactional
	@Override
	public void add(AddTransferParam param) {
		Payment payment = param.toPayment();

		if (paymentMapper.count(payment) > 0) {
			throw new IllegalArgumentException("Payment已经存在！");
		}

		paymentMapper.insert(payment);
		TransferQueryModel transferQueryModel = param.toQueryModel(payment.getId());

		/**
		 * 判断当前Payment主键和toUserId是否存在。
		 */
		if (transferMapper.count(transferQueryModel) > 0) {
			throw new IllegalArgumentException("Transfer已经存在！");
		}

		Transfer transfer = param.toTransfer(payment.getId());
		transferMapper.insert(transfer);
	}

	@Override
	public BaseListResponse<TransferDO> pageData(SearchTransferParam param) {
		BaseListResponse<TransferDO> response = new BaseListResponse<>();
		TransferQueryModel queryModel = param.toQueryModel();
		response.setCount(transferMapper.count(queryModel));
		response.setData(transferMapper.list(queryModel));
		return response;
	}

	@Override
	public BaseResponse<TransferDO> detail(int id) {
		BaseResponse<TransferDO> response = new BaseResponse<>();

		TransferQueryModel queryModel = new TransferQueryModel();
		queryModel.setId(id);

		TransferDO model = transferMapper.get(queryModel);

		if (model == null) {
			response.setErrorMessage("记录不存在！");
			return response;
		}

		response.setData(model);
		return response;
	}

	@Transactional
	@Override
	public void update(EditTransferParam param) {
		Payment payment = param.toPayment();

		if (paymentMapper.count(payment) > 0) {
			throw new IllegalArgumentException("Payment信息重复！");
		}

		if (paymentMapper.update(payment) != 1) {
			throw new IllegalArgumentException("Payment保存失败！");
		}

		Transfer transfer = param.toTransfer();

		if (transferMapper.update(transfer) != 1) {
			throw new IllegalArgumentException("Transfer保存失败！");
		}
	}
}