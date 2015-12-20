<#macro header>
	<!-- 置顶 -->
	<header>
  		<div class="logo f_l">
  			<a href="/"><img src="<@s.url '/images/logo.png'/>" /></a> 
  		</div>
  		<nav id="topnav" class="f_r">
    		<ul>
    			<li><a href="<@s.url '/index'/>">首页</a>&nbsp;<a href="myself.html">关于我</a>&nbsp;<a href="<@s.url '/articlelist'/>">文章列表</a><#if userId??><span>欢迎你, ${userId}&nbsp;<a href="<@s.url '/admin/logout'/>">注销</a></span><#else>&nbsp;<a href="<@s.url '/admin/login'/>">博主登录</a></#if></li> 
    		</ul>
    	</nav>
	</header>
</#macro>