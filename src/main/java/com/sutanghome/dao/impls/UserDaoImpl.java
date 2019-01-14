package com.sutanghome.dao.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sutanghome.dao.UserDao;
import com.sutanghome.dao.entities.User;
import com.sutanghome.dao.mappers.UserMapper;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private UserMapper userMapper;

	@Override
	public int add(User entity) {
		User person = userMapper.getByName(entity.getName());

		if (person != null) {
			throw new IllegalArgumentException(String.format("Person with name \"%s\" exists.", entity.getName()));
		}

		userMapper.insert(entity);
		return entity.getId();
	}

	@Override
	public List<User> list(int pageIndex, int pageSize) {
		return null;
	}
}