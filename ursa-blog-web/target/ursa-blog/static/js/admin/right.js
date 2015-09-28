$(function(){
	$(".list_table tr:gt(0)").hover(function(){
		$(this).addClass("m_ontr");
						   
	},function(){
		$(this).removeClass("m_ontr");
	});
});