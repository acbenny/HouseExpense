package com.acbenny.HouseExpenses.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SHARE_DETAILS")
public class Share {

	@Id
	@SequenceGenerator(name = "ID_SEQ", sequenceName = "SHARE_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQ")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EXP_LOG_ID")
	private ExpenseLog expenseLog;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private User user;

	@Column(name = "SHARE_MULTIPLIER")
	private int shareMultiplier;

	public ExpenseLog getExpenseLog() {
		return expenseLog;
	}

	public void setExpenseLog(ExpenseLog expenseLog) {
		this.expenseLog = expenseLog;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getShareMultiplier() {
		return shareMultiplier;
	}

	public void setShareMultiplier(int shareMultiplier) {
		this.shareMultiplier = shareMultiplier;
	}

}