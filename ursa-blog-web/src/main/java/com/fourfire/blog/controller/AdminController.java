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
import org.springframework.web.bind.annotation.RequestMethod;

import com.fourfire.blog.dao.ArticleInfoDao;
import com.fourfire.blog.dao.TagInfoDao;
import com.fourfire.blog.dao.TypeInfoDao;
import com.fourfire.blog.manager.ArticleInfoManager;
import com.fourfire.blog.manager.TypeInfoManager;
import com.fourfire.blog.model.PageModel;
import com.fourfire.blog.util.Constants;
import com.fourfire.blog.util.Tools;
import com.fourfire.blog.vo.ArticleInfoVO;
import com.fourfire.blog.vo.TypeInfoVO;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@Resource(name = "articleDaoImpl")
	private ArticleInfoDao adao;

	@Resource(name = "typeInfoDaoImpl")
	private TypeInfoDao tdao;

	@Resource(name = "tagInfoDaoImpl")
	private TagInfoDao tagdao;
	@Resource
	private ArticleInfoManager articleInfoManager;
	@Resource
	private TypeInfoManager typeInfoManager;

	/**
	 * 提交发文或者编辑文章
	 */
	@RequestMapping(value = "/article/submit", method = RequestMethod.POST)
	public String addAritcle(HttpServletRequest request,
			HttpServletResponse response) {
		ArticleInfoVO articleInfoVO = new ArticleInfoVO();
		
		String op = ServletRequestUtils.getStringParameter(request, "op", "");
		//修改文章
		if ("update".equalsIgnoreCase(op)) {
			articleInfoVO.setExist(true);
			articleInfoVO.setId(ServletRequestUtils.getLongParameter(request, "id", 0L));
			articleInfoVO.setReadCount(0);
		} else {
			//新文章 生成ID
			articleInfoVO.setId(Tools.getId());
		}
		//文章类型
		articleInfoVO.setType(ServletRequestUtils.getIntParameter(request, "typeId", 0));
		//文章标题
		articleInfoVO.setTitle(Tools.checkTitle(request.getParameter("title")));
		//文章内容
		articleInfoVO.setContent(ServletRequestUtils.getStringParameter(request,
				"content", "").replace("'", "&#39;"));
		//文章作者
		articleInfoVO.setAuthor(Constants.BLOG_HOST);
		//访问者IP
		articleInfoVO.setIp(request.getRemoteAddr());
		
		boolean isSuccess = articleInfoManager.addOrUpdateArticle(articleInfoVO);
		if (isSuccess) {
			if (articleInfoVO.isExist()) {
				request.setAttribute("message", "更新成功...");
			} else {
				request.setAttribute("message", "添加成功...");
			}
		} else {
			if (articleInfoVO.isExist()) {
				request.setAttribute("message", "更新失败...");
			} else {
				request.setAttribute("message", "添加失败...");
			}
		}
		
		return "/error/index";
	}

	/**
	 * 新建文章或编辑文章界面
	 */
	@RequestMapping(value = "/article/edit")
	public String getArticle(HttpServletRequest request,
			HttpServletResponse response) {
		String op = ServletRequestUtils.getStringParameter(request, "op", "");
		List<TypeInfoVO> typeList = typeInfoManager.getAllTypeInfos();
		request.setAttribute("typeList", typeList);
		if ("add".equalsIgnoreCase(op)) {
			return "/admin/article/article";
		}

		long id = ServletRequestUtils.getLongParameter(request, "id", 0L);
		ArticleInfoVO articleInfoVO = articleInfoManager.getArticleInfoById(id);
		request.setAttribute("article", articleInfoVO);

		return "/admin/article/article";
	}

	/**
	 * 获取文章列表(简单信息无需内容)
	 */
	@RequestMapping(value = "/article/articleList")
	public String getArticleList(HttpServletRequest request,
			HttpServletResponse response) {
		int pageNo = ServletRequestUtils.getIntParameter(request, "pageNo", Constants.DEFAULT_PAGE_NUM);
		int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize", Constants.DEFAULT_PAGE_SIZE);
		
		
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
