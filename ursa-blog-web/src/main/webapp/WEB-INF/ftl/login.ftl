<#import "common/header.ftl" as header>
<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="UTF-8"/>
		<meta name="keywords" content="Java, Blog, IT, Lift, Book, Read" />
		<meta name="description" content="Ursa的个人博客 记录生活" />
		<title>Ursa's Blog</title>
		<link type="text/css" href="<@s.url '/css/index.css'/>" rel="stylesheet"/>
		<link type="text/css" href="<@s.url '/css/base.css'/>" rel="stylesheet"/>
	</head>
	<body>
		<@header.header />
		<article>
			<div class="login">
				<form action="<@s.url '/admin/login'/>" method="POST">
					<p>
						ID: <input type="text" id="id" name="id" />
					</p>
					<p>
						密码: <input type="password" id="password" name="password" />
					</p>
					<br/>
					<input type="submit" value="登陆" />
				</form>
			</div>
		</article>
	</body>
</html>