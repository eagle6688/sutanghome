package com.sutanghome.common.constant.typehandlers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.sutanghome.common.constant.PaymentMedium;

public class PaymentMediumTypeHandler extends BaseTypeHandler<PaymentMedium> {
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, PaymentMedium parameter, JdbcType jdbcType) throws SQLException {
		ps.setShort(i, (short) parameter.getValue());
	}

	@Override
	public PaymentMedium getNullableResult(ResultSet rs, String columnName) throws SQLException {
		int value = rs.getInt(columnName);

		if (rs.wasNull()) {
			return PaymentMedium.UNKNOW;
		}

		return PaymentMedium.parse(value);
	}

	@Override
	public PaymentMedium getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		int value = rs.getInt(columnIndex);

		if (rs.wasNull()) {
			return PaymentMedium.UNKNOW;
		}

		return PaymentMedium.parse(value);
	}

	@Override
	public PaymentMedium getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		int value = cs.getInt(columnIndex);

		if (cs.wasNull()) {
			return PaymentMedium.UNKNOW;
		}

		return PaymentMedium.parse(value);
	}
}