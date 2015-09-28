package com.fourfire.blog.dao;

import java.util.List;

public interface TagInfoDao {
	
	public List getAlltagInfo();
	public List getHotTagInfo();
	public List getTagListByArticleId(int id);
}
