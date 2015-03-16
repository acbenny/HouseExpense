package com.acbenny.HouseExpenses.model;

import javax.persistence.Column;
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

	@Column(name = "EXP_LOG_ID")
	private Integer expenseLogId;

	@Column(name = "USER_ID")
	private Integer userId;

	@ManyToOne
	@JoinColumn(name = "EXP_LOG_ID")
	private ExpenseLog expenseLog;

	@OneToOne(optional = false)
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
