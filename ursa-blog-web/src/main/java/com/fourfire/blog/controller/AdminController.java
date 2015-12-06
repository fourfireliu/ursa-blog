package com.fourfire.blog.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fourfire.blog.entity.BaseResult;
import com.fourfire.blog.manager.ArticleInfoManager;
import com.fourfire.blog.manager.TypeInfoManager;
import com.fourfire.blog.page.PageResult;
import com.fourfire.blog.util.Constants;
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
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String get(HttpServletRequest request, HttpServletResponse response) {
		long begin = System.currentTimeMillis();
		logger.info("");
		List<TypeInfoVO> typeInfoVOs = typeInfoManager.getAllTypeInfos();
		
		return "index";
	}

	/**
	 * 提交发文或者编辑文章
	 */
	@RequestMapping(value = "/article/submit", method = RequestMethod.POST)
	public String addAritcle(HttpServletRequest request,
			HttpServletResponse response) {
		ArticleInfoVO articleInfoVO = new ArticleInfoVO();
		
		String op = ServletRequestUtils.getStringParameter(request, "op", "");
		//修改文章
		if ("update".equalsIgnoreCase(op)) {
			articleInfoVO.setId(ServletRequestUtils.getLongParameter(request, "id", 0L));
			articleInfoVO.setReadCount(0);
		} 
		//文章类型
		articleInfoVO.setType(ServletRequestUtils.getIntParameter(request, "typeId", 0));
		//文章标题
		articleInfoVO.setTitle(Tools.checkTitle(request.getParameter("title")));
		//文章内容
		articleInfoVO.setContent(ServletRequestUtils.getStringParameter(request,
				"content", "").replace("'", "&#39;"));
		//文章作者
		articleInfoVO.setAuthor(Constants.BLOG_HOST);
		//访问者IP
		articleInfoVO.setIp(request.getRemoteAddr());
		
		boolean isNewArticle = (articleInfoVO.getId() <= 0);
		BaseResult<ArticleInfoVO> result = articleInfoManager.addOrUpdateArticle(articleInfoVO);
		if (result.isSuccess()) {
			if (!isNewArticle) {
				request.setAttribute("message", "更新成功...");
			} else {
				request.setAttribute("message", "添加成功...");
			}
		} else {
			if (!isNewArticle) {
				request.setAttribute("message", "更新失败...");
			} else {
				request.setAttribute("message", "添加失败...");
			}
		}
		
		return "/error/index";
	}

	/**
	 * 新建文章或编辑文章界面
	 */
	@RequestMapping(value = "/article/edit")
	public String getArticle(HttpServletRequest request,
			HttpServletResponse response) {
		String op = ServletRequestUtils.getStringParameter(request, "op", "");
		List<TypeInfoVO> typeList = typeInfoManager.getAllTypeInfos();
		request.setAttribute("typeList", typeList);
		if ("add".equalsIgnoreCase(op)) {
			return "/admin/article/article";
		}

		long id = ServletRequestUtils.getLongParameter(request, "id", 0L);
		ArticleInfoVO articleInfoVO = articleInfoManager.getArticleInfoById(id);
		request.setAttribute("article", articleInfoVO);

		return "/admin/article/article";
	}

	/**
	 * 获取文章列表(简单信息无需内容)
	 */
	@RequestMapping(value = "/article/articleList")
	public String getArticleList(HttpServletRequest request,
			HttpServletResponse response) {
		int pageNo = ServletRequestUtils.getIntParameter(request, "pageNo", Constants.DEFAULT_PAGE_NUM);
		int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize", Constants.DEFAULT_PAGE_SIZE);
		
		PageResult<ArticleInfoVO> pageResult = articleInfoManager.pageQueryArticles(pageNo, pageSize, -1);
		request.setAttribute("hasNext", pageResult.isHasNext());
		request.setAttribute("articleInfoList", pageResult.getPageResult());
		
		return "/admin/article/articleList";
	}

	@RequestMapping(value = "/login")
	public String checkLogin(HttpServletRequest request,
			HttpServletResponse response) {

		return "/admin/index";
	}

	@RequestMapping(value = "/index")
	public String index(ModelMap modelMap) {
		long begin = System.currentTimeMillis();
		logger.info("index method begin");
		
		try {
			PageResult<TypeInfoVO> pageResult = typeInfoManager.pageQueryTypeInfos(0, 3);
			if (pageResult != null)
			if (CollectionUtils.isEmpty(typeInfoVOs)) {
				logger.info("index method get type list=" + typeInfoVOs);
			} else {
				modelMap.put("typeInfos", typeInfoVOs);
				if (typeInfoVOs.get(0) != null) {
					int defaultTypeId = typeInfoVOs.get(0).getId();
					articleInfoManager.pageQueryArticles(0, 3, defaultTypeId);
				}
			}
		} catch (Exception e) {
			logger.error("unknown exception", e);
		}
		
		long end = System.currentTimeMillis();
		logger.info("index method end, cost time=" + (end - begin) + "ms");
		return "index";
	}
}
