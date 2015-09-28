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
<div class="top">
		<div class="top_top"><a target=_top href="javascript:window.external.addFavorite('http://www.shack2.org','shack2博客');">加入收藏</a>
		<a  href='#' onclick="this.style.behavior='url(#default#homepage)';this.setHomePage('http://www.shack2.org');">设为首页</a>
				</div>
		<!--top_content-->
		<div class="top_content">
			<div class="top_bottom">
				<div class="myheart">我喜欢黑夜，喜欢网络安全，孤独寂寞的黑夜里，我只�?要，�?包烟，一台笔记本...</div>
				<!-- Baidu Button start -->
				<div id="bdshare" class="bdshare_t bds_tools_32 get-codes-bdshare">
							<a class="bds_qzone"></a>
							<a class="bds_tsina"></a>
							<a class="bds_tqq"></a>
							<a class="bds_renren"></a>
							<span class="bds_more">更多</span>
							<a class="shareCount"></a>
		  		</div>
				<script type="text/javascript" id="bdshare_js" data="type=tools" ></script>
				<script type="text/javascript" id="bdshell_js"></script>
				<script type="text/javascript">
					document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + new Date().getHours();
				</script>
				<!-- Baidu Button END -->
				<div class="top_bottom_right">
				  <div class="search">
				  	<form action="<%=basePath%>search/index.htm" method="post" id="keyform" name="keyform">
				  		<input type="text" class="search_key" name="key" />
				  		<input type="submit" class="search_btn" value="" />
				  	</form>
				  </div>
				  <div class="hot_tag">
				  	<c:forEach items="${hotTag}" var="tag"><a href="<%=basePath %>search/index.htm?key=${tag.tagName}" title="${tag.tagName}">${tag.tagName}</a></c:forEach>
				  </div>
				</div>
			</div>
			</div>
		<!--top_content end-->
	</div>