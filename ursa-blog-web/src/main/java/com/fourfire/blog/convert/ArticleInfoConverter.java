package com.fourfire.blog.convert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fourfire.blog.po.ArticleInfoPO;
import com.fourfire.blog.vo.ArticleInfoVO;

public class ArticleInfoConverter {
	/**
	 * 业务VO对象转换为数据PO对象
	 */
	public static ArticleInfoPO convertVOToPO(ArticleInfoVO articleInfoVO) {
		if (articleInfoVO == null || articleInfoVO.getId() <= 0) {
			return null;
		}
		
		ArticleInfoPO articleInfoPO = new ArticleInfoPO();
		articleInfoPO.setId(articleInfoVO.getId());
		articleInfoPO.setAuthor(articleInfoVO.getAuthor());
		articleInfoPO.setContent(articleInfoVO.getContent());
		articleInfoPO.setIp(articleInfoVO.getIp());
		articleInfoPO.setReadCount(articleInfoVO.getReadCount());
		articleInfoPO.setTitle(articleInfoVO.getTitle());
		articleInfoPO.setType(articleInfoVO.getType());
		if (!articleInfoVO.isExist()) {
			articleInfoPO.setCreateGmtDate(new Date());
		}
		articleInfoPO.setModifyGmtDate(new Date());
		
		return articleInfoPO;
	}
	
	public static List<ArticleInfoVO> convertListFromPOToVO(List<ArticleInfoPO> articleInfoPOList) {
		List<ArticleInfoVO> articleInfoVOList = new ArrayList<ArticleInfoVO>();
		if (articleInfoPOList == null) {
			return articleInfoVOList;
		}
		
		for (ArticleInfoPO articleInfoPO:articleInfoPOList) {
			ArticleInfoVO articleInfoVO = convertPOToVO(articleInfoPO);
			if (articleInfoVO != null) {
				articleInfoVOList.add(articleInfoVO);
			}
		}
		
		return articleInfoVOList;
	}
	
	/**
	 * 数据PO对象转换为业务VO对象
	 */
	public static ArticleInfoVO convertPOToVO(ArticleInfoPO articleInfoPO) {
		if (articleInfoPO == null || articleInfoPO.getId() <= 0) {
			return null;
		}
		
		ArticleInfoVO articleInfoVO = new ArticleInfoVO();
		if (articleInfoPO.getCreateGmtDate() != null) {
			articleInfoVO.setExist(true);
		}
		articleInfoVO.setAuthor(articleInfoPO.getAuthor());
		articleInfoVO.setContent(articleInfoPO.getContent());
		articleInfoVO.setId(articleInfoPO.getId());
		articleInfoVO.setIp(articleInfoPO.getIp());
		articleInfoVO.setReadCount(articleInfoPO.getReadCount());
		articleInfoVO.setTitle(articleInfoPO.getTitle());
		articleInfoVO.setType(articleInfoPO.getType());
		
		return articleInfoVO;
	}
}
