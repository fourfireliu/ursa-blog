package com.fourfire.blog.convert;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.fourfire.blog.enums.ArticleInfoType;
import com.fourfire.blog.po.ArticleInfoPO;
import com.fourfire.blog.util.Tools;
import com.fourfire.blog.vo.ArticleInfoVO;

public class ArticleInfoConverter {
	/**
	 * 业务VO对象转换为数据PO对象
	 * @throws UnsupportedEncodingException 
	 */
	public static ArticleInfoPO convertFromVOToPO(ArticleInfoVO articleInfoVO) throws UnsupportedEncodingException {
		if (articleInfoVO == null) {
			return null;
		}
		
		ArticleInfoPO articleInfoPO = new ArticleInfoPO();
		articleInfoPO.setId(articleInfoVO.getId());
		articleInfoPO.setAuthor(articleInfoVO.getAuthor());
		articleInfoPO.setContent(URLEncoder.encode(Tools.unknowCharacterFilter(articleInfoVO.getContent()), "UTF-8"));
		articleInfoPO.setIp(articleInfoVO.getIp());
		articleInfoPO.setReadCount(articleInfoVO.getReadCount());
		articleInfoPO.setCommentCount(articleInfoVO.getCommentCount());
		articleInfoPO.setTitle(articleInfoVO.getTitle());
		articleInfoPO.setTypeId(articleInfoVO.getType());
		articleInfoPO.setCreateGmtDate(articleInfoVO.getCreateDate());
		articleInfoPO.setModifyGmtDate(articleInfoVO.getModifyDate());
		
		return articleInfoPO;
	}
	
	public static List<ArticleInfoVO> convertListFromPOToVO(List<ArticleInfoPO> articleInfoPOList, ArticleInfoType articleInfoType) throws UnsupportedEncodingException {
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
	 * @throws UnsupportedEncodingException 
	 */
	public static ArticleInfoVO convertFromPOToVO(ArticleInfoPO articleInfoPO, ArticleInfoType articleInfoType) throws UnsupportedEncodingException {
		if (articleInfoPO == null || articleInfoPO.getId() <= 0) {
			return null;
		}
		
		ArticleInfoVO articleInfoVO = new ArticleInfoVO();
		articleInfoVO.setAuthor(articleInfoPO.getAuthor());
		if (ArticleInfoType.SHORT_CONTENT == articleInfoType) {
			articleInfoVO.setContent(Tools.getShortContent(articleInfoPO.getContent()));
		} else if (ArticleInfoType.ALL_CONTENT == articleInfoType) {
			articleInfoVO.setContent(URLDecoder.decode(articleInfoPO.getContent(), "UTF-8"));
		}
		articleInfoVO.setId(articleInfoPO.getId());
		articleInfoVO.setIp(articleInfoPO.getIp());
		articleInfoVO.setReadCount(articleInfoPO.getReadCount());
		articleInfoVO.setCommentCount(articleInfoPO.getCommentCount());
		articleInfoVO.setTitle(articleInfoPO.getTitle());
		articleInfoVO.setType(articleInfoPO.getTypeId());
		articleInfoVO.setCreateDate(articleInfoPO.getCreateGmtDate());
		articleInfoVO.setModifyDate(articleInfoPO.getModifyGmtDate());
		
		return articleInfoVO;
	}
}
