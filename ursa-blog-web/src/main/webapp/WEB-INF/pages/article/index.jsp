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
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>${article.title} - www.shack2.org</title>
<meta name="title" content="${article.title}" />
<meta name="Description" content="${article.title}" />
<meta name="Keywords" content="shack2,SJBlog,SJBlog官网,网络攻防,系统oday,WEB渗透,编程学习,Java交流,c#交流,php交流,攻防工具下载,开发经验分享" />
<meta name="author" content="shack2" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>static/css/comm.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>static/css/article.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>static/css/slide.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>static/kindeditor/plugins/code/prettify.css" />
<script type="text/javascript" src="<%=basePath%>static/js/jquery-1.4.1.min.js" ></script>
<script type="text/javascript" src="<%=basePath%>static/js/jquery.lazyload.js" ></script>
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
			
			<!--日志-->
			<div class="article">
				<div class="article_top_nav">
					<a href="http://www.shack2.org/" class="homepage">首&nbsp;&nbsp;页</a>>><a href="<%=basePath %>type/index.htm?id=${article.typeId}" title="${article.typeName}">${article.typeName}</a>>>${article.title}
				</div>
				<div class="article_top">
					<div class="article_top_title">${article.title}</div>
					<div class="article_top_other">
						<div class=""><span class="read">${article.readCount}</span>&nbsp;阅</div>
						<div class=""><span class="recommend">${article.commendCount}</span>&nbsp;推荐</div>
						<div class=""><span class="comment">${article.commentCount}</span>&nbsp;评论</div>
					</div>
				</div>
				<div class="article_info">分类：${article['typeName']}&nbsp;&nbsp;&nbsp;&nbsp;日期：<fmt:formatDate value="${article['date']}" pattern="yyyy-MM-dd HH:mm:ss"/>　　发布：${article['author']}</div>
				<div class="article_content">
					${article.content}
				</div>
			</div>
			<!--日志结束-->
			
			<div class="next">
					<span class="aritcle_up">上一篇：<c:if test="${! empty upArticle}"><a href="<%=basePath %>article/${upArticle['id']}.html">${upArticle['title']}</a></c:if></span>
				 		<span class="aritcle_down">下一篇：<c:if test="${! empty downArticle}"><a href="<%=basePath %>article/${downArticle['id']}.html">${downArticle['title']}</a></c:if></span>

				</div>
				
				<div class="comment_list">
					<div class="comment_title">已经有 ( <em>${fn:length(articleCommentList)}</em> ) 位网友对此发表了自己的看法，你也评一评吧! 此文不错，我要推荐-->&nbsp;&nbsp;&nbsp;&nbsp;<a class="recommend_btn" aid="${article.id}" href="javascript:void(0)">推&nbsp;荐</a></div>
					<div class="comment_list_list">
						<ul>
							<c:forEach items="${articleCommentList}" var="comment" varStatus="status">
							<li id="comment${comment.id}">
								<span class="num">
									<b>${fn:length(articleCommentList)-status.index}楼</b>
									<a href="javascript:void(0)" rel="nofollow" class="commoner" title="${comment['weburl']}">${comment['username']}</a>
									发表于:<fmt:formatDate value="${comment.date}" pattern="yyyy-MM-dd HH:mm:ss"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="reply" href="#posttop">回复</a>
								</span>
								<div class="comment-text">${comment.content}</div>
							</li>
							</c:forEach>
						</ul>
					</div>
					<div class="commonpost" id="posttop">
						<div class="commontitle mt8">欢迎参与讨论，请在这里发表您的看法、交流您的观点@禁止各种脚本</div>
						<form id="commentform" name="commentform" method="post" action="savaComment.htm" onsubmit="return checkComment()" >
						<p><input type="hidden" name="articleId" id="articleId" value="${article.id}" /></p>
						<p><input type="text" name="uname" id="uname" class="text" value="" size="28" tabindex="1" /> <label for="uname">名称(必填)</label></p>
						<p><input type="text" name="mail_qq" id="mail_qq" class="text" value="" size="28" tabindex="2" /> <label for="mail_qq">邮箱</label></p>
						<p><textarea name="comment" style="WIDTH: 600px;height:100px" id="comment" class="text" cols="40" rows="4" tabindex="5" ></textarea></p>
						<p><input name="btnSumbit" type="submit" tabindex="6" value="发布我的点评" /></p>
						</form>
					</div>
				</div>
			
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
<script type="text/javascript">
$(function(){
	$("img").lazyload({
	     effect:"fadeIn"
	});
	$("#commentform").append("<input name='yes' type='hidden' value='yes' /><span style='color:green'>js防垃圾爬虫，可以留言了...</span>");
});
</script>
</html>