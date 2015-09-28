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
<meta name="Keywords" content="shack2,SJBlog,SJBlog瀹樼綉,缃戠粶鏀婚槻,绯荤粺oday,WEB娓楅??,缂栫▼瀛︿範,Java浜ゆ祦,c#浜ゆ祦,php浜ゆ祦,鏀婚槻宸ュ叿涓嬭浇,寮?鍙戠粡楠屽垎浜?" />
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
	<!--椤堕儴-->
	<jsp:include page="../head.jsp" />
	<!--top end-->
	
<!--涓讳綋-->
<div class="main">
	
	<!--nav瀵艰埅-->
	<jsp:include page="../nav.jsp" />
	<!--nav瀵艰埅 缁撴潫-->
	
	<!--涓儴鍐呭-->
	<div class="middle">
		<!--宸﹁竟-->
		<div class="mleft">
			
			<!--鏃ュ織-->
			<div class="article">
				<div class="article_top_nav">
					<a href="http://www.shack2.org/" class="homepage">棣?&nbsp;&nbsp;椤?</a>>><a href="<%=basePath %>type/index.htm?id=${article.typeId}" title="${article.typeName}">${article.typeName}</a>>>${article.title}
				</div>
				<div class="article_top">
					<div class="article_top_title">${article.title}</div>
					<div class="article_top_other">
						<div class=""><span class="read">${article.readCount}</span>&nbsp;闃?</div>
						<div class=""><span class="recommend">${article.commendCount}</span>&nbsp;鎺ㄨ崘</div>
						<div class=""><span class="comment">${article.commentCount}</span>&nbsp;璇勮</div>
					</div>
				</div>
				<div class="article_info">鍒嗙被锛?${article['typeName']}&nbsp;&nbsp;&nbsp;&nbsp;鏃ユ湡锛?<fmt:formatDate value="${article['date']}" pattern="yyyy-MM-dd HH:mm:ss"/>銆?銆?鍙戝竷锛?${article['author']}</div>
				<div class="article_content">
					${article.content}
				</div>
			</div>
			<!--鏃ュ織缁撴潫-->
			
			<div class="next">
					<span class="aritcle_up">涓婁竴绡囷細<c:if test="${! empty upArticle}"><a href="<%=basePath %>article/${upArticle['id']}.html">${upArticle['title']}</a></c:if></span>
				 		<span class="aritcle_down">涓嬩竴绡囷細<c:if test="${! empty downArticle}"><a href="<%=basePath %>article/${downArticle['id']}.html">${downArticle['title']}</a></c:if></span>

				</div>
				
				<div class="comment_list">
					<div class="comment_title">宸茬粡鏈? ( <em>${fn:length(articleCommentList)}</em> ) 浣嶇綉鍙嬪姝ゅ彂琛ㄤ簡鑷繁鐨勭湅娉曪紝浣犱篃璇勪竴璇勫惂! 姝ゆ枃涓嶉敊锛屾垜瑕佹帹鑽?-->&nbsp;&nbsp;&nbsp;&nbsp;<a class="recommend_btn" aid="${article.id}" href="javascript:void(0)">鎺?&nbsp;鑽?</a></div>
					<div class="comment_list_list">
						<ul>
							<c:forEach items="${articleCommentList}" var="comment" varStatus="status">
							<li id="comment${comment.id}">
								<span class="num">
									<b>${fn:length(articleCommentList)-status.index}妤?</b>
									<a href="javascript:void(0)" rel="nofollow" class="commoner" title="${comment['weburl']}">${comment['username']}</a>
									鍙戣〃浜?:<fmt:formatDate value="${comment.date}" pattern="yyyy-MM-dd HH:mm:ss"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="reply" href="#posttop">鍥炲</a>
								</span>
								<div class="comment-text">${comment.content}</div>
							</li>
							</c:forEach>
						</ul>
					</div>
					<div class="commonpost" id="posttop">
						<div class="commontitle mt8">娆㈣繋鍙備笌璁ㄨ锛岃鍦ㄨ繖閲屽彂琛ㄦ偍鐨勭湅娉曘?佷氦娴佹偍鐨勮鐐笯绂佹鍚勭鑴氭湰</div>
						<form id="commentform" name="commentform" method="post" action="savaComment.htm" onsubmit="return checkComment()" >
						<p><input type="hidden" name="articleId" id="articleId" value="${article.id}" /></p>
						<p><input type="text" name="uname" id="uname" class="text" value="" size="28" tabindex="1" /> <label for="uname">鍚嶇О(蹇呭～)</label></p>
						<p><input type="text" name="mail_qq" id="mail_qq" class="text" value="" size="28" tabindex="2" /> <label for="mail_qq">閭</label></p>
						<p><textarea name="comment" style="WIDTH: 600px;height:100px" id="comment" class="text" cols="40" rows="4" tabindex="5" ></textarea></p>
						<p><input name="btnSumbit" type="submit" tabindex="6" value="鍙戝竷鎴戠殑鐐硅瘎" /></p>
						</form>
					</div>
				</div>
			
		</div>
		<!--宸﹁竟 缁撴潫-->
	
	  	<!--鍙宠竟-->
		<jsp:include page="../right.jsp" />
		<!--鍙宠竟  缁撴潫-->
	</div>
	<!--涓儴鍐呭 缁撴潫-->
</div>
<!--涓讳綋 缁撴潫-->

<!--bottom-->
<jsp:include page="../foot.jsp" />
<!--bottom end-->
</div>
<!--body 缁撴潫-->
</body>
<script type="text/javascript">
$(function(){
	$("img").lazyload({
	     effect:"fadeIn"
	});
	$("#commentform").append("<input name='yes' type='hidden' value='yes' /><span style='color:green'>js闃插瀮鍦剧埇铏紝鍙互鐣欒█浜?...</span>");
});
</script>
</html>