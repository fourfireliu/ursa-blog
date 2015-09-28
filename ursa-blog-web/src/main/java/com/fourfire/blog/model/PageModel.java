package com.fourfire.blog.model;
import java.util.List;
import java.util.Map;

public class PageModel {

	//当前页
	private int currentPage;
	
	private String keyWords;
	//总页数
	private int sumPage;
	
	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	//总条数
	private int sumCount;
	
	//每页条数
	private int pageCount;
	
	//数据集合
	private List<Map> list;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getSumPage() {
		return sumPage;
	}

	public int getSumCount() {
		return sumCount;
	}

	public void setSumCount(int sumCount) {
		this.sumCount = sumCount;
		
		//自动算出总页数
		sumPage = (int)Math.ceil(sumCount/(float)pageCount);
		
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}
	
	
}
