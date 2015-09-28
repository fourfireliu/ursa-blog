package com.fourfire.blog.controller;



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
import com.fourfire.blog.util.HtmlThread;


@Controller(value="htmlManage")

@RequestMapping(value="/htmlmanage")
public class StaticHtmlManage{
	
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
	public String creatHtml(HttpServletRequest request,HttpServletResponse response){
		
		HtmlThread ht=new HtmlThread(request.getRealPath("/"),request);
		ht.start();
		request.setAttribute("msg", "started");
		return "error/index";
	 }
}

