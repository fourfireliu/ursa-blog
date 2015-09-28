<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="cp" %>
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
<title>shack2的博客---留言板</title>
<meta name="title" content="www.shack2.org,shack2.org" />
<meta name="Description" content="SJBlog官方站，shack2乌云漏洞平台白帽子成员，网络安全从业者，编程爱好者" />
<meta name="Keywords" content="shack2,SJBlog,SJBlog官网,网络攻防,系统oday,WEB渗透,编程学习,Java交流,c#交流,php交流,攻防工具下载,开发经验分享" />
<meta name="author" content="shack2" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>static/css/comm.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>static/css/message.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>static/css/slide.css" />
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
				<div class="comment_list">
					<div class="comment_title">雁过留声|人过留言|已经有 ( <em>${pm.sumCount}</em> ) 位网友发表了留言，你也来留个言吧!</div>
					<div class="commonpost" id="posttop">
						<div class="commontitle mt8">欢迎参与留言，请在这里发表您的看法、交流您的观点@禁止各种脚本</div>
						<form id="commentform" name="commentform" method="post" action="savaMsg.htm" onsubmit="return checkMsg()" >
						<p><input type="text" name="username" id="username" class="text" value="" size="28" tabindex="1" /> <label for="username">名称(必填)</label></p>
						<p><input type="text" name="mail_qq" id="mail_qq" class="text" value="" size="28" tabindex="2" /> <label for="mail_qq">邮箱</label></p>
						<p><textarea name="comment" style="width: 600px;height:100px" id="comment" class="text" cols="40" rows="4" tabindex="5" ></textarea></p>
						<p><input name="btnSumbit" type="submit" tabindex="6" value="发布我的留言" class="dianping" /></p>
						</form>
					</div>
					<div class="comment_list_list">
						<ul>
						<c:forEach items="${pm.list}" var="msg" varStatus="status">
							<li id="msg${msg.id}">
								<span class="num">
									<b>${pm.sumCount-(pm.pageCount*(pm.currentPage-1))-status.index}楼</b>
									<a href="javascript:void(0)" rel="nofollow" class="commoner" title="${msg['weburl']}">${msg['username']}</a>
									发表于:<fmt:formatDate value="${msg.date}" pattern="yyyy-MM-dd HH:mm:ss"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="reply" href="#posttop">回复</a>
								</span>
								<div class="comment-text">${msg.content}</div>
							</li>
							</c:forEach>
						</ul>
					</div>
				</div>
				
			<div class="cut_page"><cp:CutPage url="index.htm" pm="${pm}"></cp:CutPage></div>
			
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
<script type="text/javascript">
$(function(){
	$("#commentform").append("<input name='yes' type='hidden' value='yes' /><span style='color:green'>js防垃圾爬虫，可以留言了...</span>");
});
</script>
</body>
</html>