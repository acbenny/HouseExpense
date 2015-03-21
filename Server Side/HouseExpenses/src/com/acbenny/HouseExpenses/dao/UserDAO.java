package com.acbenny.HouseExpenses.dao;

import java.util.List;

import com.acbenny.HouseExpenses.model.User;

public interface UserDAO {

	public void createUser(User user);
	
	public User deleteUser(User user);

	public List<User> getUserList();

	public User getUserByUsername(String username);
	
}
