package com.fourfire.blog.result;

/**
 * Base result class, 
 * 
 * @author fourfire
 * 
 * @param <T>
 * @date 2015-11-29
 */
public class BaseResult<T> extends ResultSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4250374846339928027L;
	
	private T t;

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	@Override
	public String toString() {
		return "BaseResult [t=" + t + ", isSuccess()=" + isSuccess() + ", getErrorInfo()=" + getErrorInfo() + "]";
	}
	
	public static void main(String args[]) {
		BaseResult<Boolean> result = new BaseResult<Boolean>();
		result.setSuccess(true);
		result.setT(false);
		ErrorInfo errorInfo = ErrorInfo.READ_DB_ERROR;
		result.setErrorInfo(errorInfo);
		
		System.out.println(result);
	}
}
