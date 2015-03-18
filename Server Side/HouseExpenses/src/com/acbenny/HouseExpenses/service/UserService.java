package com.acbenny.HouseExpenses.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
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
		try {
			userDAO.createUser(user);
		}catch (DataIntegrityViolationException dataEx) {
			translateIntegrityException(dataEx);
		} catch (Exception e) {
			System.out.println("Other Exception:" + e.getMessage());
		}
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

	public void getUserByUsername(String username) {
		User user = userDAO.getUserByUsername(username);
		System.out.println(user.toString());
	}

	private Exception translateIntegrityException(DataIntegrityViolationException ex) {
		System.out.println(ex.getMessage());
		ex.printStackTrace();
		
		return ex;

	}
}
