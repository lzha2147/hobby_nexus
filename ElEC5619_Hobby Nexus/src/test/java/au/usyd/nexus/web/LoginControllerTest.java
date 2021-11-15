package au.usyd.nexus.web;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.Test;
import org.mockito.Mockito;

import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import au.usyd.nexus.domain.User;
import au.usyd.nexus.service.UserAuthService;
import au.usyd.nexus.service.UserRegistrationService;
import au.usyd.nexus.service.UserValidator;
import junit.framework.TestCase;

public class LoginControllerTest extends TestCase{
	
	protected MockHttpSession session =new MockHttpSession();
	
	final Model model = new ExtendedModelMap();
	
	LoginController lc = new LoginController();
	
	UserValidator validatorMock =  Mockito.mock(UserValidator.class);
	UserAuthService uas = Mockito.mock(UserAuthService.class);
	UserRegistrationService urs = Mockito.mock(UserRegistrationService.class);
	
	BindingResult validResult = Mockito.mock(BindingResult.class);
	BindingResult invalidResult = Mockito.mock(BindingResult.class);
	
	User temp = new User();
	String hashedPassword =  temp.hashPassword("password");
	User invalidUser1 = new User("Doe","test2@test.email", "password");
	User validUser1 = new User("John","test@test.email", "password");
	
	protected void setUp() throws Exception {
		lc.setUserAuthService(uas);
		lc.setUserValidator(validatorMock);
		lc.setUserRegistraionService(urs);
		when(uas.checkLogin(validUser1.getEmail(), hashedPassword)).thenReturn(true);
		when(uas.checkLogin(invalidUser1.getEmail(),hashedPassword)).thenReturn(false);
		when(uas.findByEmail("test@test.email")).thenReturn(validUser1);
		when(validResult.hasErrors()).thenReturn(false);
		when(invalidResult.hasErrors()).thenReturn(true);
	}
	
	@Test
	public void testRegister() throws Exception {
		 assertThat(lc.register(model), is("register"));
		 assertThat(model.asMap().get("userForm"), is(User.class));
		 assertThat(model.asMap().get("loginForm"), is(User.class));
	}
	
	@Test
	public void testValidateValidUser() throws Exception {
		 assertThat(lc.validate(validUser1, validResult, invalidUser1, model, session), is("redirect:home"));
		 verify(uas).checkLogin("test@test.email", hashedPassword);
		 verify(uas).findByEmail("test@test.email");
		 assertNotNull(session.getAttribute("user"));
	}
	
	@Test
	public void testValidateInvalidUser() throws Exception {
		 assertThat(lc.validate(invalidUser1, invalidResult, validUser1, model, session), is("register"));
		 verify(uas).checkLogin("test2@test.email",hashedPassword);
		 assertNull(session.getAttribute("user"));
	}
	
	@Test
	public void testAddValidUser() {
		assertThat(lc.addUser(validUser1, validResult, invalidUser1, model, session), is("redirect:home"));
		verify(validResult).hasErrors();
		verify(uas).findByEmail("test@test.email");
		verify(urs, times(1)).addUser(validUser1);
		assertNotNull(session.getAttribute("user"));
	}
	
	@Test
	public void testAddInvalidUser() {
		assertThat(lc.addUser(invalidUser1, invalidResult, invalidUser1, model, session), is("register"));
		verify(invalidResult).hasErrors();
		assertNull(session.getAttribute("user"));
	}
	
	public void testLogout() {
		assertThat(lc.validate(validUser1, validResult, invalidUser1, model, session), is("redirect:home"));
		assertNotNull(session.getAttribute("user"));
		assertThat(lc.logout(session), is("redirect:home"));
		assertNull(session.getAttribute("user"));
	}

}
