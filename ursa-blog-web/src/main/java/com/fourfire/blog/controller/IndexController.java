package com.fourfire.blog.controller;


import java.util.List;

import javax.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fourfire.blog.constant.BlogConstant;
import com.fourfire.blog.enums.ArticleInfoType;
import com.fourfire.blog.manager.ArticleInfoManager;
import com.fourfire.blog.manager.TypeInfoManager;
import com.fourfire.blog.page.PageResult;
import com.fourfire.blog.vo.ArticleInfoVO;
import com.fourfire.blog.vo.TypeInfoVO;


@Controller
public class IndexController{
	Logger logger = LogManager.getLogger(IndexController.class);
	
	@Resource
	private TypeInfoManager typeInfoManager;

	@Resource
	private ArticleInfoManager articleInfoManager;
	
	//首页
	@RequestMapping(value = "/index")
	public String index(ModelMap modelMap) {
		long begin = System.currentTimeMillis();
		logger.info("index method begin");
		
		try {
			List<TypeInfoVO> typeInfoVOs = typeInfoManager.getAllTypeInfos();
			if (typeInfoVOs == null) {
				logger.error("index method get type list null");
			} else {
				//置顶文章类型列表
				modelMap.put("typeInfos", typeInfoVOs);
				PageResult<ArticleInfoVO> articleInfoPageResult = articleInfoManager.pageQueryArticles(0, BlogConstant.DEFAULT_ARTICLE_LIST_SIZE, -1, null, ArticleInfoType.SHORT_CONTENT);
				if (articleInfoPageResult != null && articleInfoPageResult.isSuccess() && articleInfoPageResult.getPageResult() != null) {
					//置顶文章 按评论数排序
					modelMap.put("topArticles", articleInfoPageResult.getPageResult());
				}
			}
			
			//获取最新文章列表
			PageResult<ArticleInfoVO> newArticleInfoPageResult = articleInfoManager.pageQueryArticles(0, BlogConstant.DEFAULT_ARTICLE_LIST_SIZE, -1, "modify_gmt_date desc", ArticleInfoType.NO_CONTENT);
			if (newArticleInfoPageResult != null && newArticleInfoPageResult.isSuccess() && newArticleInfoPageResult.getPageResult() != null) {
				modelMap.put("newArticles", newArticleInfoPageResult.getPageResult());
			}
			
		} catch (Exception e) {
			logger.error("unknown exception", e);
		}
		
		long end = System.currentTimeMillis();
		logger.info("index method end, cost time=" + (end - begin) + "ms");
		return "index";
	}
	
}
