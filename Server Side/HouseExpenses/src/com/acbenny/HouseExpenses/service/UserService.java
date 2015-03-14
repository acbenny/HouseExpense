package com.acbenny.HouseExpenses.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.acbenny.HouseExpenses.dao.UserDAO;
import com.acbenny.HouseExpenses.model.User;

@Service("UserService")
public class UserService {

	@Autowired
	@Qualifier("UserDAO")
	private UserDAO userDAO;

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public void createUser(String username, String name, String email, String password) {
		User user = new User();
		user.setUsername(username);
		user.setName(name);
		user.setEmail(email);
		user.setPwd(password);
		userDAO.createUser(user);
	}

	public void listUsers() {
		List<User> usrList = userDAO.getUserList();
		if (usrList == null) {
			System.out.println("No users in DB");
		} else {
			for (User usr : usrList) {
				System.out.println(usr.toString());
			}
		}
	}
}
