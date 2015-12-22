import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fourfire.blog.constant.BlogConstant;
import com.fourfire.blog.manager.ArticleInfoManager;
import com.fourfire.blog.manager.TypeInfoManager;
import com.fourfire.blog.result.BaseResult;
import com.fourfire.blog.util.Tools;
import com.fourfire.blog.vo.ArticleInfoVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:application-manager.xml", 
		"classpath:application-persistence.xml"})
public class SqlTest {
	@Autowired
	private ArticleInfoManager articleInfoManager;
	@Autowired
	private TypeInfoManager typeInfoManager;
	
	@Test
	public void testQueryArticle() throws UnsupportedEncodingException {
		long id = 1L;
		System.out.println("============");
		System.out.println(articleInfoManager.getArticleInfoById(id));
		System.out.println("=================");
	}
	
	@Test
	public void testAddBlog() {
		ArticleInfoVO articleInfoVO = new ArticleInfoVO();
		articleInfoVO.setAuthor(BlogConstant.DEFAULT_AUTHOR);
		String content = "<p><if test=\"userIds != null and userIds.length > 0\"></p>";
		articleInfoVO.setContent(content);
		articleInfoVO.setCreateDate(new Date());
		articleInfoVO.setIp("127.0.0.1");
		articleInfoVO.setModifyDate(new Date());
		articleInfoVO.setTitle("ABCD4");
		articleInfoVO.setType(5);
		
		BaseResult<ArticleInfoVO> result = articleInfoManager.addOrUpdateArticle(articleInfoVO);
		System.out.println(result);
	}
	
	@Test
	public void testGetUpOrDown() throws UnsupportedEncodingException {
		long id = 4L;
		System.out.println("=========");
		System.out.println(articleInfoManager.getUpOrDownArticleInfo(id, 5, true));
		System.out.println("=============");
	}
	
	@Test
	public void testAddOrUpdateArticle() {
		ArticleInfoVO articleInfoVO = new ArticleInfoVO();
		articleInfoVO.setAuthor("liuyi");
		articleInfoVO.setCommentCount(0);
		articleInfoVO.setContent("Today is Sunday");
		articleInfoVO.setIp("192.168.1.1");
		articleInfoVO.setReadCount(0);
		articleInfoVO.setTitle("Hello world");
		articleInfoVO.setType(2);
		articleInfoVO.setId(2L);
		articleInfoVO.setCreateDate(new Date());
		
		System.out.println("===============");
		System.out.println(articleInfoManager.addOrUpdateArticle(articleInfoVO));
		System.out.println("==================");
	}
	
	@Test
	public void testAddArticleCountInType() {
		System.out.println("=======");
		System.out.println(typeInfoManager.addArticleCountInType(1, 1));
		System.out.println("============");
	}
}
