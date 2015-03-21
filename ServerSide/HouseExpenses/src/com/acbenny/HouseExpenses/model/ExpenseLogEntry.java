package com.acbenny.HouseExpenses.model;

import java.util.Date;
import java.util.List;

public class ExpenseLogEntry {

	private String loggedByUserName;

	private Date logDate;

	private String itemName;
	
	private String pricePerItem;

	private int quantity;

	private String amount;

	private List<UserShare> userShareList;

	public String getLoggedByUserName() {
		return loggedByUserName;
	}

	public void setLoggedByUserName(String loggedByUserName) {
		this.loggedByUserName = loggedByUserName;
	}

	public Date getLogDate() {
		return logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getPricePerItem() {
		return pricePerItem;
	}

	public void setPricePerItem(String pricePerItem) {
		this.pricePerItem = pricePerItem;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public List<UserShare> getUserShareList() {
		return userShareList;
	}

	public void setUserShareList(List<UserShare> userShareList) {
		this.userShareList = userShareList;
	}

}
