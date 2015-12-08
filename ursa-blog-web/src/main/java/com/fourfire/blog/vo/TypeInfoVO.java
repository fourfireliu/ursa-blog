package com.fourfire.blog.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章类别VO类
 * 
 * @author liuyi
 * @date 2015-09-29
 */
public class TypeInfoVO implements Serializable {
	@Override
	public String toString() {
		return "TypeInfoVO [id=" + id + ", name=" + name + ", description="
				+ description + ", iconUrl=" + iconUrl + ", articleCount="
				+ articleCount + ", createDate=" + createDate + ", modifyDate="
				+ modifyDate + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 2824872351469793671L;
	private int id;
	private String name;
	private String description;
	private String iconUrl;
	private int articleCount;
	private Date createDate;
	private Date modifyDate;
	public int getArticleCount() {
		return articleCount;
	}
	public void setArticleCount(int articleCount) {
		this.articleCount = articleCount;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
}
