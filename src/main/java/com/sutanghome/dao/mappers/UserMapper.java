package com.sutanghome.dao.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sutanghome.dao.entities.User;

@Mapper
public interface UserMapper {
	void insert(User entity);

	User getByName(String name);

	List<User> list(int skip, int take);

	int count();
}