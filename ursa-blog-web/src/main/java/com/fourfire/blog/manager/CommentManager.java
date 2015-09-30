package com.fourfire.blog.manager;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.fourfire.blog.convert.CommentConverter;
import com.fourfire.blog.mapper.CommentMapper;
import com.fourfire.blog.po.CommentPO;
import com.fourfire.blog.vo.CommentVO;

public class CommentManager {
	private Logger logger = LogManager.getLogger(CommentManager.class);
	
	@Autowired
	private CommentMapper commentMapper;
	
	public List<CommentVO> getCommentsByArticleId(long articleId) {
		List<CommentPO> commentPOList = commentMapper.getCommentsByArticleId(articleId);
		return CommentConverter.convertListFromPOToVO(commentPOList);
	}
}
