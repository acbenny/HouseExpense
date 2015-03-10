package com.acbenny.HouseExpenses.dao;

import com.acbenny.HouseExpenses.model.User;

public interface UserDAO {

	public void createUser(String username, String name, String email, String password);
	
	public User getUserByUsername(String username);
	
	public User deleteUser(String username);
}
