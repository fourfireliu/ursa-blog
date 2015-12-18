
	var editor;
	KindEditor.ready(function(K) {
		editor = K.create('textarea[name="content"]', {
		allowFileManager : true
	});	
					
	K('input[name=commit]').click(function(e) {
			var htmlContent = editor.html();
			$("#content").val(htmlContent);
			$("#selectTypeId").val($("#blog_whole_type option:selected").text());
			$("#article_edit_form").submit();
		});
	});