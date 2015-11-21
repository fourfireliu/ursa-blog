package com.fourfire.blog.manager;

import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fourfire.blog.convert.ArticleInfoConverter;
import com.fourfire.blog.mapper.ArticleInfoPOMapper;
import com.fourfire.blog.page.ArticlePageQuery;
import com.fourfire.blog.page.PageResult;
import com.fourfire.blog.po.ArticleInfoPO;
import com.fourfire.blog.util.Constants;
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
	private ArticleInfoPOMapper articleInfoMapper;
	
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
			int modifyCount = articleInfoMapper.updateByPrimaryKey(articleInfoPO);
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
		
		ArticleInfoPO articleInfoPO = articleInfoMapper.selectByPrimaryKey(id);
		return ArticleInfoConverter.convertPOToVO(articleInfoPO);
	}
	
	/**
	 * 获取当前ID文章的上一篇
	 */
	public ArticleInfoVO getUpArticleInfo(long id) {
		if (id <= 0) {
			return null;
		}
		
		ArticleInfoPO articleInfoPO = articleInfoMapper.getUpArticleInfo(id);
		return ArticleInfoConverter.convertPOToVO(articleInfoPO);
	}
	
	/**
	 * 获取当前ID文章的下一篇
	 */
	public ArticleInfoVO getDownArticleInfo(long id) {
		if (id <= 0) {
			return null;
		}
		
		ArticleInfoPO articleInfoPO = articleInfoMapper.getDownArticleInfo(id);
		return ArticleInfoConverter.convertPOToVO(articleInfoPO);
	}
	
	/**
	 * 获取热门文章(点击率高的)
	 */
	public PageResult<ArticleInfoVO> getHotArticles() {
		ArticlePageQuery pageQuery = fillPageQuery(Constants.DEFAULT_PAGE_NUM, Constants.DEFAULT_PAGE_SIZE);
		pageQuery.setOrderByReadCount(true);
		
		PageResult<ArticleInfoVO> pageResult = new PageResult<ArticleInfoVO>();
		pageResult.setPageNo(pageQuery.getPageNo());
		pageResult.setPageSize(pageQuery.getOldPageSize());
		
		List<ArticleInfoPO> articleInfoPOList = articleInfoMapper.pageQuery(pageQuery);
		if (pageQuery.isCheckNextPage()) {
			if (articleInfoPOList != null && articleInfoPOList.size() > pageQuery.getOldPageSize()) {
				pageResult.setHasNext(true);
				articleInfoPOList.remove(articleInfoPOList.size() - 1);
			}
		}
		List<ArticleInfoVO> articleInfoVOList = ArticleInfoConverter.convertListFromPOToVO(articleInfoPOList);
		pageResult.setPageResult(articleInfoVOList);
		if (articleInfoVOList == null) {
			logger.error("getHotArticles==>articleInfoVOList: " + articleInfoVOList);
		} else {
			pageResult.setSuccess(true);
		}
		
		return pageResult;
	}
	
	/**
	 * 构建分页查询对象
	 */
	private ArticlePageQuery fillPageQuery(int pageNo, int pageSize) {
		ArticlePageQuery pageQuery = new ArticlePageQuery();
		pageQuery.setCheckNextPage(true);
		pageQuery.setPageNo(pageNo);
		pageQuery.setPageSize(pageSize);
		
		return pageQuery;
	}
	
	/**
	 * 分页获取文章列表, 若typeId>0 则获取相关分类的文章
	 */
	public PageResult<ArticleInfoVO> pageQueryArticles(int pageNo, int pageSize, int typeId) {
		ArticlePageQuery pageQuery = new ArticlePageQuery();
		pageQuery.setPageNo(pageNo);
		pageQuery.setPageSize(pageSize);
		pageQuery.setTypeId(typeId);
		pageQuery.setCheckNextPage(true);
		
		PageResult<ArticleInfoVO> pageResult = new PageResult<ArticleInfoVO>();
		pageResult.setPageNo(pageQuery.getPageNo());
		pageResult.setPageSize(pageQuery.getOldPageSize());
		
		List<ArticleInfoPO> articleInfoPOList = articleInfoMapper.pageQuery(pageQuery);
		if (pageQuery.isCheckNextPage()) {
			if (articleInfoPOList != null && articleInfoPOList.size() > pageQuery.getOldPageSize()) {
				pageResult.setHasNext(true);
				articleInfoPOList.remove(articleInfoPOList.size() - 1);
			}
		}
		List<ArticleInfoVO> articleInfoVOList = ArticleInfoConverter.convertListFromPOToVO(articleInfoPOList);
		pageResult.setPageResult(articleInfoVOList);
		if (articleInfoVOList == null) {
			logger.error("pageQueryArticles==>articleInfoVOList: " + articleInfoVOList);
		} else {
			pageResult.setSuccess(true);
		}
		
		return pageResult;
	}
	
	public boolean addReadCountByArticleId(long id) {
		if (id <= 0) {
			return false;
		}
		
		int result = articleInfoMapper.addReadCountById(id);
		if (result == 1) {
			return true;
		}
		
		return false;
	}
}
