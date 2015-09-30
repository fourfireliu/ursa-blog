package com.fourfire.blog.vo;

import java.io.Serializable;
import java.util.Date;

public class CommentVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8673956033824899826L;
	@Override
	public String toString() {
		return "CommentVO [id=" + id + ", articleId=" + articleId + ", userId="
				+ userId + ", content=" + content + ", ip=" + ip
				+ ", createGmtDate=" + createGmtDate + "]";
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getArticleId() {
		return articleId;
	}
	public void setArticleId(long articleId) {
		this.articleId = articleId;
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
	private long articleId;
	private String userId;
	private String content;
	private String ip;
	private Date createGmtDate;
}
