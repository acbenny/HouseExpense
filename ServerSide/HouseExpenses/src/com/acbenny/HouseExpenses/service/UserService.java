package com.acbenny.HouseExpenses.service;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acbenny.HouseExpenses.exception.ErrorCodes;
import com.acbenny.HouseExpenses.exception.ServiceException;
import com.acbenny.HouseExpenses.model.entity.User;
import com.acbenny.HouseExpenses.repository.UserRepository;

@Service("UserService")
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Transactional
	public User createUser(String userName, String name, String email,
			String password) throws ServiceException {
		User user = new User();
		user.setUserName(userName);
		user.setName(name);
		user.setEmail(email);
		user.setPwd(password);
		try {
			user = userRepository.saveAndFlush(user);
		} catch (DataIntegrityViolationException dataEx) {
			translateIntegrityException(dataEx);
		}
		return user;
	}

	@Transactional(readOnly = true)
	public User getUserByUserName(String userName) {
		User user = userRepository.findByUserName(userName);
		if (user == null)
			throw new ServiceException(ErrorCodes.USER_NOT_FOUND);
		return user;
	}

	@Transactional(readOnly = true)
	public List<User> listUsers() throws ServiceException {
		List<User> usrList = userRepository.findAll();
		if (usrList == null) {
			throw new ServiceException(ErrorCodes.FATAL_ERROR);
		}
		return usrList;
	}

	private void translateIntegrityException(DataIntegrityViolationException ex)
			throws ServiceException {
		if ((ex.getCause() != null)
				&& (ex.getCause() instanceof ConstraintViolationException)) {
			String constraintName = (((ConstraintViolationException) ex
					.getCause()).getConstraintName());
			if (constraintName != null) {
				if (constraintName.endsWith(User.getConstraintName("username"))) {
					throw new ServiceException(ErrorCodes.DUPLICATE_USERNAME);
				} else if (constraintName.endsWith(User
						.getConstraintName("email"))) {
					throw new ServiceException(ErrorCodes.DUPLICATE_EMAIL);
				}
			}
		}
		throw ex;
	}
}
