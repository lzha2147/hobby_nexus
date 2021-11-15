package au.usyd.nexus.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mockito;

import au.usyd.nexus.dao.ArticleDAO;
import au.usyd.nexus.domain.Article;
import junit.framework.TestCase;

public class ArticleServiceTest extends TestCase{
	ArticleService  articleService = new  ArticleService();
	 
	ArticleDAO articleDaoMock = Mockito.mock(ArticleDAO.class);
	Article article; 
	
	protected void setUp() throws Exception {
		
		articleService.setArticleDAO(articleDaoMock);
		article = new Article();
		article.setArtice_id(123);
		when(articleService.getArticleById(123)).thenReturn(article);
	}
	
	
	
	
	@Test
	public void testGetArticleById() {	
		assertEquals(article, articleService.getArticleById(123));
		verify(articleDaoMock).getArticleById(123);
		
	}
	
	
}
