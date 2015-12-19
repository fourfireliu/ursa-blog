//初始化提交分类信息相关
$(document).ready(function() {
	$(".type_link").click(function() {
		var id = $(this).siblings("#type_id").val();
		var name = $(this).siblings("#type_name").val();
		var desc = $(this).siblings("#type_desc").val();
		
		$("#typeId").val(id);
		$("#typeName").val(name);
        $("#typeDesc").val(desc);
        
        $("#cur_type_form").submit();
	});
});