$(function(){
	//导航二级菜单显示隐藏控制
	$(".nav>ul>li").mousemove(function(){
		//css控制高度无效问题							   
		$(this).find("a").first().css("height","33px");
		
		$(this).find("a").first().addClass("ona");
		$(this).find("a").css("color","#686868");
		$(this).find("ul").unbind("mousemove").slideDown(150);
	});
	$(".nav>ul>li").mouseleave(function(){
		$(this).find("ul").unbind("mouseleave").slideUp(100);
		$(this).find("a").css("color","#fff");
		$(this).find("a").first().removeClass("ona");
		$(this).find("a").first().css("height","40px");
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
	
	$(".act_right span").each(function(){
		$(this).css("color","#"+(Math.floor(Math.random()*899999+100000)));
	});
	/*link上色*/
	$(".links a").each(function(){
		//$(this).css("color","#"+(Math.floor(Math.random()*899999+100000)));
		$(this).css("background","#"+(Math.floor(Math.random()*899999+100000)));
		$(this).css("color","#Fff");
	});
	
	/*推荐*/
	$(".recommend_btn").click(function(){
		$(this).attr("disabled","disabled");
		var id=$(this).attr("aid");
		$.get("commendAritcle.htm",{"id":id},function(data){
			if(data="ok"){
				alert("推荐成功，谢谢！");
			}
			else{
				alert("抱歉，推荐失败！");
			}
			$(".recommend_btn").removeAttr("disabled");
		});
		
	});
	
	/*双击显示大图
	$(".article_content img").dblclick(function(){
		var th=$(this);
		if(th.attr("src")!=""){
			$("").remove();
			var img_div=$("<div id=\"showimg\" style=\"text-align:center;top:"+(getTop()+400)+"px;left:200px\"  class=\"imgdiv\"><div style=\"text-align:center;padding-right:15px;\"><a style=\"color:#333;font-size:16px\" href=\"javascript:closeShowImg()\">关闭</a></div><div><img src=\""+th.attr("src")+"\" /><div></div>");
			$("body").append(img_div);
		}
	});*/
});
function closeShowImg(){
	$("#showimg").remove();
}
function getTop(){
	
	var clientHeight=0;
	if(document.body.clientHeight&&document.documentElement.clientHeight){
 		clientHeight = (document.body.clientHeight<document.documentElement.clientHeight)?document.body.clientHeight:document.documentElement.clientHeight;
	}
	
	else{
		clientHeight = (document.body.clientHeight>document.documentElement.clientHeight)?document.body.clientHeight:document.documentElement.clientHeight;
		
	}
	
	return clientHeight;
}
function getLeft(){
	
	var clientWidth=0;
	if(document.body.clientWidth&&document.documentElement.clientWidth){

 		clientWidth = (document.body.clientWidth<document.documentElement.clientWidth)?document.body.clientWidth:document.documentElement.clientWidth;
	}
	else{
		clientWidth =(document.body.clientWidth>document.documentElement.clientWidth)?document.body.clientWidth:document.documentElement.clientWidth;
	}
	
	return clientWidth;
}
function checkMsg(){
	var username=document.getElementById("username").value;
	if(username==""){
		alert("请问尊姓大名...");
		return false;
	}
	var mail_qq=document.getElementById("mail_qq").value;
	if(mail_qq==""){
		alert("请问如何联系你呢...");
		return false;
	}
	var comment=document.getElementById("comment").value;
	if(comment==""){
		alert("想给我说点什么呢...");
		return false;
	}
	
	
	
}

function checkComment(){
	var articleId=document.getElementById("articleId").value;
	if(articleId==""){
		alert("没有这篇日志，不能评论哟...");
		return false;
	}
	var uname=document.getElementById("uname").value;
	if(uname==""){
		alert("问尊姓大名...");
		return false;
	}
	var mail_qq=document.getElementById("mail_qq").value;
	if(mail_qq==""){
		alert("请问如何联系你呢...");
		return false;
	}
	var comment=document.getElementById("comment").value;
	if(comment==""){
		alert("想说点什么呢...");
		return false;
	}
}
