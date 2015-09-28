package com.fourfire.blog.util;

import javax.servlet.http.HttpServletRequest;

public class HtmlThread extends Thread {
	
	private  String path="";
	private  HttpServletRequest request;
	public HtmlThread(String path,HttpServletRequest request){
		this.path=path;
		this.request=request;
	}
	
	public void run(){
		
		//生成首页
		HtmlCrater.createIndex(path,request);
	}
	
}
