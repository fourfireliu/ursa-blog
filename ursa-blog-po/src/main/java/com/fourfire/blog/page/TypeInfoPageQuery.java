package com.fourfire.blog.page;

public class TypeInfoPageQuery extends BasePageQuery {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1646696299950646276L;
	
	private String orderByColumn = "article_count";

	public String getOrderByColumn() {
		return orderByColumn;
	}

	public void setOrderByColumn(String orderByColumn) {
		this.orderByColumn = orderByColumn;
	}

	@Override
	public String toString() {
		return "TypeInfoPageQuery [orderByColumn=" + orderByColumn
				+ ", getPageNo()=" + getPageNo() + ", getPageSize()="
				+ getPageSize() + ", getOldPageSize()=" + getOldPageSize()
				+ ", isCheckNextPage()=" + isCheckNextPage()
				+ ", getStartRow()=" + getStartRow() + "]";
	}

}
