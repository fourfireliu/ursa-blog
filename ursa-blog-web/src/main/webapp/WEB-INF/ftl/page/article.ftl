<#import "../common/header.ftl" as header>
<#import "../common/footer.ftl" as footer>
<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="UTF-8"/>
		<meta name="keywords" content="Java, Blog, IT, 旅游, 读书, 生活" />
		<meta name="description" content="Ursa的个人博客 记录生活" />
		<title>Ursa's Blog</title>
		<link rel="shortcut icon" href="<@s.url '/images/myfavicon.ico'/>" type="image/x-icon" />
		<link type="text/css" href="<@s.url '/css/base.css'/>" rel="stylesheet"/>
		<link type="text/css" href="<@s.url '/css/article.css'/>" rel="stylesheet"/>
	</head>
	<body>
		<@header.header />
		<article class="blogs">
			<form id="cur_type_form" action="<@s.url '/articlelist'/>" method="POST">
      			<input type="hidden" id="typeId" name="typeId" value="${articleInfo.type}" />
      		</form>
  			<h1 class="t_nav">
  				<span>您当前的位置：<a href="<@s.url '/index'/>">首页</a>&nbsp;>&nbsp;<a class="type_link" href="javascript:void(0)">${typeInfo.name}</a></span>
  			</h1>
  			<div class="index_about">
  				<form id="cur_article_form" action="<@s.url '/admin/writearticle'/>" method="POST">
  					<input type="hidden" id="articleId" name="articleId" value="${articleInfo.id}" />
  				</form>
    			<h2 class="c_titile">${articleInfo.title}</h2>
    			<p class="box_c">
    				<span class="d_time">发布时间：${articleInfo.createDate?date}</span><span>作者：<a href='mailto:fourfireliu@gmail.com'>${articleInfo.author}</a></span><span>阅读（${articleInfo.readCount}）</span>
    				<span class="n3"><#if Session.userId??>&nbsp;<a class="update_link" href="javascript:void(0)">编辑</a></#if></span>
    			</p>
    			<ul class="infos">
      				<p>${articleInfo.content}</p>
    			</ul>
			    <div class="nextinfo">
			    	<#if previousArticle??>
						<p>上一篇：<a href="<@s.url '/article/${previousArticle.id}'/>">${previousArticle.title}</a></p>
					</#if>
					<#if nextArticle??>
						<p>下一篇：<a href="<@s.url '/article/${nextArticle.id}'/>">${nextArticle.title}</a></p>
					</#if>
    			</div>
    		</div>
		</article>
		<@footer.footer />
		<script type="text/javascript" src="<@s.url '/js/jquery.min.js'/>"></script>
		<script type="text/javascript" src="<@s.url '/js/article/onload.js'/>"></script>
	</body>
</html>		