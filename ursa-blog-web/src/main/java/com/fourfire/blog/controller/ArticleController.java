package com.fourfire.blog.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fourfire.blog.constant.BlogConstant;
import com.fourfire.blog.enums.ArticleInfoType;
import com.fourfire.blog.manager.ArticleInfoManager;
import com.fourfire.blog.manager.BlackIpManager;
import com.fourfire.blog.manager.CommentManager;
import com.fourfire.blog.manager.TypeInfoManager;
import com.fourfire.blog.result.BaseResult;
import com.fourfire.blog.result.PageResult;
import com.fourfire.blog.util.Tools;
import com.fourfire.blog.vo.ArticleInfoVO;
import com.fourfire.blog.vo.CommentVO;
import com.fourfire.blog.vo.TypeInfoVO;

@Controller
public class ArticleController {
	Logger logger = LogManager.getLogger(ArticleController.class);
	
	@Resource
	private ArticleInfoManager articleInfoManager;
	@Resource
	private TypeInfoManager typeInfoManager;
	@Resource
	private CommentManager commentManager;
	@Resource
	private BlackIpManager blackIpManager;
	
	@RequestMapping(value = "/articlelist", method=RequestMethod.POST)
	public String getArticleList(ModelMap modelMap, Integer typeId, String typeName, String typeDesc, Integer pageNo, Integer pageSize) {
		long begin = System.currentTimeMillis();
		logger.info("getArticleList method begin");
		
		if (pageNo == null || pageNo <= 0) {
			pageNo = 1;
		}
		
		if (pageSize == null || pageSize <= 0) {
			pageSize = BlogConstant.DEFAULT_ARTICLE_PAGE_SIZE;
		}
		
		modelMap.put("pageNo", pageNo);
		modelMap.put("pageSize", pageSize);
		
		if (typeId != null && typeId > 0) {
			modelMap.put("typeId", typeId);
			if (StringUtils.isBlank(typeName) || StringUtils.isBlank(typeDesc)) {
				BaseResult<TypeInfoVO> typeInfoResult = typeInfoManager.getTypeInfoById(typeId);
				if (typeInfoResult != null && typeInfoResult.isSuccess() && typeInfoResult.getT() != null) {
					typeName = typeInfoResult.getT().getName();
					typeDesc = typeInfoResult.getT().getDescription();
				}
			}
			modelMap.put("typeName", typeName);
			modelMap.put("typeDesc", typeDesc);
		}
		
		PageResult<ArticleInfoVO> pageResult = articleInfoManager.pageQueryArticles(1, BlogConstant.DEFAULT_ARTICLE_PAGE_SIZE, typeId, "create_gmt_date desc", ArticleInfoType.SHORT_CONTENT);
		if (pageResult != null && pageResult.isSuccess()) {
			modelMap.put("articleList", pageResult.getPageResult());
			modelMap.put("hasNext", pageResult.isHasNext());
		}
		
		BaseResult<Integer> baseResult = articleInfoManager.getArticleCount(typeId);
		if (baseResult != null && baseResult.isSuccess()) {
			modelMap.put("totalCount", baseResult.getT());
		}
		
		long end = System.currentTimeMillis();
		logger.info("getArticleList method end, cost time=" + (end - begin) + "ms");
		return "page/articlelist";
	}
	
	@RequestMapping(value = "/article/{id}", method=RequestMethod.GET)
	public String viewArticle(@PathVariable Long id, ModelMap modelMap) {
		long begin = System.currentTimeMillis();
		logger.info("viewArticle method begin");
		
		try {
			if (id <= 0) {
				logger.error("invalid article id=" + id);
			} else {
				BaseResult<ArticleInfoVO> articleResult = articleInfoManager.getArticleInfoById(id);
				if (articleResult != null) {
					int typeId = -1;
					if (articleResult.isSuccess()) {
						ArticleInfoVO articleInfo = articleResult.getT();
						if (articleInfo != null) {
							articleInfo.setReadCount(articleInfo.getReadCount()+1);
							//获取博客文章详情
							modelMap.put("articleInfo", articleInfo);
							//更新阅读数
							articleInfoManager.updateArticleInfo(articleInfo);
							typeId = articleResult.getT().getType();
							if (typeId <= 0) {
								logger.error("invalid type id=" + typeId);
							} else {
								BaseResult<TypeInfoVO> typeResult = typeInfoManager.getTypeInfoById(typeId);
								if (typeResult != null) {
									if (typeResult.isSuccess()) {
										//获取博客分类信息详情
										modelMap.put("typeInfo", typeResult.getT());
									} else {
										logger.error("getTypeInfoById error:" + ((typeResult.getErrorInfo() != null) ? typeResult.getErrorInfo().toString() : "no errorInfo, result=" + typeResult.getT()));
									}
								}
							}
						}
						
						//前一篇
						BaseResult<ArticleInfoVO> previousArticleResult = articleInfoManager.getUpOrDownArticleInfo(id, typeId, true);
						if (previousArticleResult != null) {
							if (previousArticleResult.isSuccess()) {
								if (previousArticleResult.getT() != null) {
									modelMap.put("previousArticle", previousArticleResult.getT());
								}
							} else {
								logger.error("getUpOrDownArticleInfo error:" + ((previousArticleResult.getErrorInfo() != null) ? previousArticleResult.getErrorInfo().toString() : "no errorInfo, result=" + previousArticleResult.getT()));
							}
						}
						//后一篇
						BaseResult<ArticleInfoVO> nextArticleResult = articleInfoManager.getUpOrDownArticleInfo(id, typeId, false);
						if (nextArticleResult != null) {
							if (nextArticleResult.isSuccess()) {
								if (nextArticleResult.getT() != null) {
									modelMap.put("nextArticle", nextArticleResult.getT());
								}
							} else {
								logger.error("getUpOrDownArticleInfo error:" + ((nextArticleResult.getErrorInfo() != null) ? nextArticleResult.getErrorInfo().toString() : "no errorInfo, result=" + nextArticleResult.getT()));
							}
						}
					} else {
						if (articleResult.getErrorInfo() != null) {
							logger.error("method error:" + articleResult.getErrorInfo().toString());
						} else {
							logger.error("method unknown error, result=" + articleResult.getT());
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("unknown error", e);
		}
		
		long end = System.currentTimeMillis();
		logger.info("viewArticle method end, cost time=" + (end - begin) + "ms");
		return "page/article";
	}

	@RequestMapping(value = "/submitComment")
	public String submitComment(HttpServletRequest request,HttpServletResponse response) {
		return null;
	}
}
