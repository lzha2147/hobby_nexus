package au.usyd.nexus.service;
import static org.mockito.Mockito.*; 

import org.junit.Test;
import org.mockito.Mockito;

import au.usyd.nexus.dao.CommentDAO;
import au.usyd.nexus.domain.Comment;
import junit.framework.TestCase;


public class CommentServiceTest extends TestCase{
	 CommentService  commentService = new  CommentService();
	 
	 CommentDAO commentDaoMock = Mockito.mock(CommentDAO.class);
	 Comment comment; 

	 
		protected void setUp() throws Exception {
			
			commentService.setCommentDAO(commentDaoMock);
			comment = new Comment();
			comment.setComment_id(123);
			comment.setArtice_id(123);
			when(commentService.getCommentById(123)).thenReturn(comment);
		}
		
		@Test
		public void testAddComment() {
			commentService.addComment(comment);
			verify(commentDaoMock).addComment(comment);
		}
		
		@Test
		public void testGetCommentById() {	
			assertEquals(comment, commentService.getCommentById(123));
			verify(commentDaoMock).getCommentById(123);
			
		}
		
		
}
