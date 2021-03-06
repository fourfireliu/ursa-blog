package com.fourfire.blog.po;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章类别PO类
 * 
 * @author liuyi
 * @date 2015-09-29
 */
public class TypeInfoPO implements Serializable {
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
	@Override
	public String toString() {
		return "TypeInfoPO [id=" + id + ", name=" + name + ", description="
				+ description + ", iconUrl=" + iconUrl + ", articleCount="
				+ articleCount + ", createGmtDate=" + createGmtDate
				+ ", modifyGmtDate=" + modifyGmtDate + "]";
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
	private static final long serialVersionUID = 2041994120791300839L;
	//分类ID
	private int id;
	//类别名称
	private String name;
	//描述
	private String description;
	//图标链接
	private String iconUrl;
	//分类下文章数
	private int articleCount;
	private Date createGmtDate;
	private Date modifyGmtDate;
	public int getArticleCount() {
		return articleCount;
	}
	public void setArticleCount(int articleCount) {
		this.articleCount = articleCount;
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
}
