<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	int port=request.getServerPort();
	String basePath="";
	if(port==80){
		basePath=request.getScheme() + "://"+ request.getServerName()+path+"/";
	}
	else{
		basePath=request.getScheme() + "://"+ request.getServerName()+":"+port+path + "/";
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>无标题文档</title>
<link rel="stylesheet" type="text/css" href="css/left.css"/>
<script type="text/javascript" src="<%=basePath %>static/js/jquery-1.4.1.min.js"></script>
<script type="text/javascript" src="<%=basePath %>static/js/admin/left.js"></script>
</head>

<body>
<div class="main">
	<div class="menu">
		<div class="fmenu">日志管理</div>
		<div class="zmenu">
			<ul>
				<li><a href="article/article.htm?op=add">添加日志</a></li>
				<li><a href="article/articleList.jsp">菜单1</a></li>
				<li><a href="#">菜单1</a></li>
				<li><a href="#">菜单1</a></li>
			</ul>
		</div>
		<div class="fmenu">系统管理</div>
		<div class="zmenu">
			<ul>
				<li><a href="<%=basePath %>htmlmanage/index.htm">更新首页</a></li>
				<li><a href="sitemap.htm?style=baidu">更新SiteMap</a></li>
				<li><a href="#">菜单2</a></li>
				<li><a href="#">菜单2</a></li>
			</ul>
		</div>
	</div>
</div>
</body>
</html>