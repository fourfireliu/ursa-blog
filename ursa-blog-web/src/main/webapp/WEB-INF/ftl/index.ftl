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
			<div class="l_box f_l">
    			<div class="banner">
      				<div id="slide-holder">
        				<div id="slide-runner">
        					<a href="/" target="_blank"><img id="slide-img-1" src="<@s.url '/images/a1.jpg'/>"  alt="" /></a>
        					<a href="/" target="_blank"><img id="slide-img-2" src="<@s.url '/images/a2.jpg'/>"  alt="" /></a>
        					<a href="/" target="_blank"><img id="slide-img-3" src="<@s.url '/images/a3.jpg'/>"  alt="" /></a>
        					<a href="/" target="_blank"><img id="slide-img-4" src="<@s.url '/images/a4.jpg'/>"  alt="" /></a>
          					<div id="slide-controls">
            					<p id="slide-client" class="text">
            						<strong></strong>
            						<span></span>
            					</p>
            					<p id="slide-desc" class="text"></p>
            					<p id="slide-nav"></p>
          					</div>
        				</div>
      				</div>
			    </div>
			    <!-- banner代码结束 -->
			    <div class="topnews">
      				<h2>
      					<span id="taglist">
      						<ul>
      							<#list typeInfos as typeInfo>
      								<#if typeInfo_index lt 3>
      									<li class="tag_list_item"><a href="/" target="_blank">${typeInfo.name}</a></li>
      								<#elseif typeInfo_index = 3>
      									<li class="tag_list_item">
	      						    		<a href="/" target="_blank">更多</a>
    		  						  			<ul class="more_tag_list">
    		  						  				<li class="more_tag_item"> <a href="/" target="_blank">${typeInfo.name}</a></li>
    		  						<#else>
    		  							<li class="more_tag_item"> <a href="/" target="_blank">${typeInfo.name}</a></li>
    		  						</#if>
    		  						
    		  						<#if !typeInfo_has_next>
    		  								</ul>
    		  							</li>
    		  						</#if>
      							</#list>
      						</ul>
      					</span>
      					<b>文章</b>推荐
      				</h2>
      				<#list topArticles as topArticle>
      					<div class="blogs">
        					<figure><img src="<@s.url '/images/01.jpg'/>"></figure>
        					<ul>
          						<li><h3><a href="/">${topArticle.title}</a></h3></li>
				            	<li><p>${topArticle.content}</p></li>
          						<li>
          							<p class="autor">
          								<span class="lm f_l"><a href="/">个人博客</a></span>
		          						<span class="dtime f_l">${topArticle.modifyDate?date}</span>
		          						<span class="viewnum f_r">浏览（${topArticle.readCount}）</span>
		          						<span class="pingl f_r">评论（${topArticle.commentCount}）</span>
          							</p>
          						</li>
        					</ul>
      					</div>
      				</#list>
      		  	</div>
  			</div>
  			<div class="r_box f_r">
    			<div class="tit01">
      				<h3>技术站点</h3>
      				<div class="gzwm">
        				<ul>
          					<li><a class="xlwb" href="#" target="_blank">新浪微博</a></li>
          					<li><a class="txwb" href="#" target="_blank">腾讯微博</a></li>
          					<li><a class="rss" href="portal.php?mod=rss" target="_blank">RSS</a></li>
          					<li><a class="wx" href="mailto:admin@admin.com">邮箱</a></li>
        				</ul>
      				</div>
    			</div>
    			<!--tit01 end-->
    			<div class="ad300x100">
    				<img src="<@s.url '/images/ad300x100.jpg'/>">
    			</div>
    			<div class="moreSelect" id="lp_right_select"> 
		       		<div class="ms-top">
        				<ul class="hd" id="tab">
          					<li class="cur"><a href="/">点击排行</a></li>
          					<li><a href="/">最新文章</a></li>
          					<li><a href="/">站长推荐</a></li>
        				</ul>
      				</div>
      				<div class="ms-main" id="ms-main">
        				<div style="display: block;" class="bd bd-news" >
          					<ul>
          						<#list newArticles as newArticle>
          							<li><a href="/" target="_blank">${newArticle.title}</a></li>
          						</#list>
  					        </ul>
        				</div>
        				<div  class="bd bd-news">
          					<ul>
            					<li><a href="/" target="_blank">住在手机里的朋友</a></li>
            					<li><a href="/" target="_blank">教你怎样用欠费手机拨打电话</a></li>
            					<li><a href="/" target="_blank">原来以为，一个人的勇敢是，删掉他的手机号码...</a></li>
          					</ul>
	        			</div>
	        			<div class="bd bd-news">
	          				<ul>
	            				<li><a href="/" target="_blank">住在手机里的朋友</a></li>
	            				<li><a href="/" target="_blank">教你怎样用欠费手机拨打电话</a></li>
	            				<li><a href="/" target="_blank">原来以为，一个人的勇敢是，删掉他的手机号码...</a></li>
	          				</ul>
	        			</div>
	      			</div>
			    	<!--ms-main end --> 
    			</div>
		    	<!--切换卡 moreSelect end -->
    
	    		<div class="cloud">
	      			<h3>标签云</h3>
	      			<ul>
	        			<li><a href="/">个人博客</a></li>
				        <li><a href="/">web开发</a></li>
				        <li><a href="/">前端设计</a></li>
				        <li><a href="/">Html</a></li>
				        <li><a href="/">CSS3</a></li>
				        <li><a href="/">Html5+css3</a></li>
				        <li><a href="/">百度</a></li>
				        <li><a href="/">Javasript</a></li>
				        <li><a href="/">web开发</a></li>
				        <li><a href="/">前端设计</a></li>
				        <li><a href="/">Html</a></li>
				        <li><a href="/">CSS3</a></li>
				        <li><a href="/">Html5+css3</a></li>
				        <li><a href="/">百度</a></li>
	      			</ul>
	    		</div>
	    		<div class="tuwen">
	      			<h3>图文推荐</h3>
				    <ul>
			        	<li>
			        		<a href="/"><img src="<@s.url '/images/01.jpg'/>"><b>住在手机里的朋友</b></a>
			          		<p>
			          			<span class="tulanmu"><a href="/">手机配件</a></span>
			          			<span class="tutime">2015-02-15</span>
			          		</p>
			        	</li>
			        	<li>
			        		<a href="/"><img src="<@s.url '/images/01.jpg'/>"><b>住在手机里的朋友</b></a>
			          		<p>
			          			<span class="tulanmu"><a href="/">手机配件</a></span>
			          			<span class="tutime">2015-02-15</span>
			          		</p>
			        	</li>
			        	<li>
			        		<a href="/"><img src="<@s.url '/images/01.jpg'/>"><b>住在手机里的朋友</b></a>
			          		<p>
			          			<span class="tulanmu"><a href="/">手机配件</a></span>
			          			<span class="tutime">2015-02-15</span>
			          		</p>
			        	</li>
			      	</ul>
	    		</div>
	    		<div class="ad"> 
	    			<img src="<@s.url '/images/03.jpg'/>">
	    		</div>
	    		<div class="links">
	      			<h3><span>[<a href="/">申请友情链接</a>]</span>友情链接</h3>
	      			<ul>
	        			<li><a href="/">杨青个人博客</a></li>
				        <li><a href="/">web开发</a></li>
				        <li><a href="/">前端设计</a></li>
				        <li><a href="/">Html</a></li>
				        <li><a href="/">CSS3</a></li>
				        <li><a href="/">Html5+css3</a></li>
				        <li><a href="/">百度</a></li>
	      			</ul>
	    		</div>
  			</div>
  			<!--r_box end --> 
		</article>
		<footer>
  			<p class="ft-copyright">Ursa's Home Design by Fourfire 蜀ICP备11002373号-1</p>
  			<div id="tbox">
  				<a id="togbook" href="/"></a>
  				<a id="gotop" href="javascript:void(0)"></a>
  			</div>
		</footer>
		<script type="text/javascript" src="<@s.url '/js/jquery.min.js'/>"></script>
		<script type="text/javascript" src="<@s.url '/js/sliders.js'/>"></script>
		<script type="text/javascript" src="<@s.url '/js/nav.js'/>"></script>
		<script type="text/javascript" src="<@s.url '/js/sliderrunner.js'/>"></script>
		<script type="text/javascript" src="<@s.url '/js/onload.js'/>"></script>
	</body>
</html>