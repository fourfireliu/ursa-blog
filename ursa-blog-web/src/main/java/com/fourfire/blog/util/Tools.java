package com.fourfire.blog.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fourfire.blog.dao.BaseDao;

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
	
	private static ApplicationContext context=null;
	
	public static boolean checkBlackIp(String ip){
		if(context==null){
			context = new ClassPathXmlApplicationContext("classpath:spring-context.xml");
		}
		BaseDao bd=(BaseDao)context.getBean("baseDao");
		return bd.checkIsblackIp(ip);
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
	
	/**
	 * taglist to string
	 * */
	public static String listToString(List<Map> list){
		StringBuffer tagstr=new StringBuffer("");
		for (Map map : list) {
			String tagName=map.get("tagName")+"";
			if(checkNotNull(tagName)){
				tagstr.append(map.get("tagName")+",");
			}
		}
		if(!"".equals(tagstr.toString())){
			return tagstr.toString().substring(0,tagstr.length()-1);
		}
		return "";
	}
	
}
