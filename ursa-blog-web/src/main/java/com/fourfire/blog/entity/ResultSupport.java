package com.fourfire.blog.entity;

import java.io.Serializable;

/**
 * base property for result
 * 
 * @author fourfire
 * @date 2015-11-24
 */
public class ResultSupport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6545727789263626209L;
	
	private boolean isSuccess;
	
	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public ErrorInfo getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(ErrorInfo errorInfo) {
		this.errorInfo = errorInfo;
	}

	@Override
	public String toString() {
		return "ResultSupport [isSuccess=" + isSuccess + ", errorInfo=" + errorInfo + "]";
	}

	private ErrorInfo errorInfo;
}
