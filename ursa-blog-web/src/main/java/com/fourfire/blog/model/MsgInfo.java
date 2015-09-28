package com.fourfire.blog.model;

public class MsgInfo {
	
	private int id;
	private String userName;
	private String ip; 
	private String date;
	private String content;
	private String mail_qq;//邮箱或qq
	private String weburl;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMail_qq() {
		return mail_qq;
	}
	public void setMail_qq(String mail_qq) {
		this.mail_qq = mail_qq;
	}
	public String getWeburl() {
		return weburl;
	}
	public void setWeburl(String weburl) {
		this.weburl = weburl;
	}

}
