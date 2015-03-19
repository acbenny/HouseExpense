package com.acbenny.HouseExpenses.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.acbenny.HouseExpenses.dao.UserDAO;
import com.acbenny.HouseExpenses.exception.ErrorCodes;
import com.acbenny.HouseExpenses.exception.ServiceException;
import com.acbenny.HouseExpenses.model.Share;
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

	public User createUser(String username, String name, String email, String password) throws ServiceException {
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

	public User getUserByUsername(String username) throws ServiceException {
		User user = null;
		try {
			user = userDAO.getUserByUsername(username);
		}catch (EmptyResultDataAccessException e) {
			throw new ServiceException(ErrorCodes.USER_NOT_FOUND);
		}
		return user;
	}

	@Transactional
	public void printExpenses(String userName) throws ServiceException {
		User user = getUserByUsername(userName);
		System.out.println("Expenses for " + userName);
		SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
		for (Share share : user.getShareList()) {
			Date dt = share.getExpenseLog().getDatetime();
			String itemName = share.getExpenseLog().getItem().getItemName();
			BigDecimal totAmount = share.getExpenseLog().getAmount();
			int shareMultiplier = share.getShareMultiplier();
			int shareDivisor = share.getExpenseLog().getShareDivisor();
			BigDecimal sharePortion = totAmount.multiply(new BigDecimal(
					shareMultiplier));
			sharePortion = sharePortion.divide(new BigDecimal(shareDivisor));
			System.out.println(outputFormat.format(dt) + "----" + itemName
					+ "----"
					+ totAmount.toString() + "----" + shareMultiplier + "----"
					+ shareDivisor + "----" + sharePortion.toString());
			
		}
	}

	private void translateIntegrityException(DataIntegrityViolationException ex) throws ServiceException {
		if ((ex.getCause()!=null)&&(ex.getCause() instanceof ConstraintViolationException)) {
			String constraintName = (((ConstraintViolationException)ex.getCause()).getConstraintName());
			if (constraintName != null) {
				if (constraintName.endsWith(User.getConstraintName("username"))) {
					throw new ServiceException(ErrorCodes.DUPLICATE_USERNAME);
				}else if (constraintName.endsWith(User.getConstraintName("email"))) {
					throw new ServiceException(ErrorCodes.DUPLICATE_EMAIL);
				}
			}
		}
		throw ex;
	}
}
