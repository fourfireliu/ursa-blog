package com.fourfire.blog.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

public class HtmlCrater {
	public static synchronized void createIndex(String path,HttpServletRequest request){
		try {
			
			 /*访问生成静态页面接口，更新首页*/
			 //需配置域名首页
			 String indexPath=Tools.getWebPath(request)+"index.htm";
			 if("index.htm".equals(indexPath)){
				 return;
			 }
			 URL url=new URL(indexPath);
			   
			 //创建一个http请求连接
			 HttpURLConnection connection=(HttpURLConnection)url.openConnection();
			   
			 //设置请求头信息
			 connection.setRequestProperty("User-Agent","Mozilla/4.0");
			    
			 connection.setConnectTimeout(30000);//10秒超时
			    
			 //请求连接
			connection.connect();
			InputStream ins =connection.getInputStream();
			
			InputStreamReader isr=new InputStreamReader(ins,"UTF-8");
			BufferedReader br=new BufferedReader(isr);
			
			File wf=new File(path+"index.html");
			long s=System.currentTimeMillis();
			long a=wf.lastModified();
			//最低3分钟更新一次
		    if(s-a<1000*60*1){
		    	return;
		    }
			OutputStreamWriter osw=new OutputStreamWriter(new FileOutputStream(wf),"UTF-8");
			BufferedWriter bw=new BufferedWriter(osw);
			
			String tem=null;
			
			while((tem=br.readLine())!=null){
				bw.write(tem);
				bw.newLine();
			}
			br.close();
			isr.close();
			
			bw.close();
			osw.close();

		} catch (Exception e) {
			
		}
	}

}
