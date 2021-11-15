package au.usyd.nexus.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import au.usyd.nexus.domain.Hobby;
import au.usyd.nexus.domain.User;
import au.usyd.nexus.dao.SearchDetDAO;
import junit.framework.TestCase;

public class SearchServiceTest extends TestCase{
	SearchService ss = new SearchService();
	
	SearchDetDAO sd = Mockito.mock(SearchDetDAO.class);
	String searchText = "John";
	User validUser = new User("John","test@test.email", "password");
	List<User> validUserList = new ArrayList<User>();
	Hobby validHobby = new Hobby();
	List<Hobby> validHobbyList = new ArrayList<Hobby>();
	
	protected void setUp() throws Exception {
		validUserList.add(validUser);
		validHobby.setHobby_name(searchText);
		validHobbyList.add(validHobby);
		ss.setSearchDetDAO(sd);
		when(sd.search(searchText)).thenReturn(validUserList);
		when(sd.searchHobby(searchText)).thenReturn(validHobbyList);
	}
	
	@Test
	public void testSearch() throws Exception {		 	 
		 assertThat(ss.search(searchText), is(validUserList));
		 verify(sd).search(searchText);
	}
	
	@Test
	public void testSearchHobby() throws Exception {		 	 
		 assertThat(ss.searchHobby(searchText), is(validHobbyList));
		 verify(sd).searchHobby(searchText);
	}
	
}
