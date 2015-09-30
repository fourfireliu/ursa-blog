package com.fourfire.blog.mapper;

import java.util.List;

import com.fourfire.blog.po.CommentPO;

public interface CommentMapper {
	public List<CommentPO> getCommentsByArticleId(long articleId);
}
