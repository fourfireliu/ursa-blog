
	var editor;
	KindEditor.ready(function(K) {
		editor = K.create('textarea[name="content"]', {
		allowFileManager : true
	});	
					
	K('input[name=commit]').click(function(e) {
		var selectTypeId = $("#blog_whole_type option:selected").val();
		if (selectTypeId == undefined || selectTypeId == "") {
			alert("请选择文章分类");
		} else {
			var htmlContent = editor.html();
			$("#content").val(htmlContent);
			$("#selectTypeId").val(selectTypeId);
			$("#article_edit_form").submit();
		}
	});
});