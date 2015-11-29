package com.fourfire.blog.entity;

public enum ErrorInfo {
	SYSTEM_ERROR(1000, "系统异常"), 
	READ_DB_ERROR(1001, "数据库读取失败"),
	
	INVALID_PARAM(1100, "错误参数"),
	UPDATE_DB_RESULT_ERROR(1101, "数据库修改操作结果异常");
	
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
}
