package com.acbenny.HouseExpenses.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.acbenny.HouseExpenses.dao.UserDAO;
import com.acbenny.HouseExpenses.model.User;

@Repository("UserDAO")
public class UserDAOImpl implements UserDAO {

	@PersistenceContext
	protected EntityManager em;
	
	public UserDAOImpl() {
	}
	public UserDAOImpl(EntityManager em) {
		this.em = em;
	}
	public EntityManager getEm() {
		return em;
	}
	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	@Transactional
	public void createUser(User user) {
		em.persist(user);
		em.flush();
	}

	@Override
	public User deleteUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public List<User> getUserList() {
		Query qry = em.createQuery("SELECT u FROM User u");
		List<User> list = qry.getResultList();
		return list;
	}
}
