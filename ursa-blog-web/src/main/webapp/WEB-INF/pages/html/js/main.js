$(function(){
	//���������˵���ʾ���ؿ���
	$(".nav>ul>li:has(ul)").mousemove(function(){
		//css���Ƹ߶���Ч����							   
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
	
	/*��ҳ��־���������ɫ*/
	$(".act_title a").each(function(index){
		$(this).css("color","#"+(Math.floor(Math.random()*899999+100000)));
	});
	/*����������ɫ*/
	$(".hot_tag a").each(function(index){
		$(this).css("color","#"+(Math.floor(Math.random()*899999+100000)));
	});
	/*��ǩ��ɫ*/
	$(".act_tag a").each(function(index){
		$(this).css("color","#"+(Math.floor(Math.random()*899999+100000)));
	});
	
	/*�Ҳ���־����ѡ�*/
	$("#act_top_tab ul li").click(function(){
		var i=$(this).index();
		$("#act_top_tab ul li:not(eq("+i+"))").removeClass("act_top_tab_on");
		$("#act_top_tab ul li:not(eq("+i+"))").addClass("act_top_tab");
		
		$(this).removeClass("act_top_tab");
		$(this).addClass("act_top_tab_on");
		$("#act_top_list div").hide();
		$("#act_top_list div:eq("+i+")").fadeIn(500);
		
	});
	
	/*����Ƶ���־������*/
	
	
	$(".act_top_list ul li").mousemove(function(){
		$(this).css("background","#f8f8f8");
		$(this).css("border-bottom-color","#333");
	});
	$(".act_top_list ul li").mouseleave(function(){
		$(this).css("background","#fff");
		$(this).css("border-bottom-color","#ccc");
	});
	
	/*��ǩ��ɫ*/
	$(".tags_list a").each(function(){
		$(this).css("color","#"+(Math.floor(Math.random()*899999+100000)));
	});
	
});