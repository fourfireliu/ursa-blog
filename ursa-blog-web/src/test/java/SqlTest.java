import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fourfire.blog.manager.ArticleInfoManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:application-manager.xml", 
		"classpath:application-persistence.xml"})
public class SqlTest {
	@Autowired
	private ArticleInfoManager articleInfoManager;
	
	@Test
	public void testQueryArticle() {
		long id = 1L;
		System.out.println("============");
		System.out.println(articleInfoManager.getArticleInfoById(id));
		System.out.println("=================");
	}
}
