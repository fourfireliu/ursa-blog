import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fourfire.blog.manager.ArticleInfoManager;
import com.fourfire.blog.manager.TypeInfoManager;
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
	public void testQueryArticle() {
		long id = 1L;
		System.out.println("============");
		System.out.println(articleInfoManager.getArticleInfoById(id));
		System.out.println("=================");
	}
	
	@Test
	public void testGetUpOrDown() {
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
