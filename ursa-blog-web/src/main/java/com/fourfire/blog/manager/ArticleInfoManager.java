package com.fourfire.blog.manager;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fourfire.blog.convert.ArticleInfoConverter;
import com.fourfire.blog.enums.ArticleInfoType;
import com.fourfire.blog.mapper.ArticleInfoPOMapper;
import com.fourfire.blog.po.ArticleInfoPO;
import com.fourfire.blog.query.ArticlePageQuery;
import com.fourfire.blog.result.BaseResult;
import com.fourfire.blog.result.ErrorInfo;
import com.fourfire.blog.result.PageResult;
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
	private ArticleInfoPOMapper articleInfoPOMapper;
	
	/**
	 * 修改或发表文章
	 */
	public BaseResult<ArticleInfoVO> addOrUpdateArticle(ArticleInfoVO articleInfoVO) {
		BaseResult<ArticleInfoVO> baseResult = new BaseResult<ArticleInfoVO>();
		
		try {
			ArticleInfoPO articleInfoPO = ArticleInfoConverter.convertFromVOToPO(articleInfoVO);
			if (articleInfoPO == null) {
				logger.error("addOrUpdateArticle==>articleInfoPO convert to null, articleInfoVO:\n" + articleInfoVO);
				baseResult.setErrorInfo(ErrorInfo.INVALID_PARAM);
				return baseResult;
			}
			
			if (articleInfoPO.getId() <= 0) {
				articleInfoPOMapper.insert(articleInfoPO);
				articleInfoVO.setId(articleInfoPO.getId());
			} else {
				int modifyCount = articleInfoPOMapper.updateByPrimaryKey(articleInfoPO);
				if (modifyCount != 1) {
					logger.error("addOrUpdateArticle==>update failed, modifyCount: " + modifyCount + ", articleInfoPO:\n"
							+ articleInfoPO);
					baseResult.setErrorInfo(ErrorInfo.UPDATE_DB_RESULT_ERROR);
					return baseResult;
				}
			}
		} catch (Exception e) {
			logger.error("addOrUpdateArticle==>unknown error", e);
			baseResult.setErrorInfo(ErrorInfo.SYSTEM_ERROR);
			return baseResult;
		}
		
		baseResult.setT(articleInfoVO);
		baseResult.setSuccess(true);
		return baseResult;
	}
	
	/**
	 * 获取指定分类的文章总数, 若typeId为-1时, 返回所有分类的文章总数
	 */
	public BaseResult<Integer> getArticleCount(int typeId) {
		logger.info("getArticleCount param: typeId=" + typeId);
		BaseResult<Integer> baseResult = new BaseResult<Integer>();
		try {
			baseResult.setT(articleInfoPOMapper.getArticleCount(typeId));
			baseResult.setSuccess(true);
		} catch (Exception e) {
			logger.error("getArticleCount==>unknown error", e);
			baseResult.setErrorInfo(ErrorInfo.SYSTEM_ERROR);
		}
		logger.info("getArticleCount result: baseResult=" + baseResult);
		
		return baseResult;
	}
	
	/**
	 * 根据ID获取文章详情
	 * @throws UnsupportedEncodingException 
	 */
	public BaseResult<ArticleInfoVO> getArticleInfoById(long articleId) throws UnsupportedEncodingException {
		BaseResult<ArticleInfoVO> baseResult = new BaseResult<ArticleInfoVO>();
		if (articleId <= 0) {
			baseResult.setErrorInfo(ErrorInfo.INVALID_PARAM);
			return baseResult;
		}
		
		ArticleInfoPO articleInfoPO = articleInfoPOMapper.selectByPrimaryKey(articleId);
		ArticleInfoVO articleInfoVO = ArticleInfoConverter.convertFromPOToVO(articleInfoPO, ArticleInfoType.ALL_CONTENT);
		if (articleInfoVO == null) {
			logger.error("getArticleInfoById==>articleInfoVO:" + articleInfoVO);
			baseResult.setErrorInfo(ErrorInfo.INVALID_RESULT);
		} else {
			baseResult.setSuccess(true);
		}
		baseResult.setT(articleInfoVO);
		
		return baseResult;
	}
	
	/**
	 * 根据ID修改文章详情
	 * 
	 * @param articleInfoVO
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public BaseResult<ArticleInfoVO> updateArticleInfo(ArticleInfoVO articleInfoVO) throws UnsupportedEncodingException {
		BaseResult<ArticleInfoVO> baseResult = new BaseResult<ArticleInfoVO>();
		ArticleInfoPO articleInfoPO = ArticleInfoConverter.convertFromVOToPO(articleInfoVO);
		if (articleInfoPO == null || articleInfoPO.getId() <= 0) {
			baseResult.setErrorInfo(ErrorInfo.INVALID_PARAM);
			return baseResult;
		}
		
		int modifyCount = articleInfoPOMapper.updateByPrimaryKey(articleInfoPO);
		if (modifyCount == 1) {
			baseResult.setSuccess(true);
			baseResult.setT(articleInfoVO);
		} else {
			logger.error("updateArticleInfo==>modifyCount:" + modifyCount);
			baseResult.setErrorInfo(ErrorInfo.INVALID_RESULT);
		}
		
		return baseResult;
	}
	
	/**
	 * 获取当前ID文章的上一篇/下一篇
	 * @throws UnsupportedEncodingException 
	 */
	public BaseResult<ArticleInfoVO> getUpOrDownArticleInfo(long articleId, int typeId, boolean isUp) throws UnsupportedEncodingException {
		BaseResult<ArticleInfoVO> baseResult = new BaseResult<ArticleInfoVO>();
		if (articleId <= 0 || typeId <= 0) {
			baseResult.setErrorInfo(ErrorInfo.INVALID_PARAM);
			return baseResult;
		}
		
		ArticleInfoPO articleInfoPO = articleInfoPOMapper.getUpOrDownArticleInfo(articleId, typeId, isUp);
		if (articleInfoPO == null) {
			baseResult.setSuccess(true);
		} else {
			ArticleInfoVO articleInfoVO = ArticleInfoConverter.convertFromPOToVO(articleInfoPO, ArticleInfoType.NO_CONTENT);
			if (articleInfoVO == null) {
				logger.error("getUpOrDownArticleInfo==>articleInfoVO:" + articleInfoVO);
				baseResult.setErrorInfo(ErrorInfo.INVALID_RESULT);
			} else {
				baseResult.setSuccess(true);
				baseResult.setT(articleInfoVO);
			}
		}
		
		return baseResult;
	}
	
	/**
	 * 分页获取文章列表, 若typeId>0 则获取相关分类的文章
	 * @throws UnsupportedEncodingException 
	 */
	public PageResult<ArticleInfoVO> pageQueryArticles(int pageNo, int pageSize, int typeId, String orderByColumn, ArticleInfoType articleInfoType) throws UnsupportedEncodingException {
		ArticlePageQuery pageQuery = new ArticlePageQuery();
		pageQuery.setPageNo(pageNo);
		pageQuery.setPageSize(pageSize);
		if (typeId > 0) {
			pageQuery.setTypeId(typeId);
		}
		pageQuery.setCheckNextPage(true);
		if (StringUtils.isNotBlank(orderByColumn)) {
			pageQuery.setOrderByColumn(orderByColumn);
		}
		
		PageResult<ArticleInfoVO> pageResult = new PageResult<ArticleInfoVO>();
		pageResult.setPageNo(pageQuery.getPageNo());
		pageResult.setPageSize(pageQuery.getOldPageSize());
		
		List<ArticleInfoPO> articleInfoPOList = articleInfoPOMapper.pageQuery(pageQuery);
		if (pageQuery.isCheckNextPage()) {
			if (articleInfoPOList != null && articleInfoPOList.size() > pageQuery.getOldPageSize()) {
				pageResult.setHasNext(true);
				articleInfoPOList.remove(articleInfoPOList.size() - 1);
			}
		}
		List<ArticleInfoVO> articleInfoVOList = ArticleInfoConverter.convertListFromPOToVO(articleInfoPOList, articleInfoType);
		pageResult.setPageResult(articleInfoVOList);
		if (articleInfoVOList == null) {
			logger.error("pageQueryArticles==>articleInfoVOList: " + articleInfoVOList);
			pageResult.setErrorInfo(ErrorInfo.INVALID_RESULT);
		} else {
			pageResult.setSuccess(true);
		}
		
		return pageResult;
	}
}
