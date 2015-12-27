<#import "../common/header.ftl" as header>
<#import "../common/footer.ftl" as footer>
<!DOCTYPE HTML>
<html>
	<head>
		<meta name="keywords" content="Java, Blog, IT, Lift, Book, Read" />
		<meta name="description" content="Ursa的个人博客 记录生活" />
		<title>Ursa's Blog</title>
		<link rel="shortcut icon" href="<@s.url '/images/myfavicon.ico'/>" type="image/x-icon" />
		<link type="text/css" href="<@s.url '/css/base.css'/>" rel="stylesheet"/>
		<link type="text/css" href="<@s.url '/css/writearticle.css'/>" rel="stylesheet"/>
		<link rel="stylesheet" href="<@s.url '/kindeditor/themes/default/default.css' />" />
		<script charset="utf-8" src="<@s.url '/kindeditor/kindeditor-min.js' />"></script>
		<script charset="utf-8" src="<@s.url '/kindeditor/lang/zh_CN.js' />"></script>
	</head>
	<body>
		<@header.header />
		<form id="article_edit_form" action="<@s.url '/admin/newarticle/submit'/>" method="POST" onsubmit="location.href='<@s.url '/index'/>';">
			<input type="hidden" id="selectTypeId" name="selectTypeId" value="-1" />
			<table id="editortable">
				<tr>
  					<td class="row1">博客标题<span style="color:red;margin-left:5px;font-weight:bold">*</span></td>
  					<td>
    					<select class="required" id="blog_whole_type" name="blog_whole_type">
    						<option value="">-选择分类-</option>
    						<#list typeInfos as typeInfo>
    							<option value=${typeInfo.id}>${typeInfo.name}</option>
    						</#list>
						</select>					
    					<input class="text required min-length-3 bad-words" id="title" maxlength="80" name="title" size="45" style="width:350px;" type="text" />
  					</td>
				</tr>
				<tr>
					<td colspan="2" align="left">
						<textarea id="content" name="content" style="width:800px;height:400px;visibility:hidden;">
						</textarea>
					</td>
				</tr>
				<tr>
					<td align="center">
        				<input class="submit" id="submit_button" name="commit" type="button" value="发布" />
      				</td>
					<td align="center">
        				<input class="submit" id="cancel_button" type="button" value="取消"/>
        			</td>
    			</tr>
			</table>
		</form>
		<script type="text/javascript" src="<@s.url '/js/writearticle/onload.js'/>"></script>
		<script type="text/javascript" src="<@s.url '/js/jquery.min.js'/>"></script>
		<@footer.footer />
	</body>
 </html>		