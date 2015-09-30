package com.fourfire.blog.convert;

import java.util.ArrayList;
import java.util.List;

import com.fourfire.blog.po.CommentPO;
import com.fourfire.blog.vo.CommentVO;

public class CommentConverter {
	public static CommentVO convertPOToVO(CommentPO commentPO) {
		if (commentPO == null || commentPO.getId() <= 0) {
			return null;
		}
		
		CommentVO commentVO = new CommentVO();
		commentVO.setArticleId(commentPO.getArticleInfoId());
		commentVO.setContent(commentPO.getContent());
		commentVO.setCreateGmtDate(commentPO.getCreateGmtDate());
		commentVO.setId(commentPO.getId());
		commentVO.setIp(commentPO.getIp());
		commentVO.setUserId(commentPO.getUserId());
		
		return commentVO;
	}
	
	public static List<CommentVO> convertListFromPOToVO(List<CommentPO> commentPOList) {
		List<CommentVO> commentVOList = new ArrayList<CommentVO>();
		if (commentPOList != null) {
			for (CommentPO commentPO:commentPOList) {
				CommentVO commentVO = convertPOToVO(commentPO);
				if (commentVO != null) {
					commentVOList.add(commentVO);
				}
			}
		}
		
		return commentVOList;
	}
}
