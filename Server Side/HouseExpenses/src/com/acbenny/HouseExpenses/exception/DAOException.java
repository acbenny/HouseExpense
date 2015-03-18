package com.acbenny.HouseExpenses.exception;

public class DAOException extends Throwable{

	private static final long serialVersionUID = 1L;

	private ErrorCode errorCodeEnum;
	
	public DAOException(ErrorCode ec){
		errorCodeEnum = ec;
	}

	@Override
	public String getMessage() {
		return "ERROR:"+errorCodeEnum.getCode()+"-"+errorCodeEnum.getMessage();
	}

	public ErrorCode getError() {
		return errorCodeEnum;
	}

	public void setError(ErrorCode error) {
		this.errorCodeEnum = error;
	}
	
}
