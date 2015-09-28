package com.fourfire.blog.po;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章信息DO对象
 * 
 * @author fourfireliu
 * @date 2015-09-28
 */
public class ArticleInfoPO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1185509959242255363L;
	//ID
	private long id;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "ArticleInfoPO [id=" + id + ", type=" + type + ", title="
				+ title + ", content=" + content + ", author=" + author
				+ ", readCount=" + readCount + ", ip=" + ip
				+ ", createGmtDate=" + createGmtDate + ", modifyGmtDate="
				+ modifyGmtDate + "]";
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
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
	public Date getModifyGmtDate() {
		return modifyGmtDate;
	}
	public void setModifyGmtDate(Date modifyGmtDate) {
		this.modifyGmtDate = modifyGmtDate;
	}
	//文章类型
	private int type;
	//标题
	private String title;
	//内容
	private String content;
	//作者
	private String author;
	//浏览次数
	private int readCount;
	//ip地址
	private String ip;
	//创建日期
	private Date createGmtDate;
	//最新修改日期
	private Date modifyGmtDate;
}
