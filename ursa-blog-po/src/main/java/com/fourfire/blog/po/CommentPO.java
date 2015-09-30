package com.fourfire.blog.po;

import java.io.Serializable;
import java.util.Date;

public class CommentPO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3852214214142137906L; 
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "CommentPO [id=" + id + ", articleInfoId=" + articleInfoId
				+ ", userId=" + userId + ", content=" + content + ", ip=" + ip
				+ ", createGmtDate=" + createGmtDate + "]";
	}
	public long getArticleInfoId() {
		return articleInfoId;
	}
	public void setArticleInfoId(long articleInfoId) {
		this.articleInfoId = articleInfoId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getCreateGmtDate() {
		return createGmtDate;
	}
	public void setCreateGmtDate(Date createGmtDate) {
		this.createGmtDate = createGmtDate;
	}
	private long id;
	private long articleInfoId;
	private String userId;
	private String content;
	private String ip;
	private Date createGmtDate;
}
