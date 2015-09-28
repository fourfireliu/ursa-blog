package com.fourfire.blog.controller;


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
import com.fourfire.blog.util.Tools;

@Controller
@RequestMapping(value = "/search")
public class SearchAction {
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
	public String indexView(HttpServletRequest request,HttpServletResponse response) {

		// 关键字
		String key = Tools.checkString(request.getParameter("key")).toLowerCase();
		if (key.indexOf("%") > 0 || key.indexOf("'") > 0
				|| key.indexOf("and") > 0 || key.indexOf("union") > 0
				|| key.indexOf("select") > 0) {

			request.setAttribute("error", "请不要注入了，换一个关键字...");
			return "error/500";
		}

		// 导航
		List<Map<String, Object>> typeList = tdao.getAllTypeInfo();
		request.setAttribute("typeList", typeList);

		// 热门标签
		List<Map<String, Object>> hotTag = tagdao.getHotTagInfo();
		request.setAttribute("hotTag", hotTag);

		List<Map<String, Object>> commendArticle = adao.getCommendAricle();// 评论
		List<Map<String, Object>> hotArticle = adao.getHotAricle();// 热门
		List<Map<String, Object>> hotCommentArticle = adao.getHotCommentAricle();//推荐

		request.setAttribute("commendArticle", commendArticle);
		request.setAttribute("hotArticle", hotArticle);
		request.setAttribute("hotCommentArticle", hotCommentArticle);

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
		PageModel pm = adao.getArticleInfoByKey(key, page, pageCount);
		// 查询对应的标签放入list
		List<Map> list = pm.getList();
		for (Map map : list) {
			int id = Integer.parseInt(map.get("id") + "");
			List<Map> tagsList = tagdao.getTagListByArticleId(id);
			map.put("tagsList", tagsList);
		}
		request.setAttribute("pm", pm);

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
		
		return "search/index";
	}

}
