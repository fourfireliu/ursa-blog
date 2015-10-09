package com.fourfire.blog.page;

public class ArticlePageQuery extends BasePageQuery {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1954354068730846369L;
	private int typeId;

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	@Override
	public String toString() {
		return "ArticlePageQuery [typeId=" + typeId + ", getPageNo()="
				+ getPageNo() + ", getPageSize()=" + getPageSize()
				+ ", getOldPageSize()=" + getOldPageSize()
				+ ", isCheckNextPage()=" + isCheckNextPage()
				+ ", getStartRow()=" + getStartRow() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}	
}
