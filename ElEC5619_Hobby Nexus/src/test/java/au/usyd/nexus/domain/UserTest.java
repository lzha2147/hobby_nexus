package au.usyd.nexus.domain;

import junit.framework.TestCase;

public class UserTest extends TestCase {

    private User user;

    protected void setUp() throws Exception {
        user = new User("test", "test@test.com", "passwordpassword");
    }

    public void testSetandGetUsername() {
    	assertEquals("test", user.getUser_name());
        String testUsername = "diffUsername";
        user.setUser_name(testUsername);
        assertEquals(testUsername, user.getUser_name());
    }

    public void testSetandGetEmail() {
    	assertEquals("test@test.com", user.getEmail());
        String testEmail = "diffEmail@email.com";
        user.setEmail(testEmail);
        assertEquals(testEmail, user.getEmail());
    }
    
    public void testSetandGetPassword() {
    	assertEquals("passwordpassword", user.getPassword());
        String testPass = "differentpassword";
        user.setPassword(testPass);
        assertEquals(testPass, user.getPassword());
    }
    
    public void testSetandGetLocation() {
    	assertNull(user.getLocation());
        String location = "123 John Street NSW 2750";
        user.setLocation(location);
        assertEquals(location, user.getLocation());
    }
  
}
