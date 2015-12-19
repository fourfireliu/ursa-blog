package com.fourfire.blog.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.fourfire.blog.constant.BlogConstant;

public class Tools {
	/**
	 * 转换HTML内容
	 */
	public static String checkHtmlContent(String htmlContent) {
		if (StringUtils.isNotBlank(htmlContent)) {
			return htmlContent.replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;").replace("'", "&#39;");
		}
		return  "";
	}
	
	/**
	 * 转换回HTML格式
	 * 
	 * @param content
	 * @return
	 */
	public static String changeToHtmlContent(String content) {
		if (StringUtils.isNotBlank(content)) {
			return content.replace("&lt;", "<").replace("&gt;", ">").replace("&quot;", "\"").replace("&#39;", "'");
		}
		return  "";
	}
	
	public static String getIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for"); 
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	           ip = request.getHeader("Proxy-Client-IP"); 
	       } 
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	           ip = request.getHeader("WL-Proxy-Client-IP"); 
	       } 
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	           ip = request.getRemoteAddr(); 
	       } 
	       return ip;
	}
	
	/**
	 * 
	 * 生成uuid,注，此功能此适合不频繁分配Id使用
	 * 
	 * */
	public static long getId(){
		return Long.parseLong((System.currentTimeMillis()+"").substring(0, 10));
	}
	
	private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static String getTime(){
		return sdf.format(new Date());
	}
	
	public static String getShortContent(String allContent) {
		if (StringUtils.isBlank(allContent) || allContent.length() <= BlogConstant.DEFAULT_ARTICLE_SIZE) {
			return allContent;
		}
		
		return allContent.substring(0, BlogConstant.DEFAULT_ARTICLE_SIZE) + "...";
	}
}
