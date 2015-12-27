
	var editor;
	KindEditor.ready(function(K) {
		editor = K.create('textarea[name="content"]', {
		allowFileManager : true
		
		uploadJson : 'tools/kindeditor-4.1.10/jsp/upload_json.jsp',

        fileManagerJson : 'tools/kindeditor-4.1.10/jsp/file_manager_json.jsp',

        allowFileManager : true,

        allowImageUpload : true, 

        autoHeightMode : true,

        afterCreate : function() {this.loadPlugin('autoheight');},

        afterBlur : function(){ this.sync(); }  //Kindeditor下获取文本框信息
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