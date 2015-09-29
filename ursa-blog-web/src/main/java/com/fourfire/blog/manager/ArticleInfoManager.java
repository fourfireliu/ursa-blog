package com.fourfire.blog.manager;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fourfire.blog.convert.ArticleInfoConverter;
import com.fourfire.blog.mapper.ArticleInfoMapper;
import com.fourfire.blog.po.ArticleInfoPO;
import com.fourfire.blog.vo.ArticleInfoVO;

/**
 * 文章相关操作的控制类, 与数据层交互
 * 
 * @author liuyi
 * @date 2015-09-29
 */
public class ArticleInfoManager {
	Logger logger = LogManager.getLogger(ArticleInfoManager.class);
	
	@Resource
	private ArticleInfoMapper articleInfoMapper;
	
	/**
	 * 修改或发表文章
	 */
	public boolean addOrUpdateArticle(ArticleInfoVO articleInfoVO) {
		ArticleInfoPO articleInfoPO = ArticleInfoConverter.convertVOToPO(articleInfoVO);
		if (articleInfoPO == null) {
			logger.info("addOrUpdateArticle==>articleInfoPO convert to null, articleInfoVO:\n" + articleInfoVO);
			return false;
		}
		
		if (articleInfoVO.isExist()) {
			int modifyCount = articleInfoMapper.updateById(articleInfoPO);
			if (modifyCount != 1) {
				logger.info("addOrUpdateArticle==>update failed, modifyCount: " + modifyCount + ", articleInfoPO:\n"
						+ articleInfoPO);
				return false;
			}
		} else {
			int result = articleInfoMapper.insert(articleInfoPO);
			if (result != 1) {
				logger.info("addOrUpdateArticle==>insert failed, insertCount: " + result + ", articleInfoPO:\n"
						+ articleInfoPO);
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * 根据ID获取文章详情
	 */
	public ArticleInfoVO getArticleInfoById(long id) {
		if (id <= 0) {
			return null;
		}
		
		ArticleInfoPO articleInfoPO = articleInfoMapper.getArticleInfoById(id);
		return ArticleInfoConverter.convertPOToVO(articleInfoPO);
	}
}
