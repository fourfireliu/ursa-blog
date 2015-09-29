package com.fourfire.blog.vo;

import java.io.Serializable;

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
				+ description + ", iconUrl=" + iconUrl + "]";
	}
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
	private long id;
	private String name;
	private String description;
	private String iconUrl;
}
