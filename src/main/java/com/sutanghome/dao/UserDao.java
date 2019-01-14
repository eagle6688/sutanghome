package com.sutanghome.dao;

import java.util.List;

import com.sutanghome.dao.entities.User;

public interface UserDao {
	int add(User entity);

	List<User> list(int pageIndex, int pageSize);
}