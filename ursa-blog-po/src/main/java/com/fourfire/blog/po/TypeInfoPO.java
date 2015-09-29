package com.fourfire.blog.po;

import java.io.Serializable;

/**
 * 文章类别PO类
 * 
 * @author liuyi
 * @date 2015-09-29
 */
public class TypeInfoPO implements Serializable {
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
				+ description + ", iconUrl=" + iconUrl + "]";
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
	private long id;
	//类别名称
	private String name;
	//描述
	private String description;
	//图标链接
	private String iconUrl;
}
