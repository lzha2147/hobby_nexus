package au.usyd.nexus.service;
import static org.mockito.Mockito.*; 

import org.mockito.Mockito;

import junit.framework.TestCase;
import au.usyd.nexus.dao.LoginDAO;
import au.usyd.nexus.domain.User;

public class UserRegistrationServiceTest extends TestCase{
	
	//Autowired in main program
	LoginDAO loginDaoMock = Mockito.mock(LoginDAO.class);
	
	//Autowired in main program
	UserRegistrationService urs = new UserRegistrationService();
	
	User user1;

	protected void setUp() throws Exception {
		urs.setLoginDAO(loginDaoMock);
		user1 = new User("username", "test@test.com", "password");
	}
	
	public void testAddUser() {
		urs.addUser(user1);
		verify(loginDaoMock).addUser(user1);
	}
	
}
