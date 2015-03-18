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

import com.acbenny.HouseExpenses.dao.ItemDAO;
import com.acbenny.HouseExpenses.model.Item;

@Repository("ItemDAO")
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

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Item> getItemList() {
		Query qry = em.createQuery("SELECT i FROM Item i");
		List<Item> list = qry.getResultList();
		return list;
	}

	@Override
	@Transactional
	public Item getItemByName(String itemName) {
		CriteriaQuery<Item> cq = em.getCriteriaBuilder().createQuery(Item.class);
		Root<Item> RootUser = cq.from(Item.class);
		cq.select(RootUser);
		cq.where(RootUser.get("itemName").in(itemName));
		TypedQuery<Item> tq = em.createQuery(cq);
		Item item = tq.getSingleResult();
		return item;
	}

}
