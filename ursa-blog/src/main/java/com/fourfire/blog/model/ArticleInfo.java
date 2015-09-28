package com.fourfire.blog.model;

public class ArticleInfo {
	
	private int id;
	private int type;//类型,0-心情,1-日志，2-公告
	private TypeInfo typeInfo;
	private String title;
	private String content;
	private String author;
	private int readCount;
	private int status;//状态
	private int orderby;//排序
	private String ip;
	private String date;
	private int isCommend; //是否推荐
	
	public int getIsCommend() {
		return isCommend;
	}
	public void setIsCommend(int isCommend) {
		this.isCommend = isCommend;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public TypeInfo getTypeInfo() {
		return typeInfo;
	}
	public void setTypeInfo(TypeInfo typeInfo) {
		this.typeInfo = typeInfo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getOrderby() {
		return orderby;
	}
	public void setOrderby(int orderby) {
		this.orderby = orderby;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

}
