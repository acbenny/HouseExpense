package com.acbenny.HouseExpenses.exception;

public enum DAOErrorCodes implements ErrorCode{
	DUPLICATE_USERNAME(9001,"Username already present!!!"),
	DUPLICATE_EMAIL(9002,"Email already present!!!"),
	DUPLICATE_ITEM(9003,"Item already present!!!"),
	USER_NOT_FOUND(9004,"User not found!!!"),
	ITEM_NOT_FOUND(9005,"Item not found!!!");

	private int code;
	private String message;
	
	private DAOErrorCodes(int code, String message){
		this.code = code;
		this.message = message;
	}
	
	@Override
	public int getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
