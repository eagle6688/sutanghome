package com.sutanghome.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sutanghome.dao.entities.User;
import com.sutanghome.dao.model.UserQueryParam;

@Mapper
public interface UserMapper {
	void insert(User entity);

	int count(User entity);

	List<User> list(UserQueryParam param);

	int update(User entity);
}