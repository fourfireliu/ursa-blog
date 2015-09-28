<%@tag import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@tag import="java.net.URLEncoder"%>
<%@ tag pageEncoding="UTF-8" %>
<%@ attribute name="pm" required="true" type="com.fourfire.blog.model.PageModel" %>
<%@ attribute name="url" required="true" type="java.lang.String" %>
<%@ attribute name="id" required="false" type="java.lang.Integer" %>
<a href="${url}?page=1&id=${id}&key=${pm.keyWords}">第一页</a>
 <%
 int page=1;
 try{
	 page=Integer.parseInt(request.getParameter("page"));
 }catch(Exception e){
	 
 }

 int cp=(int)page/10;
 int start=cp*10;
 if(start<=0){
	 start=1;
 }
 int end =(cp+1)*10;

 if(pm.getSumPage()<10){
	 end=pm.getSumPage(); 
 }
 
 for(int i=start;i<=end;i++){
	 if(i>pm.getSumPage()){
		 break;
	 }
	    out.print("<a");
	  	if(i==pm.getCurrentPage()){
	  		out.print(" class=\"now-page\" ");
	  	}
		%> href="${url}?page=<%=i %>&key=${pm.keyWords}&id=${id}"><%=i %><%
	    out.print("</a>");
 }
 %>
 <a href="${url}?page=${pm.sumPage}&id=${id}&key=${pm.keyWords}">末&nbsp;&nbsp;页</a>&nbsp;&nbsp;&nbsp;&nbsp;总记录：${pm.sumCount}条&nbsp;&nbsp;共${pm.sumPage}页