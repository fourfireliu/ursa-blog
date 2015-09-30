package com.fourfire.blog.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fourfire.blog.dao.ArticleInfoDao;
import com.fourfire.blog.dao.CommentInfoDao;
import com.fourfire.blog.dao.LinkInfoDao;
import com.fourfire.blog.dao.MsgInfoDao;
import com.fourfire.blog.dao.TagInfoDao;
import com.fourfire.blog.dao.TypeInfoDao;
import com.fourfire.blog.manager.ArticleInfoManager;
import com.fourfire.blog.manager.CommentManager;
import com.fourfire.blog.util.HtmlThread;
import com.fourfire.blog.util.Tools;
import com.fourfire.blog.vo.ArticleInfoVO;

@Controller
@RequestMapping(value = "/article")
public class ArticleController {
	Logger logger = LogManager.getLogger(ArticleController.class);
	
	@Resource(name = "articleDaoImpl")
	private ArticleInfoDao adao;

	@Resource(name = "typeInfoDaoImpl")
	private TypeInfoDao tdao;

	@Resource(name = "msgInfoDaoImpl")
	private MsgInfoDao mdao;

	@Resource(name = "commentInfoDaoImpl")
	private CommentInfoDao comdao;

	@Resource(name = "tagInfoDaoImpl")
	private TagInfoDao tagdao;

	@Resource(name = "linkInfoDaoImpl")
	private LinkInfoDao linkdao;

	@Resource
	private ArticleInfoManager articleInfoManager;
	@Resource
	private CommentManager commentManager;
	
	@RequestMapping(value = "/index")
	public String indexView(HttpServletRequest request,
			HttpServletResponse response) {
		long id = ServletRequestUtils.getLongParameter(request, "id", 0L);
		ArticleInfoVO articleInfoVO = articleInfoManager.getArticleInfoById(id);
		request.setAttribute("article", articleInfoVO);
		//查询日志评论
		request.setAttribute("articleCommentList", commentManager.getCommentsByArticleId(id));
		// 上一篇
		request.setAttribute("upArticle", adao.getUpArticleInfo(id));

		// 下一篇
		request.setAttribute("downArticle", adao.getDownArticleInfo(id));
		
		//评论列表
		request.setAttribute("commentList", comdao.getArticleCommentByArticleId(id));
		

		// 最近心情
		request.setAttribute("mood", adao.getNearMood());

		// 导航
		List<Map<String, Object>> typeList = tdao.getAllTypeInfo();
		request.setAttribute("typeList", typeList);

		// 热门标签
		List<Map<String, Object>> hotTag = tagdao.getHotTagInfo();
		request.setAttribute("hotTag", hotTag);

		List<Map<String, Object>> commendArticle = adao.getCommendAricle();// 首页右边推荐日志
		List<Map<String, Object>> hotArticle = adao.getHotAricle();// 热门
		List<Map<String, Object>> hotCommentArticle = adao.getHotCommentAricle();// 首页右边评论日志

		request.setAttribute("commendArticle", commendArticle);
		request.setAttribute("hotArticle", hotArticle);
		request.setAttribute("hotCommentArticle", hotCommentArticle);

		// 最近心情
		request.setAttribute("mood", adao.getNearMood());

		List<Map<String, Object>> msgList = mdao.getNew10MsgInfo();
		request.setAttribute("msgList", msgList);

		List<Map<String, Object>> commentList = comdao.getNewTop10CommentInfo();
		request.setAttribute("commentList", commentList);

		List<Map<String, Object>> tagList = tagdao.getAlltagInfo();
		request.setAttribute("tagList", tagList);

		List<Map<String, Object>> linkList = linkdao.getAllLinkInfo();
		request.setAttribute("linkList", linkList);

		HashMap<String, Object> data = new HashMap<String, Object>();

		// 浏览次数加1
		if (id > 0) {

			adao.addReadCountByArticleId(id);
		}
		HtmlThread ht = new HtmlThread(request.getRealPath("/"),request);
		ht.start();

		return "article/index";
	}

	@RequestMapping(value = "/commendAritcle")
	public void commandTheArticle(HttpServletRequest request,
			HttpServletResponse response) {

		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {

		}

		if (id > 0) {

			int result = adao.commendAricle(id);
			if (result > 0) {
				try {
					response.getWriter().print("ok");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

	@RequestMapping(value = "/savaComment")
	public String savaComment(HttpServletRequest request,HttpServletResponse response) {
		String yes = request.getParameter("yes");
		if (!"yes".equals(yes)) {
			request.setAttribute("error", "请等待网页加载完毕，有js防虫功能...");
			return "/error/index";
		}
		String ip = request.getRemoteAddr();
		if (Tools.checkBlackIp(ip)) {
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

		int articleId = 0;

		try {
			articleId = Integer.parseInt(request.getParameter("articleId"));
		} catch (Exception e) {

		}
		if (articleId <= 0) {
			request.setAttribute("error", "请不要恶意传递错误编号。");
			return "/error/index";
		}

		String uname = Tools.checkString(request.getParameter("uname"));
		if ("".equals(uname)) {
			request.setAttribute("error", "朋友请留下您的大名！");
			return "/error/index";
		}

		String mail_qq = Tools.checkString(request.getParameter("mail_qq"));

		String content = Tools.checkString(request.getParameter("comment"));

		if ("".equals(content)) {
			request.setAttribute("error", "朋友,你想说点什么呢？");
			return "/error/index";
		}

		String sql = "insert into comment values(null," + articleId + ",'"
				+ uname + "','" + mail_qq + "','" + content + "','" + ip
				+ "',now())";
		int result = adao.savaComment(sql);

		if (result <= 0) {

			request.setAttribute("error", "评论失败，请联系管理员。");
			return "/error/index";
		}

		request.getSession().setAttribute("time", System.currentTimeMillis());

		/* 更新首页静态文件 */
		HtmlThread ht = new HtmlThread(request.getRealPath("/"),request);
		ht.start();

		try {
			response.sendRedirect("index.htm?id=" + articleId);
		} catch (IOException e) {
			request.setAttribute("error", "页面跳转出错，请联系管理员。");
			return "/error/index";
		}
		return null;
	}
}
