package au.usyd.nexus.service;
import static org.mockito.Mockito.*; 

import org.junit.Test;
import org.mockito.Mockito;

import junit.framework.TestCase;
import au.usyd.nexus.dao.LoginDAO;
import au.usyd.nexus.domain.User;
 
public class UserAuthServiceTest extends TestCase{
	
	//Autowired in main program
	LoginDAO loginDaoMock = Mockito.mock(LoginDAO.class);
	
	//Autowired in main program
	UserAuthService uas = new UserAuthService();
	
	User user1;

	protected void setUp() throws Exception {
		uas.setLoginDAO(loginDaoMock);
		
		user1 = new User("username", "test@test.com", "password");
		
		when(loginDaoMock.checkLogin("test@test.com", "password")).thenReturn(true);
		when(loginDaoMock.checkLogin("invalidEmail@email.com", "password")).thenReturn(false);
		when(loginDaoMock.checkLogin("test@test.com", "invalidPassword")).thenReturn(false);
		
		when(loginDaoMock.findByEmail("test@test.com")).thenReturn(user1);
		when(loginDaoMock.findByEmail("invalidEmail@email.com")).thenReturn(null);
		
		when(loginDaoMock.findById(123)).thenReturn(user1);
		when(loginDaoMock.findById(345)).thenReturn(null);
	}
	
	
	@Test(expected = NullPointerException.class)
	public void testSetNullLoginDAO() {
		uas.checkLogin("test@test.com", "password");
	}
	
	public void testSetLoginDAO() {
		assertTrue(uas.checkLogin("test@test.com", "password"));
	}
	
	public void testCheckLogin() {
		assertTrue(uas.checkLogin("test@test.com", "password"));
		verify(loginDaoMock).checkLogin("test@test.com", "password");
		
		assertFalse(uas.checkLogin("invalidEmail@email.com", "password"));
		verify(loginDaoMock).checkLogin("invalidEmail@email.com", "password");
		
		assertFalse(uas.checkLogin("test@test.com", "invalidPassword"));
		verify(loginDaoMock).checkLogin("test@test.com", "invalidPassword");
	}
	
	public void testFindByEmail() {
		assertEquals(user1, uas.findByEmail("test@test.com"));
		verify(loginDaoMock).findByEmail("test@test.com");
		
		assertNull(uas.findByEmail("invalidEmail@email.com"));
		verify(loginDaoMock).findByEmail("invalidEmail@email.com");
	}
	
	public void testFindById() {
		assertEquals(user1, uas.findById(123));
		verify(loginDaoMock).findById(123);
		
		assertNull(uas.findById(345));
		verify(loginDaoMock).findById(345);
	}
	
	
	
}
