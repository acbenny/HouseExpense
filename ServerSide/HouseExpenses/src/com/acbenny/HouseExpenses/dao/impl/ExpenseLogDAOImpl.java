package com.acbenny.HouseExpenses.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.acbenny.HouseExpenses.dao.ExpenseLogDAO;
import com.acbenny.HouseExpenses.model.ExpenseLog;

@Repository("ExpenseLogDAO")
public class ExpenseLogDAOImpl implements ExpenseLogDAO {

	@PersistenceContext
	protected EntityManager em;

	public ExpenseLogDAOImpl() {
	}

	public ExpenseLogDAOImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	@Transactional
	public void createLog(ExpenseLog log) {
		em.persist(log);
	}

	@Override
	@Transactional
	public List<ExpenseLog> getExpenseLogsBetweenDateRange(Date startDate,
			Date endDate) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ExpenseLog> cq = builder.createQuery(ExpenseLog.class);
		Root<ExpenseLog> root = cq.from(ExpenseLog.class);
		cq.select(root);
		if (startDate != null) {
			cq.where(builder.and(builder.greaterThanOrEqualTo(
					root.<Date> get("datetime"), startDate)));
		}
		if (endDate != null) {
			cq.where(builder.and(builder.lessThanOrEqualTo(
					root.<Date> get("datetime"), endDate)));
		}
		TypedQuery<ExpenseLog> tq = em.createQuery(cq);
		List<ExpenseLog> list = tq.getResultList();
		return list;
	}

}
