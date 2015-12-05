package com.fourfire.blog.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class Tools {
	
	public static String checkString(String str){
		if(StringUtils.isNotBlank(str)){
			//这里过滤参数
			String newstr=str.replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;").replace("'", "&#39;");
			return newstr;
		}
		else{
			return "";
		}
		
	}
	
	/**
	 * 获取文章标题
	 */
	public static String checkTitle(String title) {
		if (StringUtils.isNotBlank(title)) {
			return title.replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;").replace("'", "&#39;");
		}
		
		return Constants.DEFAULUT_TITLE;
	}
	
	/**
	 * 
	 * 生成uuid,注，此功能此适合不频繁分配Id使用
	 * 
	 * */
	public static long getId(){
		return Long.parseLong((System.currentTimeMillis()+"").substring(0, 10));
	}
	
	/**
	 * 判断字符是否为空
	 * 
	 * */
	public static boolean checkNotNull(String str){
		
		if(str!=null){
			if(str.equals("")||str.equals("null")){
				return false;
			}
			return true;
		}
		return false;
		
	}
	
	public static String getWebPath(HttpServletRequest request){
		if(request==null){
			return "";
		}
		String path = request.getContextPath();
		int port=request.getServerPort();
		String basePath="";
		if(port==80){
			basePath=request.getScheme() + "://"+ request.getServerName()+path+"/";
		}
		else{
			basePath=request.getScheme() + "://"+ request.getServerName()+":"+port+path + "/";
		}
		return basePath;
	}
	
	private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static String getTime(){
		return sdf.format(new Date());
	}
}
