<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%
try{
File f=new File(request.getRealPath("/")+"index.html");
if(f.exists()){
	response.sendRedirect("index.html");
}
else{
	response.sendRedirect("index.htm");
}
}catch(Exception e){
	response.sendRedirect("index.html");
}
%>