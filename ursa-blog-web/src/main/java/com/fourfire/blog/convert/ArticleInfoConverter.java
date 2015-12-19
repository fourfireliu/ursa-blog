package com.fourfire.blog.convert;

import java.util.ArrayList;
import java.util.List;

import com.fourfire.blog.enums.ArticleInfoType;
import com.fourfire.blog.po.ArticleInfoPO;
import com.fourfire.blog.util.Tools;
import com.fourfire.blog.vo.ArticleInfoVO;

public class ArticleInfoConverter {
	/**
	 * 业务VO对象转换为数据PO对象
	 */
	public static ArticleInfoPO convertFromVOToPO(ArticleInfoVO articleInfoVO) {
		if (articleInfoVO == null) {
			return null;
		}
		
		ArticleInfoPO articleInfoPO = new ArticleInfoPO();
		articleInfoPO.setId(articleInfoVO.getId());
		articleInfoPO.setAuthor(articleInfoVO.getAuthor());
		articleInfoPO.setContent(Tools.checkHtmlContent(articleInfoVO.getContent()));
		articleInfoPO.setIp(articleInfoVO.getIp());
		articleInfoPO.setReadCount(articleInfoVO.getReadCount());
		articleInfoPO.setCommentCount(articleInfoVO.getCommentCount());
		articleInfoPO.setTitle(Tools.checkHtmlContent(articleInfoVO.getTitle()));
		articleInfoPO.setTypeId(articleInfoVO.getType());
		articleInfoPO.setCreateGmtDate(articleInfoVO.getCreateDate());
		articleInfoPO.setModifyGmtDate(articleInfoVO.getModifyDate());
		
		return articleInfoPO;
	}
	
	public static List<ArticleInfoVO> convertListFromPOToVO(List<ArticleInfoPO> articleInfoPOList, ArticleInfoType articleInfoType) {
		List<ArticleInfoVO> articleInfoVOList = new ArrayList<ArticleInfoVO>();
		if (articleInfoPOList == null) {
			return articleInfoVOList;
		}
		
		for (ArticleInfoPO articleInfoPO:articleInfoPOList) {
			ArticleInfoVO articleInfoVO = convertFromPOToVO(articleInfoPO, articleInfoType);
			if (articleInfoVO != null) {
				articleInfoVOList.add(articleInfoVO);
			}
		}
		
		return articleInfoVOList;
	}
	
	/**
	 * 数据PO对象转换为业务VO对象
	 */
	public static ArticleInfoVO convertFromPOToVO(ArticleInfoPO articleInfoPO, ArticleInfoType articleInfoType) {
		if (articleInfoPO == null || articleInfoPO.getId() <= 0) {
			return null;
		}
		
		ArticleInfoVO articleInfoVO = new ArticleInfoVO();
		articleInfoVO.setAuthor(articleInfoPO.getAuthor());
		if (ArticleInfoType.SHORT_CONTENT == articleInfoType) {
			articleInfoVO.setContent(Tools.getShortContent(articleInfoPO.getContent()));
		} else if (ArticleInfoType.ALL_CONTENT == articleInfoType) {
			articleInfoVO.setContent(Tools.changeToHtmlContent(articleInfoPO.getContent()));
		}
		articleInfoVO.setId(articleInfoPO.getId());
		articleInfoVO.setIp(articleInfoPO.getIp());
		articleInfoVO.setReadCount(articleInfoPO.getReadCount());
		articleInfoVO.setCommentCount(articleInfoPO.getCommentCount());
		articleInfoVO.setTitle(Tools.changeToHtmlContent(articleInfoPO.getTitle()));
		articleInfoVO.setType(articleInfoPO.getTypeId());
		articleInfoVO.setCreateDate(articleInfoPO.getCreateGmtDate());
		articleInfoVO.setModifyDate(articleInfoPO.getModifyGmtDate());
		
		return articleInfoVO;
	}
}
