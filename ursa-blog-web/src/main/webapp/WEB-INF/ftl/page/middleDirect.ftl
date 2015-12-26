<#import "../common/header.ftl" as header>
<#import "../common/footer.ftl" as footer>
<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="UTF-8"/>
		<meta name="keywords" content="Java, Blog, IT, 旅游, 读书, 生活" />
		<meta name="description" content="Ursa的个人博客 记录生活" />
		<meta http-equiv="refresh" content="3;URL=<@s.url '/index'/>">
		<title>Ursa's Blog</title>
		<link rel="shortcut icon" href="<@s.url '/images/myfavicon.ico'/>" type="image/x-icon" />
		<link type="text/css" href="<@s.url '/css/base.css'/>" rel="stylesheet"/>
		<link type="text/css" href="<@s.url '/css/article.css'/>" rel="stylesheet"/>
	</head>
	<body>
		<@header.header />
		<article class="blogs">
			<span>
				<p>${info}</p>3秒后自动进入首页
			</span>
		</article>
		<@footer.footer />
	</body>
</html>