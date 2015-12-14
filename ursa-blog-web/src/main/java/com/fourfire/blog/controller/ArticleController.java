package com.fourfire.blog.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fourfire.blog.manager.ArticleInfoManager;
import com.fourfire.blog.manager.BlackIpManager;
import com.fourfire.blog.manager.CommentManager;
import com.fourfire.blog.manager.TypeInfoManager;
import com.fourfire.blog.result.BaseResult;
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
	
	@RequestMapping(path = "/articlelist/{typeId}", method=RequestMethod.GET)
	public String getArticleList(@PathVariable Long typeId, ModelMap modelMap) {
		long begin = System.currentTimeMillis();
		logger.info("getArticleList method begin");
		
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
		String yes = request.getParameter("yes");
		if (!"yes".equals(yes)) {
			request.setAttribute("error", "请等待网页加载完毕，有js防虫功能...");
			return "/error/index";
		}
		String ip = request.getRemoteAddr();
		if (blackIpManager.checkIsBlack(ip)) {
			request.setAttribute("error", "抱歉，您的Ip已被加入黑名单...");
			return "/error/index";
		}

		Object s_time = request.getSession().getAttribute("time");
		if (s_time != null) {
			long time = Long.parseLong(s_time + "");
			Long n_time = System.currentTimeMillis();
			if (n_time - time < 10 * 1000) {
				request.setAttribute("error", "抱歉，两次留言不能小于10秒...");
				return "/error/index";
			}
		}

		long articleId = ServletRequestUtils.getLongParameter(request, "articleId", 0L);
		if (articleId <= 0) {
			request.setAttribute("error", "请不要恶意传递错误编号。");
			return "/error/index";
		}

		String uname = Tools.checkString(request.getParameter("uname"));
		if (StringUtils.isEmpty(uname)) {
			request.setAttribute("error", "朋友请留下您的大名！");
			return "/error/index";
		}

		String content = Tools.checkString(request.getParameter("comment"));

		if (StringUtils.isEmpty(content)) {
			request.setAttribute("error", "朋友,你想说点什么呢？");
			return "/error/index";
		}

		CommentVO commentVO = new CommentVO();
		commentVO.setArticleId(articleId);
		commentVO.setContent(content);
		commentVO.setCreateGmtDate(new Date());
		commentVO.setIp(ip);
		commentVO.setUserId(uname);
		if (!commentManager.insertComment(commentVO)) {
			request.setAttribute("error", "评论失败，请联系管理员。");
			return "/error/index";
		}

		request.getSession().setAttribute("time", System.currentTimeMillis());
		
		return "/error/index";
	}
}
