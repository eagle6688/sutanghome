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
import devutility.internal.models.OperationResult;

@Service
public class TransferServiceImpl implements TransferService {
	@Autowired
	private PaymentMapper paymentMapper;

	@Autowired
	private TransferMapper transferMapper;

	@Transactional
	@Override
	public OperationResult add(AddTransferParam param) {
		OperationResult result = new OperationResult();
		Payment payment = param.toPayment();

		if (paymentMapper.count(payment) > 0) {
			result.setErrorMessage("Payment已经存在！");
			return result;
		}

		paymentMapper.insert(payment);
		TransferQueryModel transferQueryModel = param.toQueryModel(payment.getId());

		/**
		 * 判断当前Payment主键和toUserId是否存在。
		 */
		if (transferMapper.count(transferQueryModel) > 0) {
			result.setErrorMessage("Transfer已经存在！");
			return result;
		}

		Transfer transfer = param.toTransfer(payment.getId());
		transferMapper.insert(transfer);

		result.setMessage("Transfer保存成功！");
		return result;
	}

	@Override
	public BaseListResponse<TransferDO> pageData(SearchTransferParam param) {
		BaseListResponse<TransferDO> response = new BaseListResponse<>();
		TransferQueryModel queryModel = param.toQueryModel();
		response.setCount(transferMapper.count(queryModel));
		response.setData(transferMapper.list(queryModel));
		return response;
	}

	@Transactional
	@Override
	public OperationResult update(EditTransferParam param) {
		OperationResult result = new OperationResult();
		Payment payment = param.toPayment();

		if (paymentMapper.count(payment) > 0) {
			result.setErrorMessage("Payment信息重复！");
			return result;
		}

		if (paymentMapper.update(payment) != 1) {
			result.setErrorMessage("Payment保存失败！");
			return result;
		}

		Transfer transfer = param.toTransfer();

		if (transferMapper.update(transfer) != 1) {
			result.setErrorMessage("Transfer保存失败！");
		}

		result.setMessage("Transfer保存成功！");
		return result;
	}
}