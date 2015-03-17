package com.acbenny.HouseExpenses.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.acbenny.HouseExpenses.dao.ItemDAO;
import com.acbenny.HouseExpenses.model.Item;

public class ItemDAOImpl implements ItemDAO {

	@PersistenceContext
	protected EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	@Transactional
	public void createItem(Item item) {
		em.persist(item);
		em.flush();
	}

}
