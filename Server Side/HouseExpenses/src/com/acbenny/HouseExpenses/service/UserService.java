package com.acbenny.HouseExpenses.service;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.acbenny.HouseExpenses.dao.UserDAO;
import com.acbenny.HouseExpenses.exception.DAOErrorCodes;
import com.acbenny.HouseExpenses.exception.DAOException;
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

	public User createUser(String username, String name, String email, String password) throws DAOException {
		User user = new User();
		user.setUsername(username);
		user.setName(name);
		user.setEmail(email);
		user.setPwd(password);
		try {
			userDAO.createUser(user);
		}catch (DataIntegrityViolationException dataEx) {
			translateIntegrityException(dataEx);
		}
		return user;
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

	public User getUserByUsername(String username) throws DAOException {
		User user = null;
		try {
			user = userDAO.getUserByUsername(username);
		}catch (EmptyResultDataAccessException e) {
			throw new DAOException(DAOErrorCodes.USER_NOT_FOUND);
		}
		return user;
	}

	private void translateIntegrityException(DataIntegrityViolationException ex) throws DAOException {
		if ((ex.getCause()!=null)&&(ex.getCause() instanceof ConstraintViolationException)) {
			String constraintName = (((ConstraintViolationException)ex.getCause()).getConstraintName());
			if (constraintName != null) {
				if (constraintName.endsWith(User.getConstraintName("username"))) {
					throw new DAOException(DAOErrorCodes.DUPLICATE_USERNAME);
				}else if (constraintName.endsWith(User.getConstraintName("email"))) {
					throw new DAOException(DAOErrorCodes.DUPLICATE_EMAIL);
				}
			}
		}
		throw ex;
	}
}
