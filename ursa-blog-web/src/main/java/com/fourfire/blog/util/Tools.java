package com.fourfire.blog.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.fourfire.blog.constant.BlogConstant;

public class Tools {
	private static final String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式 
    private static final String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式 
	
	/**
	 * 过滤掉不可见字符
	 * 
	 * @param input
	 * @return
	 */
	public static String unknowCharacterFilter(String input) {
		if (StringUtils.isBlank(input)) {
			return "";
		}
		
		for (int i = 0;i < input.length();i++) {
			if (input.charAt(i) < 32 || input.charAt(i) == 127) {
				input = input.replace(input.charAt(i), ' ');
			}
		}
		
		return input;
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
	
	public static String removeHtmlTag(String htmlContent) {
		Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE); 
        Matcher m_style=p_style.matcher(htmlContent); 
        htmlContent=m_style.replaceAll(""); //过滤style标签 
         
        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
        Matcher m_html=p_html.matcher(htmlContent); 
        htmlContent=m_html.replaceAll(""); //过滤html标签 

        return htmlContent.trim(); //返回文本字符串 
	}
	
	/**
	 * 去掉html标签的内容缩减版
	 * 
	 * @param allContent
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String getShortContent(String allContent) throws UnsupportedEncodingException {
		String htmlContent = URLDecoder.decode(allContent, "UTF-8");
		String oriContent = removeHtmlTag(htmlContent);
		if (StringUtils.isBlank(oriContent) || oriContent.length() <= BlogConstant.DEFAULT_ARTICLE_SIZE) {
			return oriContent;
		}

		return oriContent.substring(0, BlogConstant.DEFAULT_ARTICLE_SIZE) + "...";
	}
	
	public static void main(String args[]) {
		String test = "<p><if test=\"userIds != null and userIds.length > 0\"></p>";
	}
}
