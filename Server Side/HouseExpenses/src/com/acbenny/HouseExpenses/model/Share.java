package com.acbenny.HouseExpenses.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SHARE_DETAILS")
public class Share {

	@Id
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "EXP_LOG_ID")
	private ExpenseLog expenseLog;

	@OneToOne
	@JoinColumn(name = "USER_ID")
	private User user;

	private int shareMultiplier;

	public int getShareMultiplier() {
		return shareMultiplier;
	}

	public void setShareMultiplier(int shareMultiplier) {
		this.shareMultiplier = shareMultiplier;
	}

}
