<#import "../common/header.ftl" as header>
<!DOCTYPE HTML>
<html>
	<head>
		<meta name="keywords" content="Java, Blog, IT, Lift, Book, Read" />
		<meta name="description" content="Ursa的个人博客 记录生活" />
		<title>Ursa's Blog</title>
		<link type="text/css" href="<@s.url '/css/base.css'/>" rel="stylesheet"/>
		<link type="text/css" href="<@s.url '/css/writearticle.css'/>" rel="stylesheet"/>
		
		<link rel="stylesheet" href="<@s.url '/css/default.css' />" />
		<script charset="utf-8" src="<@s.url '/js/kindeditor-min.js' />"></script>
		<script charset="utf-8" src="<@s.url '/js/zh_CN.js' />"></script>
		<script>
			var editor;
			KindEditor.ready(function(K) {
				editor = K.create('textarea[name="content"]', {
					allowFileManager : true
				});
			});
		</script>
	</head>
	<body>
		<@header.header />
		<form>
			<table id="editortable">
				<tr>
  					<td class="row1">博客标题<span style="color:red;margin-left:5px;font-weight:bold">*</span></td>
  					<td>
    					<select class="required" id="blog_whole_category_id" name="blog[whole_category_id]">
    						<option value="">-选择分类-</option>
    						<#list typeInfos as typeInfo>
    							<option value=${typeInfo_index+1}>${typeInfo.name}</option>
    						</#list>
						</select>					
    					<input class="text required min-length-3 bad-words" id="blog_title" maxlength="80" name="blog[title]" size="45" style="width:350px;" type="text" />
  					</td>
				</tr>
				<tr>
					<td colspan="2" align="left">
						<textarea name="content" style="width:800px;height:400px;visibility:hidden;">
							${topicContent!''}
						</textarea>
					</td>
				</tr>
				<tr>
					<td align="center">
        				<input class="submit" id="submit_button" name="commit" type="submit" value="发布" />
      				</td>
					<td align="center">
        				<input class="submit" id="cancel_button" type="button" value="取消"/>
        			</td>
    			</tr>
			</table>
		</form>
    </body> 
</html>		