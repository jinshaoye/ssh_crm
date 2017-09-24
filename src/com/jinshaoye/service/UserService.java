package com.jinshaoye.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.jinshaoye.dao.UserDao;
import com.jinshaoye.entity.User;

@Transactional
public class UserService {

	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	//登录的方法
	public User login(User user) {
		// 调用dao里面的方法
		return userDao.loginUser(user);
	}
	
	public List<User> findAll() {
		return userDao.findAll();
	}
	
}
