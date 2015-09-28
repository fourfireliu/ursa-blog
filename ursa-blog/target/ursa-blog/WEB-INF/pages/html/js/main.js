$(function(){
	//导航二级菜单显示隐藏控制
	$(".nav>ul>li:has(ul)").mousemove(function(){
		//css控制高度无效问题							   
		$(this).find("a").first().css("height","33px");
		
		$(this).find("a").first().addClass("ona");
		$(this).find("a").css("color","#686868");
		$(this).find("ul").unbind("mousemove").slideDown(150);
	});
	$(".nav>ul>li:has(ul)").mouseleave(function(){
		$(this).find("ul").unbind("mouseleave").slideUp(100);
		$(this).find("a").css("color","#fff");
		$(this).find("a").first().removeClass("ona");
	});
	
	$(".nav>ul>li>ul>li>a").mousemove(function(){
		$(this).css("background-color","#ccc");
	});
	$(".nav>ul>li>ul>li>a").mouseleave(function(){
		$(this).css("background-color","#f3f3f3");
	});
	
	$(".act").each(function(index){
		$(this).css("border-bottom-color","#"+(Math.floor(Math.random()*899999+100000)));
	});
	
	/*首页日志标题随机颜色*/
	$(".act_title a").each(function(index){
		$(this).css("color","#"+(Math.floor(Math.random()*899999+100000)));
	});
	/*热门搜索上色*/
	$(".hot_tag a").each(function(index){
		$(this).css("color","#"+(Math.floor(Math.random()*899999+100000)));
	});
	/*标签上色*/
	$(".act_tag a").each(function(index){
		$(this).css("color","#"+(Math.floor(Math.random()*899999+100000)));
	});
	
	/*右侧日志排行选项卡*/
	$("#act_top_tab ul li").click(function(){
		var i=$(this).index();
		$("#act_top_tab ul li:not(eq("+i+"))").removeClass("act_top_tab_on");
		$("#act_top_tab ul li:not(eq("+i+"))").addClass("act_top_tab");
		
		$(this).removeClass("act_top_tab");
		$(this).addClass("act_top_tab_on");
		$("#act_top_list div").hide();
		$("#act_top_list div:eq("+i+")").fadeIn(500);
		
	});
	
	/*鼠标移到日志评论上*/
	
	
	$(".act_top_list ul li").mousemove(function(){
		$(this).css("background","#f8f8f8");
		$(this).css("border-bottom-color","#333");
	});
	$(".act_top_list ul li").mouseleave(function(){
		$(this).css("background","#fff");
		$(this).css("border-bottom-color","#ccc");
	});
	
	/*标签上色*/
	$(".tags_list a").each(function(){
		$(this).css("color","#"+(Math.floor(Math.random()*899999+100000)));
	});
	
});