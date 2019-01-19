package com.sutanghome.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sutanghome.dao.entities.User;
import com.sutanghome.dao.model.user.UserQueryModel;

import devutility.internal.models.KeyValue;

@Mapper
public interface UserMapper {
	void insert(User entity);

	int count(User entity);

	User get(User entity);

	List<User> list(UserQueryModel model);

	List<KeyValue> listKV(UserQueryModel model);

	int update(User entity);
}