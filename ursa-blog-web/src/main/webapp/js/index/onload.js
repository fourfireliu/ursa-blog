window.onload = function ()
{
	var oLi = document.getElementById("tab").getElementsByTagName("li");
	var oUl = document.getElementById("ms-main").getElementsByTagName("div");
	
	for(var i = 0; i < oLi.length; i++) {
		oLi[i].index = i;
		oLi[i].onmouseover = function ()
		{
			for(var n = 0; n < oLi.length; n++) {
				oLi[n].className="";
			}
			this.className = "cur";
			for(var n = 0; n < oUl.length; n++) {
				oUl[n].style.display = "none";
			}
			oUl[this.index].style.display = "block";
		};
	}
};

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