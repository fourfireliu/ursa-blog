<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<div class="bottom">
	<div class="bottom_content">本站采用Java语言�?�?,Spring框架,欢迎朋友们提意见。重新对页面进行布局，修改了程序，方便开源使用，由于本人美工真的不行 ，很少用背景图片，页面基本都用背景色...<br/>
	  主题：无色无�? | <a title="网站地图" href="http://www.shack2.org/baiduSiteMap.xml">网站地图</a>|<script src="http://s15.cnzz.com/stat.php?id=4159773&web_id=4159773" language="JavaScript"></script><br/>
	  Copyright (c) 2012-2013 <a href="http://www.shack2.org/" target="_blank">www.shack2.org</a> All Rights Reserved. | 空ICP�?111111111�? | 程序设计: shack2 Powered by SJBlog v1.0
	联系QQ�?1341413415</div>
</div>