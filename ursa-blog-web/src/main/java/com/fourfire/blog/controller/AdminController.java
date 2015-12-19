package com.fourfire.blog.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fourfire.blog.constant.BlogConstant;
import com.fourfire.blog.manager.ArticleInfoManager;
import com.fourfire.blog.manager.TypeInfoManager;
import com.fourfire.blog.result.BaseResult;
import com.fourfire.blog.util.Tools;
import com.fourfire.blog.vo.ArticleInfoVO;
import com.fourfire.blog.vo.TypeInfoVO;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	Logger logger = LogManager.getLogger(AdminController.class);
	
	@Resource
	private ArticleInfoManager articleInfoManager;
	@Resource
	private TypeInfoManager typeInfoManager;
	
	/**
	 * 提交发文或者编辑文章
	 */	
	@RequestMapping(value = "/newarticle/submit", method = RequestMethod.POST)
	public String addAritcle(HttpServletRequest request, ModelMap modelMap, String title, String content, Integer selectTypeId) {
		if (StringUtils.isBlank(title) || StringUtils.isBlank(content) || selectTypeId == null) {
			logger.error("invalid parameter: title={}, content={}, selectTypeId={}", title, content, selectTypeId);
			modelMap.put("info", "发文失败");
			return "page/middleDirect";
		}
		
		try {
			ArticleInfoVO articleInfoVO = new ArticleInfoVO();
			articleInfoVO.setAuthor(BlogConstant.DEFAULT_AUTHOR);
			articleInfoVO.setContent(Tools.checkHtmlContent(content));
			articleInfoVO.setCreateDate(new Date());
			articleInfoVO.setIp(Tools.getIp(request));
			articleInfoVO.setModifyDate(new Date());
			articleInfoVO.setTitle(Tools.checkHtmlContent(title));
			articleInfoVO.setType(selectTypeId);
			
			BaseResult<ArticleInfoVO> result = articleInfoManager.addOrUpdateArticle(articleInfoVO);
			if (result != null && result.isSuccess() && result.getT() != null) {
				typeInfoManager.addArticleCountInType(selectTypeId, 1);
				modelMap.put("info", "发文成功");
				return "page/middleDirect";
			} else {
				logger.error("add article failed, result=" + result);
			}
		} catch (Exception e) {
			logger.error("unknown error", e);
		}
		
		modelMap.put("info", "发文失败");
		return "page/middleDirect";
	}

	/**
	 * 新建文章或编辑文章界面
	 */
	@RequestMapping(value = "/article/edit")
	public String getArticle(HttpServletRequest request,
			HttpServletResponse response) {
		String op = ServletRequestUtils.getStringParameter(request, "op", "");

		long id = ServletRequestUtils.getLongParameter(request, "id", 0L);
		

		return "/admin/article/article";
	}

	/**
	 * 获取文章列表(简单信息无需内容)
	 */
	@RequestMapping(value = "/article/articleList")
	public String getArticleList(HttpServletRequest request,
			HttpServletResponse response) {
//		int pageNo = ServletRequestUtils.getIntParameter(request, "pageNo", Constants.DEFAULT_PAGE_NUM);
//		int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize", Constants.DEFAULT_PAGE_SIZE);
//		
//		PageResult<ArticleInfoVO> pageResult = articleInfoManager.pageQueryArticles(pageNo, pageSize, -1, null);
//		request.setAttribute("hasNext", pageResult.isHasNext());
//		request.setAttribute("articleInfoList", pageResult.getPageResult());
//		
		return "/admin/article/articleList";
	}

	@RequestMapping(value = "/login")
	public String checkLogin(HttpServletRequest request,
			HttpServletResponse response) {

		return "/admin/index";
	}

	@RequestMapping(value = "/writearticle")
	public String writeBlog(ModelMap modelMap) {
		long begin = System.currentTimeMillis();
		logger.info("wrtieBlog method begin");
		
		try {
			List<TypeInfoVO> typeInfoVOs = typeInfoManager.getAllTypeInfos();
			if (typeInfoVOs == null) {
				logger.error("writeArticle method get type list null");
			} else {
				//置顶文章类型列表
				modelMap.put("typeInfos", typeInfoVOs);
			}
		} catch (Exception e) {
			logger.error("unknown exception", e);
		}
		
		long end = System.currentTimeMillis();
		logger.info("writeBlog method end, cost time=" + (end - begin) + "ms");
		return "page/writearticle";
	}
}
