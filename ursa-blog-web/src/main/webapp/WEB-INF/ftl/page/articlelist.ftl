<#import "../common/header.ftl" as header>
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
				<span>${typeDesc}</span>
				<a href="<@s.url '/index'/>" class="n1">网站首页</a><a href="#" class="n2">${typeName}</a>
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
    				<a title="Total record"><b>106</b> </a><b>1</b><a href="/news/index_2.html">2</a><a href="/news/index_3.html">3</a><a href="/news/index_4.html">4</a><a href="/news/index_5.html">5</a><a href="/news/index_2.html">></a><a href="/news/index_5.html">>></a>
    			</div>
			</div>
		</article>
		<footer>
			<p>Design by DanceSmile <a href="http://www.miitbeian.gov.cn/" target="_blank">蜀ICP备11002373号-1</a></p>
		</footer>
	</body>
</html>
		
