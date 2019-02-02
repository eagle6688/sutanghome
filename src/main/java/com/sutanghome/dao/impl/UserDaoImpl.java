package com.sutanghome.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sutanghome.dao.UserDao;
import com.sutanghome.dao.entities.User;
import com.sutanghome.dao.mapper.UserMapper;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private UserMapper userMapper;

	@Override
	public User get(int id) {
		User searchEntity = new User();
		searchEntity.setId(id);
		return userMapper.get(searchEntity);
	}
}