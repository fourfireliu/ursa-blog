<#import "../common/header.ftl" as header>
<#import "../common/footer.ftl" as footer>
<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="UTF-8"/>
		<meta name="keywords" content="Java, Blog, IT, Lift, Book, Read" />
		<meta name="description" content="Ursa的个人博客 记录生活" />
		<title>Ursa's Blog</title>
		<link type="text/css" href="<@s.url '/css/base.css'/>" rel="stylesheet"/>
		<link type="text/css" href="<@s.url '/css/articlelist.css'/>" rel="stylesheet"/>
	</head>
	<body>
		<@header.header />
		<article class="blogs">
			<h1 class="t_nav">
				<span class="n4">${typeDesc?default("全部博文")}</span>
				<span class="n3"><#if Session.userId??>&nbsp;<a href="<@s.url '/admin/writearticle'/>">我要发文</a></#if></span>
				<a href="<@s.url '/index'/>" class="n1">网站首页</a><a href="#" class="n2">${typeName?default("所有博文")}</a>
			</h1>
			
			<div class="newblog left">
				<#list articleList as articleInfo>
					<#if articleInfo_index gt 0>
						<div class="line"></div>
					</#if>
					<h2>
						<a title="${articleInfo.title}" href="<@s.url '/article/${articleInfo.id}'/>" >${articleInfo.title}</a>
					</h2>
	   				<p class="dateview">
	   					<span>发布时间：${articleInfo.modifyDate?date}</span><span>作者：${articleInfo.author}</span>
	 				</p>
	    			<ul class="nlist">
	      				<p>${articleInfo.content}</p>
	      				<a href="<@s.url '/article/${articleInfo.id}'/>" title="${articleInfo.title}" class="readmore">阅读全文>></a>
	    			</ul>
				</#list>
	    		<div class="line"></div>
    			<div class="blank"></div>
    			<div class="page">
    				<a title="Total record"><b>${totalCount}</b> </a>
    					<#if pageNo gt 1>
    						<a href="/news/index_2.html"><<</a><a href="/news/index_5.html"><</a>
    					</#if>
    					<#assign pageCount=(totalCount/pageSize+1)/>
    					<#if pageCount lt 6>
    						<#list 1..pageCount as curPage>
    							<#if curPage == pageNo>
    								<b>${pageNo}</b>
    							<#else>
    								<a href="/news/index_2.html">${curPage}</a>
    							</#if>
    						</#list>
    					<#else>
    						<#assign fromPage = 1 />
    						<#assign toPage = 5 />
    						<#if pageNo gt 3>
    							<#assign fromPage = (pageNo - 2) />
    							<#assign toPage = (pageNo + 2) />
    						</#if>
    						<#list fromPage..toPage as curPage>
    							<#if curPage == pageNo>
    								<b>${pageNo}</b>
    							<#else>
    								<a href="/news/index_2.html">${curPage}</a>
    							</#if>
    						</#list>
    					</#if>
    					<#if hasNext>
    						<a href="/news/index_2.html">></a><a href="/news/index_5.html">>></a>
    					</#if>
    			</div>
			</div>
		</article>
		<@footer.footer />
	</body>
</html>
		
