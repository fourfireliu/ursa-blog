package com.fourfire.blog.result;

public enum ErrorInfo {
	SYSTEM_ERROR(1000, "系统异常"), 
	READ_DB_ERROR(1001, "数据库读取失败"),
	UPDATE_DB_RESULT_ERROR(1002, "数据库修改操作结果异常"),
	
	INVALID_PARAM(1100, "错误参数"),
	INVALID_RESULT(1101, "错误结果");
	
	private int errorCode;
	private String errorMsg;
	
	private ErrorInfo(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	public String toString() {
		return "errorCode=" + this.getErrorCode() + ", errorMsg=" + this.getErrorMsg();
	}
}
