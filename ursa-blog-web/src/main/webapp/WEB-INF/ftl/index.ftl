<#import "common/header.ftl" as header>
<#import "common/footer.ftl" as footer>
<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="UTF-8"/>
		<meta name="keywords" content="Java, Blog, IT, Lift, Book, Read" />
		<meta name="description" content="Ursa的个人博客 记录生活" />
		<title>Ursa's Blog</title>
		<link rel="shortcut icon" href="<@s.url '/images/myfavicon.ico'/>" type="image/x-icon" />
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
        					<a href="/" target="_blank"><img id="slide-img-5" src="<@s.url '/images/a5.jpg'/>"  alt="" /></a>
        					<a href="/" target="_blank"><img id="slide-img-6" src="<@s.url '/images/a6.jpg'/>"  alt="" /></a>
        					<a href="/" target="_blank"><img id="slide-img-7" src="<@s.url '/images/a7.jpg'/>"  alt="" /></a>
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
      						<form id="cur_type_form" action="<@s.url '/articlelist'/>" method="POST">
      							<input type="hidden" id="typeId" name="typeId" value="-1"/>
      							<input type="hidden" id="typeName" name="typeName" value=""/>
      							<input type="hidden" id="typeDesc" name="typeDesc" value=""/>
      						</form>
      						<ul>
      							<#list typeInfos as typeInfo>
      								<#if typeInfo_index lt 3>
      									<li class="tag_list_item">
      										<a class="type_link" href="javascript:void(0)">${typeInfo.name}</a>
      										<input type="hidden" id="type_id" value=${typeInfo.id} />
      										<input type="hidden" id="type_name" value=${typeInfo.name} />
      										<input type="hidden" id="type_desc" value=${typeInfo.description} />
      									</li>
      								<#elseif typeInfo_index = 3>
      									<li class="tag_list_item">
	      						    		<a class="type_link" href="<@s.url '/typelist'/>">全部分类</a>
	      						    	</li>
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
          						<li><h3><a href="<@s.url '/article/${topArticle.id}'/>">${topArticle.title}</a></h3></li>
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
          					<li><a class="xlwb" href="#">新浪微博</a></li>
          					<li><a class="txwb" href="#">腾讯微博</a></li>
          					<li><a class="rss" href="#">RSS</a></li>
          					<li><a class="wx" href="#">邮箱</a></li>
        				</ul>
      				</div>
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
        				<div style="display: block;" class="bd" id="read_count_ranking" >
          					<ul>
          						<#list hotArticles as hotArticle>
          							<li><a href="<@s.url '/article/${hotArticle.id}'/>">${hotArticle.title}</a></li>
          						</#list>
  					        </ul>
        				</div>
        				<div  class="bd" id="new_blog_ranking">
          					<ul>
            					<#list newArticles as newArticle>
          							<li><a href="<@s.url '/article/${newArticle.id}'/>">${newArticle.title}</a></li>
          						</#list>
          					</ul>
	        			</div>
	        			<div class="bd" id="comment_count_ranking">
	          				<ul>
	          					<#list topArticles as topArticle>
	          						<li><a href="<@s.url '/article/${topArticle.id}'/>">${topArticle.title}</a></li>
	          					</#list>
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
				        <li><a href="/">Javasript</a></li>
	      			</ul>
	    		</div>
  			</div>
  			<!--r_box end --> 
		</article>
		<@footer.footer />
		<script type="text/javascript" src="<@s.url '/js/jquery.min.js'/>"></script>
		<script type="text/javascript" src="<@s.url '/js/sliders.js'/>"></script>
		<script type="text/javascript" src="<@s.url '/js/nav.js'/>"></script>
		<script type="text/javascript" src="<@s.url '/js/sliderrunner.js'/>"></script>
		<script type="text/javascript" src="<@s.url '/js/index/onload.js'/>"></script>
	</body>
</html>