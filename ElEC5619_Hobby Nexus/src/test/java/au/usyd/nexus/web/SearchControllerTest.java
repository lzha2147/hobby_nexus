package au.usyd.nexus.web;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import org.junit.Test;
import org.mockito.Mockito;

import au.usyd.nexus.domain.User;
import au.usyd.nexus.domain.Hobby;

import au.usyd.nexus.service.SearchService;
import junit.framework.TestCase;

public class SearchControllerTest extends TestCase {

	SearchController sc = new SearchController();
	
	SearchService ss = Mockito.mock(SearchService.class);
	String searchText = "John";
	User validUser = new User("John","test@test.email", "password");
	List<User> validUserList = new ArrayList<User>();
	Hobby validHobby = new Hobby();
	List<Hobby> validHobbyList = new ArrayList<Hobby>();
	protected void setUp() throws Exception {
		validUserList.add(validUser);
		validHobby.setHobby_name(searchText);
		validHobbyList.add(validHobby);
		sc.setSearchService(ss);
		when(ss.search(searchText)).thenReturn(validUserList);
		when(ss.searchHobby(searchText)).thenReturn(validHobbyList);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testSearch() throws Exception {
		 Map<String,Object> mapModel = sc.search(searchText).getModel();
		 
		 assertThat((List<User>)mapModel.get("userList"), is(validUserList));
		 assertThat((List<Hobby>)mapModel.get("hobbyList"), is(validHobbyList));
		 assertThat((String)mapModel.get("searchedText"), is(searchText));
	}
	
}
