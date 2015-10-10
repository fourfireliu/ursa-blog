package com.fourfire.blog.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fourfire.blog.manager.ArticleInfoManager;
import com.fourfire.blog.manager.BlackIpManager;
import com.fourfire.blog.manager.CommentManager;
import com.fourfire.blog.page.PageResult;
import com.fourfire.blog.util.Tools;
import com.fourfire.blog.vo.ArticleInfoVO;
import com.fourfire.blog.vo.CommentVO;

@Controller
@RequestMapping(value = "/article")
public class ArticleController {
	Logger logger = LogManager.getLogger(ArticleController.class);
	
	@Resource
	private ArticleInfoManager articleInfoManager;
	@Resource
	private CommentManager commentManager;
	@Resource
	private BlackIpManager blackIpManager;
	
	@RequestMapping(value = "/index")
	public String indexView(HttpServletRequest request,
			HttpServletResponse response) {
		long id = ServletRequestUtils.getLongParameter(request, "id", 0L);
		ArticleInfoVO articleInfoVO = articleInfoManager.getArticleInfoById(id);
		request.setAttribute("article", articleInfoVO);
		//查询日志评论
		request.setAttribute("articleCommentList", commentManager.getCommentsByArticleId(id));
		// 上一篇
		request.setAttribute("upArticle", articleInfoManager.getUpArticleInfo(id));
		// 下一篇
		request.setAttribute("downArticle", articleInfoManager.getDownArticleInfo(id));
		//评论列表
		request.setAttribute("commentList", commentManager.getCommentsByArticleId(id));

		PageResult<ArticleInfoVO> result = articleInfoManager.getHotArticles();
		List<ArticleInfoVO> hotArticles = new ArrayList<ArticleInfoVO>();
		if (result != null && result.isSuccess() && result.getPageResult() != null) {
			hotArticles = result.getPageResult();
		}
		request.setAttribute("hotArticles", hotArticles);
		// 浏览次数加1
		if (id > 0) {
			articleInfoManager.addReadCountByArticleId(id);
		}

		return "article/index";
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
		if (StringUtils.isBlank(uname)) {
			request.setAttribute("error", "朋友请留下您的大名！");
			return "/error/index";
		}

		String content = Tools.checkString(request.getParameter("comment"));

		if (StringUtils.isBlank(content)) {
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
