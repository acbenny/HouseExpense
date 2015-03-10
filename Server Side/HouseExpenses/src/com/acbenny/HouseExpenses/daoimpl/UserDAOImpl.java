package com.acbenny.HouseExpenses.daoimpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.acbenny.HouseExpenses.dao.UserDAO;
import com.acbenny.HouseExpenses.model.User;

public class UserDAOImpl implements UserDAO {

	protected EntityManager em;
	
	public UserDAOImpl(EntityManager em) {
		this.em = em;
	}
	public EntityManager getEm() {
		return em;
	}
	@PersistenceContext
	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	public void createUser(String username, String name, String email,
			String password) {
		User user = new User();
		user.setUsername(username);
		user.setName(name);
		user.setEmail(email);
		
	}

	@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User deleteUser(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
