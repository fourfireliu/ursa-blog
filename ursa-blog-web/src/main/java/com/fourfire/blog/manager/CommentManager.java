package com.fourfire.blog.manager;

import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.fourfire.blog.convert.CommentConverter;
import com.fourfire.blog.mapper.CommentMapper;
import com.fourfire.blog.po.CommentPO;
import com.fourfire.blog.vo.CommentVO;

public class CommentManager {
	private Logger logger = LogManager.getLogger(CommentManager.class);
	
	@Resource
	private CommentMapper commentMapper;
	
	public List<CommentVO> getCommentsByArticleId(long articleId) {
		List<CommentPO> commentPOList = commentMapper.getCommentsByArticleId(articleId);
		return CommentConverter.convertListFromPOToVO(commentPOList);
	}
	
	public boolean insertComment(CommentVO commentVO) {
		CommentPO commentPO = CommentConverter.convertVOToPO(commentVO);
		if (commentPO == null) {
			return false;
		}
		
		int result = commentMapper.insertComment(commentPO);
		if (result == 1) {
			return true;
		}
		
		return false;
	}
}
