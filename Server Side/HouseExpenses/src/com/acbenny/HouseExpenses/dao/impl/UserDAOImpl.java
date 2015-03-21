package com.acbenny.HouseExpenses.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
	@Transactional
	public User getUserByUsername(String username) {

		CriteriaQuery<User> cq = em.getCriteriaBuilder().createQuery(User.class);
		Root<User> RootUser = cq.from(User.class);
		cq.select(RootUser);
		cq.where(RootUser.get("username").in(username));
		TypedQuery<User> tq = em.createQuery(cq);
		User user = tq.getSingleResult();
		return user;
	}

	@Override
	@Transactional
	public List<User> getUserList() {
		Query qry = em.createQuery("SELECT u FROM User u");
		@SuppressWarnings("unchecked")
		List<User> list = qry.getResultList();
		return list;
	}
}
