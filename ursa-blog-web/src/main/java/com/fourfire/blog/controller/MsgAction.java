package com.fourfire.blog.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fourfire.blog.dao.ArticleInfoDao;
import com.fourfire.blog.dao.CommentInfoDao;
import com.fourfire.blog.dao.LinkInfoDao;
import com.fourfire.blog.dao.MsgInfoDao;
import com.fourfire.blog.dao.TagInfoDao;
import com.fourfire.blog.dao.TypeInfoDao;
import com.fourfire.blog.model.PageModel;
import com.fourfire.blog.util.HtmlThread;
import com.fourfire.blog.util.Tools;

@Controller
@RequestMapping(value = "/message")
public class MsgAction {
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

	@RequestMapping(value = "/index")
	public String indexView(HttpServletRequest request,
			HttpServletResponse response) {

		// 每页条数默认10条
		int pageCount = 10;
		// 页数，默认第一页
		int page = 1;
		try {
			page = Integer.parseInt(request.getParameter("page"));
			pageCount = Integer.parseInt(request.getParameter("pageCount"));
		} catch (Exception e) {

		}
		if (page <= 0) {
			page = 1;
		}

		PageModel pm = mdao.getMsgInfoByPage(page, pageCount);

		request.setAttribute("pm", pm);

		// 导航
		List<Map<String, Object>> typeList = tdao.getAllTypeInfo();
		request.setAttribute("typeList", typeList);

		// 热门标签
		List<Map<String, Object>> hotTag = tagdao.getHotTagInfo();
		request.setAttribute("hotTag", hotTag);

		List<Map<String, Object>> commendArticle = adao.getCommendAricle();// 评论
		List<Map<String, Object>> hotArticle = adao.getHotAricle();// 热门
		List<Map<String, Object>> hotCommentArticle = adao.getHotCommentAricle();// 推荐

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

		return "message/index";
	}

	@RequestMapping(value = "/savaMsg")
	public String savaMsg(HttpServletRequest request,
			HttpServletResponse response) {
		
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

		String username = Tools.checkString(request.getParameter("username"));
		if ("".equals(username)) {
			request.setAttribute("error", "朋友请留下您的大名！");
			return "/error/index";
		}

		String mail_qq = Tools.checkString(request.getParameter("mail_qq"));

		String weburl = Tools.checkString(request.getParameter("weburl"));

		String content = Tools.checkString(request.getParameter("comment"));

		if ("".equals(content)) {
			request.setAttribute("error", "朋友,你想说点什么呢？");
			return "/error/index";
		}

		String sql = "insert into msginfo values(null,'" + username + "','"
				+ mail_qq + "','" + weburl + "','" + content + "','" + ip
				+ "',now())";

		int result = mdao.saveMsg(sql);

		if (result <= 0) {
			request.setAttribute("error", "留言失败，请联系管理员。");
			return "/error/index";
		}

		request.getSession().setAttribute("time", System.currentTimeMillis());

		/* 更新首页静态文件 */
		HtmlThread ht = new HtmlThread(request.getRealPath("/"),request);
		ht.start();

		try {
			response.sendRedirect("index.htm");
		} catch (IOException e) {
			request.setAttribute("error", "页面跳转出错，请联系管理员。");
			return "/error/index";
		}
		return null;
	}

}
