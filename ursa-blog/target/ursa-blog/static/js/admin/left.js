$(function(){
		   
	$(".fmenu").toggle(function(){
		$(this).next().next().css("borderTop","1px solid #ccc");
		$(this).next().find("ul").slideDown(500);
		
		
	},function(){
		$(this).next().next().css("borderTop","none");
		$(this).next().find("ul").hide(500);
		
	});


});