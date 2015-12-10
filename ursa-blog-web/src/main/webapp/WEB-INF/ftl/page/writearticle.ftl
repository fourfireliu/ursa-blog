<#import "../common/header.ftl" as header>
<!DOCTYPE HTML>
<html>
	<head>
		<meta name="keywords" content="Java, Blog, IT, Lift, Book, Read" />
		<meta name="description" content="Ursa的个人博客 记录生活" />
		<title>Ursa's Blog</title>
		<link type="text/css" href="<@s.url '/css/base.css'/>" rel="stylesheet"/>
		<link type="text/css" href="<@s.url '/css/article.css'/>" rel="stylesheet"/>
		<!-- 加载编辑器的容器 -->
    	<script id="container" name="content" type="text/plain">
        	这里写你的初始化内容
    	</script>
    	<!-- 配置文件 -->
    	<script type="text/javascript" src="ueditor.config.js"></script>
    	<!-- 编辑器源码文件 -->
    	<script type="text/javascript" src="ueditor.all.js"></script>
    	<!-- 实例化编辑器 -->
    	<script type="text/javascript">
        	var ue = UE.getEditor('container');
    	</script>
	</head>
	<body>
		<@header.header />
		