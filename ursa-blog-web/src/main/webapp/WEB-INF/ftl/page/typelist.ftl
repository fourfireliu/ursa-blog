<#import "../common/header.ftl" as header>
<#import "../common/footer.ftl" as footer>
<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="UTF-8"/>
		<meta name="keywords" content="Java, Blog, IT, Lift, Book, Read" />
		<meta name="description" content="Ursa的个人博客 记录生活" />
		<title>Ursa's Blog</title>
		<link type="text/css" href="<@s.url '/css/base.css'/>" rel="stylesheet"/>
		<link type="text/css" href="<@s.url '/css/typelist.css'/>" rel="stylesheet"/>
	</head>
	<body>
		<@header.header />
		<article class="blogs">
			<form id="cur_type_form" action="<@s.url '/articlelist'/>" method="POST">
				<input type="hidden" id="typeId" name="typeId" value="-1"/>
      			<input type="hidden" id="typeName" name="typeName" value=""/>
      			<input type="hidden" id="typeDesc" name="typeDesc" value=""/>
      		</form>
			<div class='blog_main_title'>
				<h2>博文分类</h2>
  				<div id='fd'></div>
			</div>
			<table class="admin">
  				<thead>
    				<tr>
				      <td>名称</td>
				      <td>文章数量</td>
    				</tr>
  				</thead>
  				<tbody>
  					<#list typeInfos as typeInfo>
  						<tr>
  							<td>
  								<a class="type_link" href="javascript:void(0)">${typeInfo.name}</a>
  								<input type="hidden" id="type_id" value=${typeInfo.id} />
      							<input type="hidden" id="type_name" value=${typeInfo.name} />
      							<input type="hidden" id="type_desc" value=${typeInfo.description} />
  							</td>
  							<td>${typeInfo.articleCount}</td>
  						</tr>
  					</#list>
				</tbody>
			</table>
		</article>
		<@footer.footer />
		<script type="text/javascript" src="<@s.url '/js/jquery.min.js'/>"></script>
		<script type="text/javascript" src="<@s.url '/js/typelist/onload.js'/>"></script>
	</body>
</html>