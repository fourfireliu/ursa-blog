<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	int port=request.getServerPort();
	String basePath="";
	if(port==80){
		basePath=request.getScheme() + "://"+ request.getServerName()+path+"/";
	}
	else{
		basePath=request.getScheme() + "://"+ request.getServerName()+":"+port+path + "/";
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="<%=basePath %>static/kindeditor/themes/default/default.css" />
<link rel="stylesheet" href="<%=basePath %>static/kindeditor/plugins/code/prettify.css" />
<script charset="utf-8" src="<%=basePath %>static/kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="<%=basePath %>static/kindeditor/lang/zh_CN.js"></script>
<script charset="utf-8" src="<%=basePath %>static/kindeditor/plugins/code/prettify.js"></script>
<script>
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="content"]', {
				cssPath : '<%=basePath %>static/kindeditor/plugins/code/prettify.css',
				uploadJson : '<%=basePath %>static/kindeditor/jsp/upload_json.jsp',
				fileManagerJson : '<%=basePath %>static/kindeditor/jsp/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['form1'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['form1'].submit();
					});
				}
			});
			prettyPrint();
		});
	</script>

<style>
table {border-collapse: collapse;}
table td{border: 1px #ccc solid;padding:10px;}
</style>
</head>
<body>
<div style="width:980px; margin:0px auto">
  <form id="form1" name="form1" method="post" action="updateArticle.htm?op=${param.op}">
    <table width="100%" height="208" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="100px">文章类型�?</td>
        <td width="660px">
          <select name="typeId">
	          <c:forEach items="${typeList}" var="type">
	         
	         	 <option value="${type['id']}" <c:if test="${type['typeName'] eq article['typeName']}">selected="selected"</c:if>>${type['typeName']}</option>
	          </c:forEach>
          </select>
       </td>
      </tr>
      <tr>
        <td>�? 题：</td>
        <td>
        	<input name="id" type="hidden" value="${article.id}" />
          <input name="title" style="width:630px;" type="text" value="${article.title}" />
        </td>
      </tr>
       <tr>
        <td>标签：�?�号隔开</td>
        <td>
          <input name="tagList" style="width:120px" type="text" value="${article.taglist}" />
        </td>
      </tr>
      <tr>
        <td>�? 容：</td>
        <td>
        	<textarea name="content" cols="100" rows="30" style="width:640px;">${article.content}</textarea>
        </td>
      </tr>
      <tr>
        <td height="41" colspan="2">是否推荐
        
        <input type="radio" name="isCommand" value="1" />
        是否置顶(不制定，默认0)
        
        
        <input type="radio" name="top" value="0"  />
        是否启用
        
        
        <input name="status" type="radio" value="1" checked="checked" checked="checked" />
	</td>
      </tr>
      <tr>
        <td colspan="2"><label>
          <input type="submit" name="Submit" value="提交" />
        </label></td>
      </tr>
    </table>
  </form>
</div>
</body>
</html>