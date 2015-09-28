<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="cp"%>
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
<title></title>
<link rel="stylesheet" type="text/css" href="<%=basePath %>static/css/admin/comm.css"/>
<script type="text/javascript" src="<%=basePath %>static/js/jquery-1.4.1.min.js"></script>
<script type="text/javascript" src="<%=basePath %>static/js/admin/right.js"></script>
</head>

<body>
<div>
	<div class="right_top">
		<div class="title"><img src="static/admin/images/info.png" align="absmiddle">&nbsp;</div>
		<div class="dobtn"><span><a href="#"><img src="images/add.png" align="absmiddle"></a></span><span><a href="#"><img src="images/del.png" align="absmiddle"></a></span></div>
	</div>
	<div class="list_table">
	  <table width="100%" border="0" cellspacing="0" cellpadding="1">
          <tr align="center" bgcolor="#eaeaea">
            <td>编号</td>
            <td>标题</td>
            <td>标签</td>
            <td>添加时间</td>
            <td>操作</td>
          </tr>
         	<c:forEach items="${pm.list}" var="act">
          <tr> 
            <td>${act.id}</td>
            <td><a href="article.htm?op=update&id=${act.id}">${act.title}</a></td>
            <td>${act.tagsList}</td>
            <td>${act.date}</td>
            <td>&nbsp;</td>
          </tr>
          </c:forEach>
        </table>
	</div>
	<!--分页-->
		<div class="cut_page"><cp:CutPage url="articleList.htm" pm="${pm}"></cp:CutPage></div>
	<!--分页 结束-->
</div>
</body>
</html>