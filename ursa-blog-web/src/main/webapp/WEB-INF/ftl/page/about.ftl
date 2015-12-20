<#import "../common/header.ftl" as header>
<#import "../common/footer.ftl" as footer>
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
  				<span>您当前的位置：<a href="<@s.url '/index'/>">首页</a>&nbsp;>&nbsp;<a class="type_link" href="#">关于我</a></span>
  			</h1>
  			<div class="test">
    			<h2 class="c_titile">定风波</h2>
    			<p class="test1">
      					莫听穿林打叶声，
      					<br/>
      					何妨吟啸且徐行。
      					<br/> 
						竹杖芒鞋轻胜马，
						<br/>
						谁怕？
						<br/>
						一蓑烟雨任平生。 
						<br/>
						料峭春风吹酒醒，
						<br/>
						微冷，
						<br/>
						山头斜照却相迎。
						<br/> 
						回首向来萧瑟处，
						<br/>
						归去，
						<br/>
						也无风雨也无晴。 
      				</p	>
    		</div>
		</article>
		<@footer.footer />
		<script type="text/javascript" src="<@s.url '/js/jquery.min.js'/>"></script>
	</body>
</html>		