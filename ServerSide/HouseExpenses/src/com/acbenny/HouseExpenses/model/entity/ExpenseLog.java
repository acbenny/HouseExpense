package com.acbenny.HouseExpenses.model.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "EXPENSE_LOGS")
public class ExpenseLog {

	@Id
	@SequenceGenerator(name = "ID_SEQ", sequenceName = "EXP_LOG_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQ")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "LOGGED_BY")
	private User loggedBy;

	private Date dateTime;

	@ManyToOne
	@JoinColumn(name = "ITEM_ID")
	private Item item;

	private BigDecimal amount;

	@OneToMany(mappedBy = "expenseLog", targetEntity = Share.class, fetch = FetchType.LAZY)
	private List<Share> shareList;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public User getLoggedBy() {
		return loggedBy;
	}

	public void setLoggedBy(User loggedBy) {
		this.loggedBy = loggedBy;
	}

	public List<Share> getShareList() {
		return shareList;
	}

	public void setShareList(List<Share> shareList) {
		this.shareList = shareList;
	}

	public int getShareDivisor() {
		List<Share> shareList = getShareList();
		int divisor = 0;
		for (Share share : shareList)
			divisor += share.getShareMultiplier();
		return divisor;
	}
}
