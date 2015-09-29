package com.fourfire.blog.vo;

import java.io.Serializable;

/**
 * 博文的VO对象
 * 
 * @author liuyi
 * @date 2015-09-29
 */
public class ArticleInfoVO implements Serializable {

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "ArticleInfoVO [id=" + id + ", type=" + type + ", title="
				+ title + ", content=" + content + ", author=" + author
				+ ", readCount=" + readCount + ", ip=" + ip + ", isExist="
				+ isExist + "]";
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
	public boolean isExist() {
		return isExist;
	}
	public void setExist(boolean isExist) {
		this.isExist = isExist;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 779499642129233899L;
	
	//ID
	private long id;
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
	//是否已有博文
	private boolean isExist;
}
