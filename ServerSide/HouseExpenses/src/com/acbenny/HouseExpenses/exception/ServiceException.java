package com.acbenny.HouseExpenses.exception;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private ErrorCodes errorCodeEnum;
	
	public ServiceException(ErrorCodes ec) {
		errorCodeEnum = ec;
	}

	@Override
	public String getMessage() {
		return "ERROR:"+errorCodeEnum.getCode()+"-"+errorCodeEnum.getMessage();
	}

	public ErrorCodes getError() {
		return errorCodeEnum;
	}

	public void setError(ErrorCodes error) {
		this.errorCodeEnum = error;
	}
	
}
