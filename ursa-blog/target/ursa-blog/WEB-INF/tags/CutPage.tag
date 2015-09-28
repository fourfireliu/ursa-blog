<%@tag import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@tag import="java.net.URLEncoder"%>
<%@ tag pageEncoding="UTF-8" %>
<%@ attribute name="pm" required="true" type="com.fourfire.blog.model.PageModel" %>
<%@ attribute name="url" required="true" type="java.lang.String" %>
<%@ attribute name="id" required="false" type="java.lang.Integer" %>
<a href="${url}?page=1&id=com.fourfire:ursa-blog:war:1.0&key=${pm.keyWords}">ç¬¬ä¸€é¡?</a>
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
		%> href="${url}?page=<%=i %>&key=${pm.keyWords}&id=com.fourfire:ursa-blog:war:1.0"><%=i %><%
	    out.print("</a>");
 }
 %>
 <a href="${url}?page=${pm.sumPage}&id=com.fourfire:ursa-blog:war:1.0&key=${pm.keyWords}">æœ?&nbsp;&nbsp;é¡?</a>&nbsp;&nbsp;&nbsp;&nbsp;æ€»è®°å½•ï¼š${pm.sumCount}æ?&nbsp;&nbsp;å…?${pm.sumPage}é¡?