package com.fourfire.blog.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fourfire.blog.dao.ArticleInfoDao;
import com.fourfire.blog.dao.TagInfoDao;
import com.fourfire.blog.dao.TypeInfoDao;
import com.fourfire.blog.model.PageModel;
import com.fourfire.blog.util.Tools;

@Controller
@RequestMapping(value = "/admin")
public class AdminAction {

	@Resource(name = "articleDaoImpl")
	private ArticleInfoDao adao;

	@Resource(name = "typeInfoDaoImpl")
	private TypeInfoDao tdao;

	@Resource(name = "tagInfoDaoImpl")
	private TagInfoDao tagdao;

	@RequestMapping(value = "/article/updateArticle")
	public String addAritcle(HttpServletRequest request,
			HttpServletResponse response) {

		int typeId = ServletRequestUtils.getIntParameter(request, "typeId", 0);
		String title = Tools.checkString(request.getParameter("title"));
		String content = ServletRequestUtils.getStringParameter(request,
				"content", "").replace("'", "&#39;");
		int isCommend = ServletRequestUtils.getIntParameter(request,
				"isCommend", 0);
		int top = ServletRequestUtils.getIntParameter(request, "top", 0);
		int status = ServletRequestUtils.getIntParameter(request, "status", 0);
		String ip = request.getRemoteAddr();
		String tagList = Tools.checkString(request.getParameter("tagList"));

		long articleId = Tools.getId();

		String sql = "insert into articleinfo values(" + articleId + ",1,"
				+ typeId + ",'" + title + "','" + content + "','shack2',0,"
				+ status + ",0,'" + ip + "','" + Tools.getTime() + "'," + top
				+ "," + isCommend + ")";

		String op = ServletRequestUtils.getStringParameter(request, "op", "");
		// 更新
		if (op.equals("update")) {
			articleId = ServletRequestUtils.getLongParameter(request, "id", 0);
			sql = "update articleinfo set typeId=" + typeId + ",top=" + top
					+ ",status=" + status + ",title='" + title + "',content='"
					+ content + "' where id=" + articleId;
			int result = adao.update(sql);
			if (result <= 0) {
				request.setAttribute("error", "更新失败...");
				return "/error/index";
			}
			request.setAttribute("error", "更新成功...");
			return "/error/index";
		}
	
		int result = adao.addArticle(sql);

		if (result <= 0) {
			request.setAttribute("error", "添加失败...");
			return "/error/index";
		}
		if (Tools.checkNotNull(Tools.checkString(tagList))) {
			// int articleId=adao.getUpdateArticleId();
			String[] tags = tagList.split(",");
			for (String tag : tags) {
				String sql_tag = "insert into taginfo values(null," + articleId
						+ ",'" + tag + "')";
				adao.update(sql_tag);
			}

		}
		request.setAttribute("error", "添加成功...");
		return "/error/index";

	}

	@RequestMapping(value = "/article/article")
	public String getArticle(HttpServletRequest request,
			HttpServletResponse response) {
		String op = ServletRequestUtils.getStringParameter(request, "op", "");
		List ftypeList = tdao.getAllTypeInfo();
		request.setAttribute("typeList", ftypeList);

		if ("add".equals(op)) {
			return "/admin/article/article";
		}

		int id = ServletRequestUtils.getIntParameter(request, "id", 0);

		Map<String, Object> article = adao.getAticleInfoById(id);
		request.setAttribute("article", article);
		List tagList = tagdao.getTagListByArticleId(id);

		String tagStr = Tools.listToString(tagList);
		if (article != null) {
			article.put("taglist", tagStr);
		}
		return "/admin/article/article";
	}

	@RequestMapping(value = "/article/articleList")
	public String getArticleList(HttpServletRequest request,
			HttpServletResponse response) {
		int page = ServletRequestUtils.getIntParameter(request, "page",1);
		int pageCount = ServletRequestUtils.getIntParameter(request, "pageCount",10);
		PageModel pm = adao.getArticleInfoByPage(0, page, pageCount);
		
		// 查询对应的标签放入list
		List<Map> list = pm.getList();
		
		for (Map map : list) {
			int id = Integer.parseInt(map.get("id") + "");
			List<Map> tagsList = tagdao.getTagListByArticleId(id);
			String tagstr=Tools.listToString(tagsList);
			map.put("tagsList", tagstr);
		}
		request.setAttribute("pm", pm);
		return "/admin/article/articleList";
	}

	@RequestMapping(value = "/login")
	public String checkLogin(HttpServletRequest request,
			HttpServletResponse response) {

		return "/admin/index";
	}

	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request, HttpServletResponse response) {

		return "/mywhere/index";
	}

	@RequestMapping(value = "/sitemap")
	public String createSiteMap(HttpServletRequest request,
			HttpServletResponse response) {

		String style = request.getParameter("style");
		if (style != null && "".equals(style) == false) {
			if (style.equals("baidu")) {

				// 生成百度sitemap

				// 查询所有的分类
				List<Map<String, Object>> typeList = tdao.getFatherTypeInfo();

				// 所有标签
				List<Map<String, Object>> tagList = tagdao.getAlltagInfo();

				// 所有日志
				List<Map<String, Object>> articleList = adao.getAllAricle();

				// 1. 创建document文档对象
				Document doc = DocumentHelper.createDocument();

				// 2. 添加元素
				Element root = doc.addElement("urlset").addAttribute("xmlns",
						"http://www.sitemaps.org/schemas/sitemap/0.9");
				root.attribute("");
				Element map = root.addElement("url");
				map.addElement("loc").setText(
						"http://www.shack2.org/baiduSiteMap.xml");
				map.addElement("changefreq").setText("daily");
				map.addElement("priority").setText("1.0");

				// domain

				// 首页
				String domain = "http://www.shack2.org/";

				// 分类页
				for (Map<String, Object> type : typeList) {
					Element u = root.addElement("url");
					u.addElement("loc").setText(
							domain + "type/index.htm?id=" + type.get("id"));
					u.addElement("changefreq").setText("monthly");
					u.addElement("priority").setText("0.7");
				}

				// 标签页
				for (Map<String, Object> tag : tagList) {
					Element u = root.addElement("url");
					u.addElement("loc").setText(
							domain + "search/index.htm?key="
									+ tag.get("tagName"));
					u.addElement("changefreq").setText("weekly");
					u.addElement("priority").setText("0.6");
				}
				// 日志页面
				for (Map<String, Object> article : articleList) {
					Element u = root.addElement("url");
					u.addElement("loc").setText(
							domain + "article/" + article.get("id") + ".html");
					u.addElement("changefreq").setText("never");
					u.addElement("priority").setText("0.9");
				}

				// 3. 设置格式
				OutputFormat format = OutputFormat.createCompactFormat();
				format.setIndentSize(4);
				format.setNewlines(true);

				try {
					// 4. 保存xml文件
					XMLWriter out = new XMLWriter(new FileOutputStream(
							request.getRealPath("/") + "baiduSiteMap.xml"),
							format);

					out.write(doc);

					response.getWriter().print("ok");

				} catch (Exception e) {
					try {
						response.getWriter().print("failed");
					} catch (IOException e1) {

					}
				}

			}
		}
		return "/error/index";
	}

}
