<%@page import="java.util.List"%>
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
<div class="nav">
		<ul>
			<li style="margin-left:10px;"><a href="<%=basePath%>">首页</a></li>
	
			<c:forEach items="${typeList}" var="type">
				<c:if test="${type.isFType==1}">
				<li><a href="<%=basePath%>type/index.htm?id=${type.id}">${type.typeName}</a>
				<ul>
					<c:forEach items="${typeList}" var="ctype">
						<c:if test="${ctype.fTypeId==type.id}"><li><a href="<%=basePath%>type/index.htm?id=${ctype.id}">${ctype.typeName}</a></li></c:if>
					</c:forEach>
				</ul>
				</c:if>
			</li>
			</c:forEach>
			<li><a href="http://www.shack2.org/message/index.htm">给我留言</a></li>
		</ul>
	</div>