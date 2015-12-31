package com.fourfire.blog.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	public String submitAritcle(HttpServletRequest request, ModelMap modelMap, String title, String content, Integer selectTypeId, Long articleId) {
		long begin = System.currentTimeMillis();
		logger.info("addAritcle method begin");
		
		if (StringUtils.isBlank(title) || StringUtils.isBlank(content) || selectTypeId == null) {
			logger.error("invalid parameter: title={}, content={}, selectTypeId={}", title, content, selectTypeId);
			modelMap.put("info", "发文失败");
			return "page/middleDirect";
		}
		
		try {
			ArticleInfoVO articleInfoVO = new ArticleInfoVO();
			if (articleId == null || articleId <= 0) {
				articleInfoVO.setAuthor(BlogConstant.DEFAULT_AUTHOR);
				articleInfoVO.setCreateDate(new Date());
				articleInfoVO.setContent(content);
				articleInfoVO.setIp(Tools.getIp(request));
				articleInfoVO.setModifyDate(new Date());
				articleInfoVO.setTitle(title);
				articleInfoVO.setType(selectTypeId);
			} else {
				BaseResult<ArticleInfoVO> result = articleInfoManager.getArticleInfoById(articleId);
				if (result != null && result.isSuccess() && result.getT() != null) {
					articleInfoVO = result.getT();
					articleInfoVO.setTitle(title);
					articleInfoVO.setContent(content);
					articleInfoVO.setType(selectTypeId);
					articleInfoVO.setModifyDate(new Date());
				} else {
					modelMap.put("info", "修改文章失败");
					logger.error("submitAritcle getArticleInfoById error, articleId=" + articleId);
					return "page/middleDirect";
				}
			}
			
			BaseResult<ArticleInfoVO> result = articleInfoManager.addOrUpdateArticle(articleInfoVO);
			if (result != null && result.isSuccess() && result.getT() != null) {
				typeInfoManager.addArticleCountInType(selectTypeId, 1);
				modelMap.put("info", "发文成功");
				
				long end = System.currentTimeMillis();
				logger.info("addAritcle method end, cost time=" + (end - begin) + "ms");
				
				return "page/middleDirect";
			} else {
				logger.error("add article failed, result=" + result);
			}
		} catch (Exception e) {
			logger.error("unknown error", e);
		}
		
		long end = System.currentTimeMillis();
		logger.info("addAritcle method end, cost time=" + (end - begin) + "ms");
		
		modelMap.put("info", "发文失败");
		return "page/middleDirect";
	}
	
	@RequestMapping(value = "/logout")
	public String logout(ModelMap modelMap, HttpSession session) {
		session.removeAttribute("userId");
		session.invalidate();
		return "login";
	}

	@RequestMapping(value = "/login")
	public String login(String id, String password, ModelMap modelMap, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			if (StringUtils.isBlank(id) || StringUtils.isBlank(password)) {
				return "login";
			} else {
				if (id.equals(BlogConstant.DEFAULT_USER_ID) && password.equals(BlogConstant.DEFAULT_USER_PASSWORD)) {
					modelMap.put("userId", id);
					session.setAttribute("userId", id);
				} else {
					return "login";
				}
			}
		} else {
			modelMap.put("info", "欢迎你, " + session.getAttribute("id"));
			return "page/middleDirect";
		}
		
		modelMap.put("info", "欢迎你的登录, " + id);
		return "page/middleDirect";
	}
	
	@RequestMapping(value = "editarticle/{id}")
	public String editBlog(@PathVariable long id, ModelMap modelMap, HttpSession session) {
		long begin = System.currentTimeMillis();
		logger.info("editBlog method begin");
		
		try {
			if (session.getAttribute("userId") == null) {
				return "login";
			}
			
			if (id <= 0) {
				logger.error("invalid parameter: id={}", id);
				modelMap.put("info", "无此文章");
				return "page/middleDirect";
			}
			
			BaseResult<ArticleInfoVO> articleInfo = articleInfoManager.getArticleInfoById(id);
			if (articleInfo == null || !articleInfo.isSuccess() || articleInfo.getT() == null) {
				logger.error("search article failed, id={}", id);
				modelMap.put("info", "系统异常");
				return "page/middelDirect";
			}
			
			modelMap.put("curArticleId", articleInfo.getT().getId());
			modelMap.put("title", articleInfo.getT().getTitle());
			modelMap.put("content", articleInfo.getT().getContent());
			modelMap.put("curTypeId", articleInfo.getT().getType());
			
			List<TypeInfoVO> typeInfoVOs = typeInfoManager.getAllTypeInfos();
			if (typeInfoVOs == null) {
				logger.error("writeArticle method get type list null");
				modelMap.put("info", "系统异常");
				return "page/middelDirect";
			} else {
				//置顶文章类型列表
				modelMap.put("typeInfos", typeInfoVOs);
			}
		} catch (Exception e) {
			logger.error("unknown error", e);
			modelMap.put("info", "系统异常");
			return "page/middelDirect";
		}
		
		long end = System.currentTimeMillis();
		logger.info("editBlog method end, cost time=" + (end - begin) + "ms");
		return "page/writearticle";
	}

	@RequestMapping(value = "/writearticle")
	public String writeBlog(ModelMap modelMap, HttpSession session) {
		long begin = System.currentTimeMillis();
		logger.info("wrtieBlog method begin");
		
		try {
			if (session.getAttribute("userId") == null) {
				return "login";
			}
				
			List<TypeInfoVO> typeInfoVOs = typeInfoManager.getAllTypeInfos();
			if (typeInfoVOs == null) {
				logger.error("writeArticle method get type list null");
			} else {
				//置顶文章类型列表
				modelMap.put("typeInfos", typeInfoVOs);
			}
		} catch (Exception e) {
			logger.error("unknown exception", e);
			modelMap.put("info", "系统异常");
			return "page/middelDirect";
		}
		
		long end = System.currentTimeMillis();
		logger.info("writeBlog method end, cost time=" + (end - begin) + "ms");
		return "page/writearticle";
	}
	
	@RequestMapping(value="/deleteInBlog", method=RequestMethod.POST)
	public String deleteInBlog(Long articleId, ModelMap modelMap) {
		long begin = System.currentTimeMillis();
		return null;
	}
}
