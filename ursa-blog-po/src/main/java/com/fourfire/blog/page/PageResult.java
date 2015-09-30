package com.fourfire.blog.page;

import java.io.Serializable;
import java.util.List;

/**
 * 分页查询结果对象 泛型
 * 
 * @author liuyi
 * @date 2015-09-29
 * @param <T>
 */
public class PageResult<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1485183559936050337L;
	
	private int pageNo = 1;
	private int pageSize;
	private List<T> pageResult;
	private boolean hasNext;
	private int totalCount;
	private boolean isSuccess;
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public int getPageNo() {
		return pageNo;
	}
	@Override
	public String toString() {
		return "PageResult [pageNo=" + pageNo + ", pageSize=" + pageSize
				+ ", pageResult=" + pageResult + ", hasNext=" + hasNext
				+ ", totalCount=" + totalCount + ", isSuccess=" + isSuccess
				+ "]";
	}
	public void setPageNo(int pageNo) {
		if (pageNo <= 0) {
			pageNo = 1;
		}
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		if (pageSize < 0) {
			pageSize = 0;
		}
		this.pageSize = pageSize;
	}
	public List<T> getPageResult() {
		return pageResult;
	}
	public void setPageResult(List<T> pageResult) {
		this.pageResult = pageResult;
	}
	public boolean isHasNext() {
		return hasNext;
	}
	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getStartRow() {
		return (pageNo - 1) * pageSize;
	}
}
