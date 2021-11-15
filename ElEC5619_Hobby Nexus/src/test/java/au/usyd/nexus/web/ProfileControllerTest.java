package au.usyd.nexus.web;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import au.usyd.nexus.domain.User;
import au.usyd.nexus.service.UserAuthService;
import au.usyd.nexus.service.UserManager;
import au.usyd.nexus.service.UserValidator;
import junit.framework.TestCase;

public class ProfileControllerTest extends TestCase{
	
protected MockHttpSession session =new MockHttpSession();
	
	final Model model = new ExtendedModelMap();
	
	ProfileController pc = new ProfileController();
	
	UserValidator validatorMock =  Mockito.mock(UserValidator.class);
	UserAuthService uas = Mockito.mock(UserAuthService.class);
	UserManager um = Mockito.mock(UserManager.class);
	
	BindingResult validResult = Mockito.mock(BindingResult.class);
	BindingResult invalidResult = Mockito.mock(BindingResult.class);
	
	
	User invalidUser1 = new User("Doe","test2@test.email", "password");
	User validUser1 = new User("John","test@test.email", "password");
	
	protected void setUp() throws Exception {
		validUser1.setUser_id(1);
		pc.setUserAuthService(uas);
		pc.setUserValidator(validatorMock);
		pc.setUserManager(um);
		when(uas.findById(1)).thenReturn(validUser1);
		when(uas.checkLogin(validUser1.getEmail(), validUser1.getPassword())).thenReturn(true);
		when(uas.checkLogin(invalidUser1.getEmail(), invalidUser1.getPassword())).thenReturn(false);
		when(uas.findByEmail("test@test.email")).thenReturn(validUser1);
		when(validResult.hasErrors()).thenReturn(false);
		when(invalidResult.hasErrors()).thenReturn(true);
	}
	
	public void testViewValidProfile() {
		assertThat(pc.viewProfile(1, model, session), is("/profile"));
		assertThat(model.asMap().get("selected_user"), is(User.class));
	}
	
	public void testViewInvalidProfile() {
		assertThat(pc.viewProfile(2, model, session), is("redirect:/home"));
		assertNull(model.asMap().get("selected_user"));
	}
	
	public void testProfile() {
		assertNull(session.getAttribute("user"));
		assertNull(model.asMap().get("detailsForm"));
		assertThat(pc.profile(model, session), is("redirect:/register"));
		session.setAttribute("user", validUser1);
		assertThat(session.getAttribute("user"), is(User.class));
		assertThat(pc.profile(model, session), is("editProfile"));
		assertThat(model.asMap().get("detailsForm"), is(User.class));
	}
	
	public void testEditValidUserDetails() {
		assertNull(session.getAttribute("user"));
		assertThat(pc.editUserDetails(validUser1, validResult, invalidUser1, 1, model, session),is("redirect:/editProfile"));
		verify(validatorMock, times(1)).validate(validUser1, validResult);
		verify(validResult).hasErrors();
		verify(um, times(1)).updateUserDetails(validUser1);
		assertThat(session.getAttribute("user"), is(User.class));
	}
	
	public void testEditInvalidUserDetails() {
		assertNull(session.getAttribute("user"));
		assertThat(pc.editUserDetails(invalidUser1, invalidResult, validUser1, 2, model, session),is("editProfile"));
		verify(validatorMock, times(1)).validate(invalidUser1, invalidResult);
		verify(invalidResult).hasErrors();
		assertNull(session.getAttribute("user"));
	}
	
	
}
