package com.acbenny.HouseExpenses.exception;

public enum ErrorCodes {
	FATAL_ERROR(9000, "Unhandled Scenario!!!"),
	DUPLICATE_USERNAME(9001, "Username already present!!!"),
	DUPLICATE_EMAIL(9002, "Email already present!!!"),
	DUPLICATE_ITEM(9003,"Item already present!!!"),
	USER_NOT_FOUND(9004,"User not found!!!"),
	ITEM_NOT_FOUND(9005, "Item not found!!!"),
	EXPENSE_AMOUNT_MISSING(9006, "Total Amount for Expense Log unavailable!!!"),
	SHARE_INTEGRITY(9007,"Invalid Share entiry!!!"),
	NO_ITEMS(9008,"No Items defined");

	private int code;
	private String message;
	
	private ErrorCodes(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
