package au.usyd.nexus.web;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import junit.framework.TestCase;

public class IndexControllerTest extends TestCase{
	
	protected MockHttpSession session =new MockHttpSession();
	
	final Model model = new ExtendedModelMap();
	@Autowired
	IndexController il = new IndexController();
	
	@Test
	public void testHome() throws Exception {
		 assertThat(il.home(model, session), is("home"));
	}

}
