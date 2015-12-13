<#import "../common/header.ftl" as header>
<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="UTF-8"/>
		<meta name="keywords" content="Java, Blog, IT, 旅游, 读书, 生活" />
		<meta name="description" content="Ursa的个人博客 记录生活" />
		<title>Ursa's Blog</title>
		<link type="text/css" href="<@s.url '/css/base.css'/>" rel="stylesheet"/>
		<link type="text/css" href="<@s.url '/css/article.css'/>" rel="stylesheet"/>
	</head>
	<body>
		<@header.header />
		<article class="blogs">
  			<h1 class="t_nav">
  				<span>您当前的位置：<a href="/">首页</a>&nbsp;>&nbsp;<a href="/jstt/">${typeInfo.name}</a></span>
  			</h1>
  			<div class="index_about">
    			<h2 class="c_titile">${articleInfo.title}</h2>
    			<p class="box_c">
    				<span class="d_time">发布时间：${articleInfo.createDate?date}</span><span>作者：<a href='mailto:dancesmiling@qq.com'>${articleInfo.author}</a></span><span>阅读（${articleInfo.readCount}）</span>
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
		<div id="tbox">
			<a id="togbook" href="/e/tool/gbook/?bid=1"></a> <a id="gotop" href="javascript:void(0)" style="display: block;"></a>
		</div>
		<footer>
  			<p>Design by DanceSmile <a href="http://www.miitbeian.gov.cn/" target="_blank">蜀ICP备11002373号-1</a></p>
		</footer>
	</body>
</html>		