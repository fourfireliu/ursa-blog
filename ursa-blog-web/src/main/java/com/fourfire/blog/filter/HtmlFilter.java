package com.fourfire.blog.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.CharacterEncodingFilter;

//伪静态锅过滤器
public class HtmlFilter extends CharacterEncodingFilter {

	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// 请求的uri
		String uri = request.getRequestURI();
		if(StringUtils.isNotBlank(uri) && uri.indexOf("/article/") != -1){
			int id=0;
			try {
				System.out.println(uri);
				System.out.println(uri.substring(uri.lastIndexOf("/")+1,uri.lastIndexOf(".")));
				id=Integer.parseInt(uri.substring(uri.lastIndexOf("/")+1,uri.lastIndexOf(".")));
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(id==0){
				response.getWriter().print("id->error...");
				return;
			}
			
			request.s
			request.getRequestDispatcher(uri + "?id="+id).forward(request, response);
			return;
		}
		else {  
            // 如果不执行过滤，则继续  
			filterChain.doFilter(request, response);  
		}  
	}
	
	public static void main(String args[]) {
		String test = "/ursablog/article/index/2.do";
		System.out.println(test.lastIndexOf("/"));
		System.out.println(test.lastIndexOf("."));
		System.out.println(test.substring(test.lastIndexOf("/")+1,test.lastIndexOf(".")));
		
	}

}