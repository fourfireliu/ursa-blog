package com.fourfire.blog.dao;

import java.util.List;

public interface CommentInfoDao {
	
	public List getNewTop10CommentInfo();
	
	public List getArticleCommentByArticleId(int id);

}
