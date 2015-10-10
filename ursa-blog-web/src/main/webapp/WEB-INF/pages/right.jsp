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
<div class="mright">
			<!--act_top-->
			<div class="act_top">
				<!--最近动态-->
				<div class="act_top_list_m">
                    <div class="act_top_tab">
						<ul>
							<li class="act_top_tab_on">最近动态</li>
							<li>&nbsp;</li>
							<li>&nbsp;</li>
						</ul>
                    </div>
					
					<div class="act_top_list">
						<div>
							<ul>
								<li>
								  <a href="http://www.shack2.org/article/189.html">SDomainQuery V1.2发布，新版更新了接口...</a>
								</li>
								<li>
								   博客模板已更新，稍后将开放源代码，敬请期待...
								</li>
							</ul>
						</div>
					</div>
					
				</div>
				
				<div class="act_top_list_m">
                    <div class="act_top_tab" id="act_top_tab">
						<ul>
							<li class="act_top_tab_on">点击Top</li>
							<li>推荐Top</li>
							<li>评论Top</li>
						</ul>
						 <a href="<%=basePath %>type/index.htm" target="_blank" title="更多" class="more_info">更 多&gt;&gt;</a>
                    </div>
					
					<div class="act_top_list" id="act_top_list">
						<!--点击TOP-->
						<div>
							<ul>
								<c:forEach items="${hotArticle}" var="act">
									<li>
									<a href="<%=basePath %>article/${act.id}.html" title="${act.title}">${act.title}</a>
									<span class="info">${act.readCount}阅</span>
									</li>
								</c:forEach>
							</ul>
						</div>
						<!--点击TOP 结束-->
						
						<!--推荐TOP-->
						<div style="display:none">
							<ul>
								<c:forEach items="${commendArticle}" var="act">
									<li>
									<a href="<%=basePath %>article/${act.id}.html" title="${act.title}">${act.title}</a>
									<span class="info">${act.commendCount}荐</span>
									</li>
								</c:forEach>
							</ul>
						</div>
						<!--推荐TOP 结束-->
						
						<!--评论TOP-->
						<div style="display:none">
							<ul>
								<c:forEach items="${hotCommentArticle}" var="act">
									<li>
									<a href="<%=basePath %>article/${act.id}.html" title="${act.title}">${act.title}</a>
									<span class="info">${act.commentCount}评</span>
									</li>
								</c:forEach>
							</ul>
						</div>
						<!--评论TOP 结束-->
					</div>
					
				</div>
				<!--右侧单块 结束-->
				
				<div class="act_top_list_m">
                    <div class="act_top_tab">
						<ul>
							<li class="act_top_tab_on">最近留言</li>
							<li>&nbsp;</li>
							<li>&nbsp;</li>
						</ul>
						 <a href="<%=basePath %>message/index.htm" target="_blank" title="更多" class="more_info">更 多&gt;&gt;</a>
                    </div>
					
					<div class="act_top_list">
						<div>
							<ul>
							<c:forEach items="${msgList}" var="msg">
								<li>
								<a href="<%=basePath%>message/index.htm#msg${msg.id}" title="${msg.content}">${msg.content}</a>
								<span class="info"><a title="${msg.username}">${msg.username}</a></span>
								</li>
							</c:forEach>
							</ul>
						</div>
					</div>
					
				</div>
				
				<div class="act_top_list_m">
                    <div class="act_top_tab">
						<ul>
							<li class="act_top_tab_on">最新评论</li>
							<li>&nbsp;</li>
							<li>&nbsp;</li>
						</ul>
                    </div>
					
					<div class="act_top_list">
						
						<div>
							<ul>
							<c:forEach items="${commentList}" var="comment">
								<li>
								<a href="<%=basePath %>article/${comment.articleId}.html#comment${comment.id}" title="${comment.content}">${comment.content}</a>
								<span class="info"><a title="${comment.uname}">${comment.uname}</a></span>
								</li>
							</c:forEach>
							</ul>
						</div>
					</div>
					
				</div>
				
				<!--标签列表-->
				<div class="act_top_list_m">
                    <div class="act_top_tab">
						<ul>
							<li class="act_top_tab_on">标签列表</li>
							<li>&nbsp;</li>
							<li>&nbsp;</li>
						</ul>
                    </div>
					
					<div class="tags_list">
					<c:forEach items="${tagList}" var="tag">
						<a href="<%=basePath %>search/index.htm?key=${tag.tagName}" title="${tag.tagName}">${tag.tagName}</a>
					</c:forEach>
					</div>
					
				</div>
				<!--标签列表结束-->
				
				<!--友情链接-->
				<div class="act_top_list_m">
                    <div class="act_top_tab">
						<ul>
							<li class="act_top_tab_on">友情链接</li>
							<li>&nbsp;</li>
							<li>&nbsp;</li>
						</ul>
                    </div>
					
					<div class="links">
					<c:forEach items="${linkList}" var="link">
						<a href="${link.linkurl}" title="${link.linkname}">${link.linkname}</a>
					</c:forEach>
					</div>
					
				</div>
				<!--标签列表结束-->
				
			</div>
			<!--act_top 结束-->
			
		</div>