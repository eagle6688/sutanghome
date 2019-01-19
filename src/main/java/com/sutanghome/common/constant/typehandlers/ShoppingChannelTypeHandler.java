package com.sutanghome.common.constant.typehandlers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.sutanghome.common.constant.ShoppingChannel;

public class ShoppingChannelTypeHandler extends BaseTypeHandler<ShoppingChannel> {
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, ShoppingChannel parameter, JdbcType jdbcType) throws SQLException {
		ps.setShort(i, (short) parameter.getValue());
	}

	@Override
	public ShoppingChannel getNullableResult(ResultSet rs, String columnName) throws SQLException {
		int value = rs.getInt(columnName);

		if (rs.wasNull()) {
			return ShoppingChannel.UNKNOW;
		}

		return ShoppingChannel.parse(value);
	}

	@Override
	public ShoppingChannel getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		int value = rs.getInt(columnIndex);

		if (rs.wasNull()) {
			return ShoppingChannel.UNKNOW;
		}

		return ShoppingChannel.parse(value);
	}

	@Override
	public ShoppingChannel getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		int value = cs.getInt(columnIndex);

		if (cs.wasNull()) {
			return ShoppingChannel.UNKNOW;
		}

		return ShoppingChannel.parse(value);
	}
}