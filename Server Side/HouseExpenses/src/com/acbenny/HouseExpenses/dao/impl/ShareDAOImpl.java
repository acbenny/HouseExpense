package com.acbenny.HouseExpenses.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.acbenny.HouseExpenses.dao.ShareDAO;
import com.acbenny.HouseExpenses.model.Share;

@Repository("ShareDAO")
public class ShareDAOImpl implements ShareDAO {

	@PersistenceContext
	protected EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public void createShare(Share share) {
		em.persist(share);
	}
}
