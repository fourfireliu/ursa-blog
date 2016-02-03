//初始化提交分类信息相关
$(document).ready(function() {
	$(".type_link").click(function() {
		$("#cur_type_form").submit();
	});
	
	$(".update_link").click(function() {
		$("#cur_article_form").submit();
	})
});