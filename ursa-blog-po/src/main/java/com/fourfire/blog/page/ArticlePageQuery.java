package com.fourfire.blog.page;

public class ArticlePageQuery extends BasePageQuery {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1954354068730846369L;
	private int typeId;
	private boolean orderByReadCount;

	public boolean isOrderByReadCount() {
		return orderByReadCount;
	}

	public void setOrderByReadCount(boolean orderByReadCount) {
		this.orderByReadCount = orderByReadCount;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	@Override
	public String toString() {
		return "ArticlePageQuery [typeId=" + typeId + ", orderByReadCount="
				+ orderByReadCount + ", getPageNo()=" + getPageNo()
				+ ", getPageSize()=" + getPageSize() + ", getOldPageSize()="
				+ getOldPageSize() + ", isCheckNextPage()=" + isCheckNextPage()
				+ ", getStartRow()=" + getStartRow() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}	
}
