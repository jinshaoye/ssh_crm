package com.jinshaoye.dao;

import java.util.List;

import com.jinshaoye.entity.User;

public interface UserDao {

	User loginUser(User user);

	List<User> findAll();

}
