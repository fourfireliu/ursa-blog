<%@page import="java.util.List"%>
<%@page import="com.fourfire.blog.dao.impl.TagInfoDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="cp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${pm.keyWords}-搜索结果</title>
<meta name="title" content="${pm.keyWords}-搜索结果" />
<meta name="Description" content="${pm.keyWords}-搜索结果" />
<meta name="Keywords" content="shack2,SJBlog,SJBlog官网,网络攻防,系统oday,WEB渗�??,编程学习,Java交流,c#交流,php交流,攻防工具下载,�?发经验分�?" />
<meta name="author" content="shack2" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>static/css/comm.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>static/css/main.css" />
<script type="text/javascript" src="<%=basePath%>static/js/jquery-1.4.1.min.js" ></script>
<script type="text/javascript" src="<%=basePath%>static/js/main.js"></script>
</head>
<body>
<div class="body">
	<!--顶部-->
	<jsp:include page="../head.jsp" />
	<!--top end-->
	
<!--主体-->
<div class="main">
	
	<!--nav导航-->
	<jsp:include page="../nav.jsp" />
	<!--nav导航 结束-->
	
	<!--中部内容-->
	<div class="middle">
		<!--左边-->
		<div class="mleft">
		
			<div class="article_top_nav">
				当前位置�?<a href="http://www.shack2.org/" class="homepage">�?&nbsp;�?</a>>><a href="#" title="搜索结果">搜索结果</a>
			</div>
			
			<!--日志列表-->
			<div class="act_list">
				<!--单个日志信息-->
				<c:forEach items="${pm.list}" var="act">
				<div class="act">
					<div class="act_left">
						<div class="act_title"><a href="<%=basePath%>article/${act.id}.html">${act.title}</a></div>
						<div class="act_tag">标签�?
						<c:forEach items="${act.tagsList}" var="tag"><span><a href="<%=basePath%>search/index.htm?key=${tag.tagName}">${tag.tagName}</a></span></c:forEach></div>
						<div class="act_info">发布�?<fmt:formatDate value="${act.date}" pattern="yyyy-MM-dd HH:mm:ss"/>�?分类�?<a href="${act.typeId}">${act.typeName}</a></div>
					</div>
					<div class="act_right">
						<div><span class="act_read">${act.readCount}</span><span>�?</span></div>
						<div><span class="act_read">${act.commendCount}</span><span>�?</span></div>
						<div><span class="act_read">${act.commentCount}</span><span>�?</span></div>
					</div>
				</div>
				</c:forEach>
			</div>
			<!--日志列表 结束-->
			
			<!--分页-->
			<div class="cut_page"><cp:CutPage url="index.htm" pm="${pm}"></cp:CutPage></div>
			<!--分页 结束-->
			
			
		</div>
		<!--左边 结束-->
	
	  	<!--右边-->
		<jsp:include page="../right.jsp" />
		<!--右边  结束-->
	</div>
	<!--中部内容 结束-->
</div>
<!--主体 结束-->
<!--bottom-->
<jsp:include page="../foot.jsp" />
<!--bottom end-->
</div>
<!--body 结束-->
</body>
</html>