package strings;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RabinKarpTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		assertEquals("should be same",2,RabinKarp.isSubstring("peekayhhhh", "ek"));
		//assertEquals("should be same",7,RabinKarp.isSubstring("state university", "nive"));
		assertEquals("should be same",2,RabinKarp.isSubstring("san diego", "n d"));
	}

}
