package com.fourfire.blog.controller;


import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fourfire.blog.manager.ArticleInfoManager;
import com.fourfire.blog.manager.TypeInfoManager;
import com.fourfire.blog.page.PageResult;
import com.fourfire.blog.util.Constants;
import com.fourfire.blog.vo.ArticleInfoVO;
import com.fourfire.blog.vo.TypeInfoVO;


@Controller
public class IndexController{
	Logger logger = LogManager.getLogger(IndexController.class);
	
	@Resource
	private TypeInfoManager typeInfoManager;

	@Resource
	private ArticleInfoManager articleInfoManager;
	
	@RequestMapping(value="/index")
	public String indexView(HttpServletRequest request,HttpServletResponse response){
		//导航
		List<TypeInfoVO> typeInfoVOList = typeInfoManager.getAllTypeInfos();
		request.setAttribute("typeList", typeInfoVOList);
		
		PageResult<ArticleInfoVO> hotResult = articleInfoManager.getHotArticles();
		List<ArticleInfoVO> hotArticleInfoVOList = new ArrayList<ArticleInfoVO>();
		if (hotResult != null) {
			hotArticleInfoVOList = hotResult.getPageResult();
		}
		request.setAttribute("hotArticleList", hotArticleInfoVOList);
		
		int pageNo = ServletRequestUtils.getIntParameter(request, "pageNo", Constants.DEFAULT_PAGE_NUM);
		int typeId = ServletRequestUtils.getIntParameter(request, "typeId", -1);
		PageResult<ArticleInfoVO> articlePageResult = articleInfoManager.pageQueryArticles(pageNo, Constants.DEFAULT_PAGE_SIZE, typeId);
		List<ArticleInfoVO> articleInfoVOList = new ArrayList<ArticleInfoVO>();
		if (articlePageResult != null) {
			articleInfoVOList = articlePageResult.getPageResult();
		}
		request.setAttribute("articleList", articleInfoVOList);
		
		return "index";
	 }
	
}
