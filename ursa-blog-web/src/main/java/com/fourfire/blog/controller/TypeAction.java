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

@Controller
@RequestMapping(value="/type")
public class TypeAction{
	@Resource(name="articleDaoImpl")
	private ArticleInfoDao adao;
	
	@Resource(name="typeInfoDaoImpl")
	private TypeInfoDao tdao;
	
	@Resource(name="msgInfoDaoImpl")
	private MsgInfoDao mdao;
	
	@Resource(name="commentInfoDaoImpl")
	private CommentInfoDao comdao;
	
	@Resource(name="tagInfoDaoImpl")
	private TagInfoDao tagdao;
	
	@Resource(name="linkInfoDaoImpl")
	private LinkInfoDao linkdao;
	
	

	@RequestMapping(value="/index")
	public String indexView(HttpServletRequest request,HttpServletResponse response){
		
		
		//类型
		int typeId=0;
		try {
			typeId=Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(typeId<0){
			
		}
		Map<String, Object> type;
		//查询全部类型的日志
		if(typeId==0){
			type=new HashMap<String, Object>();
			type.put("typeName", "全部");
		}
		else{
			type=tdao.getTypeInfoById(typeId);
		}
		
		request.setAttribute("type", type);
		
		//导航
		List<Map<String, Object>> typeList=tdao.getAllTypeInfo();
		request.setAttribute("typeList", typeList);
		
		
		//每页条数默认10条
		int pageCount=10;
		//页数，默认第一页
		int page=1;
		try {
			page=Integer.parseInt(request.getParameter("page"));
			pageCount=Integer.parseInt(request.getParameter("pageCount"));
		} catch (Exception e) {
			
		}
		
		if(page<=0){
			page=1;
		}
		
		PageModel pm=adao.getArticleInfoByPage(typeId, page, pageCount);

		//查询对应的标签放入list
		List<Map> list=pm.getList();
		for (Map map : list) {
			int id=Integer.parseInt(map.get("id")+"");
			List<Map> tagsList=tagdao.getTagListByArticleId(id);
			map.put("tagsList", tagsList);
		}
		request.setAttribute("pm", pm);
		
		
		//最近心情
		request.setAttribute("mood", adao.getNearMood());
		
		List<Map<String, Object>> commendArticle=adao.getCommendAricle();
		List<Map<String, Object>> hotArticle=adao.getHotAricle();
		List<Map<String, Object>> hotCommentArticle=adao.getHotCommentAricle();

		request.setAttribute("commendArticle", commendArticle);
		request.setAttribute("hotArticle", hotArticle);
		request.setAttribute("hotCommentArticle", hotCommentArticle);
		
		List<Map<String, Object>> topArticle=adao.getTopAricle();//置顶
		request.setAttribute("topArticle", topArticle);
		
		List<Map<String, Object>> msgList=mdao.getNew10MsgInfo();
		request.setAttribute("msgList", msgList);
		
		List<Map<String, Object>> commentList=comdao.getNewTop10CommentInfo();
		request.setAttribute("commentList", commentList);
		
		List<Map<String, Object>> tagList=tagdao.getAlltagInfo();
		request.setAttribute("tagList", tagList);
		
		//热门标签
		List<Map<String, Object>> hotTag=tagdao.getHotTagInfo();
		request.setAttribute("hotTag", hotTag);
		
		//日志列表
		
		List<Map<String, Object>> linkList=linkdao.getAllLinkInfo();
		request.setAttribute("linkList", linkList);
		
		return "type/index";
	 }
	
}
