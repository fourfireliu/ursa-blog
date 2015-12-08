package com.fourfire.blog.page;

public class ArticlePageQuery extends BasePageQuery {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1954354068730846369L;
	private Integer typeId;
	private String orderByColumn = "comment_count desc";

	public String getOrderByColumn() {
		return orderByColumn;
	}

	public void setOrderByColumn(String orderByColumn) {
		this.orderByColumn = orderByColumn;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	@Override
	public String toString() {
		return "ArticlePageQuery [typeId=" + typeId + ", orderByColumn="
				+ orderByColumn + ", getPageNo()=" + getPageNo()
				+ ", getPageSize()=" + getPageSize() + ", getOldPageSize()="
				+ getOldPageSize() + ", isCheckNextPage()=" + isCheckNextPage()
				+ ", getStartRow()=" + getStartRow() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}	
}
