package com.sutanghome.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sutanghome.dao.PaymentMapper;
import com.sutanghome.dao.TransferMapper;
import com.sutanghome.dao.entities.Payment;
import com.sutanghome.dao.entities.Transfer;
import com.sutanghome.dao.model.transfer.TransferDO;
import com.sutanghome.dao.model.transfer.TransferQueryModel;
import com.sutanghome.model.transfer.AddTransferParam;
import com.sutanghome.model.transfer.EditTransferParam;
import com.sutanghome.model.transfer.SearchTransferParam;
import com.sutanghome.service.TransferService;

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
		TransferQueryModel transferQueryModel = param.toQueryModel();

		if (transferMapper.count(transferQueryModel) > 0) {
			throw new IllegalArgumentException("Transfer已经存在！");
		}

		Transfer transfer = param.toTransfer();
		transfer.setPaymentId(payment.getId());
		transferMapper.insert(transfer);
	}

	@Override
	public List<TransferDO> pageData(SearchTransferParam param) {
		return transferMapper.list(param.toQueryModel());
	}

	@Override
	public void update(EditTransferParam param) {

	}
}