package com.sutanghome.common.constant.typehandlers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.sutanghome.common.constant.PayMediumType;

public class PayMediumTypeHandler extends BaseTypeHandler<PayMediumType> {
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, PayMediumType parameter, JdbcType jdbcType) throws SQLException {
		ps.setShort(i, (short) parameter.getValue());
	}

	@Override
	public PayMediumType getNullableResult(ResultSet rs, String columnName) throws SQLException {
		int value = rs.getInt(columnName);

		if (rs.wasNull()) {
			return PayMediumType.UNKNOW;
		}

		return PayMediumType.parse(value);
	}

	@Override
	public PayMediumType getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		int value = rs.getInt(columnIndex);

		if (rs.wasNull()) {
			return PayMediumType.UNKNOW;
		}

		return PayMediumType.parse(value);
	}

	@Override
	public PayMediumType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		int value = cs.getInt(columnIndex);

		if (cs.wasNull()) {
			return PayMediumType.UNKNOW;
		}

		return PayMediumType.parse(value);
	}
}