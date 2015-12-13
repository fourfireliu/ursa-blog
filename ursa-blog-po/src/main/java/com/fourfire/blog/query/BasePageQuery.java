package com.fourfire.blog.query;

import java.io.Serializable;

/**
 * 用于分页查询请求的基础类
 * 
 * @author liuyi
 *
 */
public class BasePageQuery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8751500995814496332L;

	private int pageNo;
	private int pageSize;
	
	//若设为True
	private boolean checkNextPage;

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		if (pageNo <= 0) {
			pageNo = 1;
		}
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		if (checkNextPage) {
			return pageSize + 1;
		}
		return pageSize;
	}
	
	public int getOldPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if (pageSize < 0) {
			pageSize = 0;
		}
		this.pageSize = pageSize;
	}

	public boolean isCheckNextPage() {
		return checkNextPage;
	}

	public void setCheckNextPage(boolean checkNextPage) {
		this.checkNextPage = checkNextPage;
	}
	
	public int getStartRow() {
		return (pageNo - 1) * pageSize;
	}

	@Override
	public String toString() {
		return "BasePageQuery [pageNo=" + pageNo + ", pageSize=" + pageSize
				+ ", checkNextPage=" + checkNextPage + "]";
	}
}
