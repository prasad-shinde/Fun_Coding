package zillow.tree;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringUtilTest {

	@Test
	public void testLong() {
		assertEquals("Long value ", 2245579, StringUtil.stringToLong("2245579"));
		assertEquals("Long value ", 224, StringUtil.stringToLong("224"));
		assertNotEquals("its long not a string", "2245579", StringUtil.stringToLong("2245579"));
	}
	
	@Test(expected=NumberFormatException.class)
	public void testNumberFormatException() {
		StringUtil.stringToLong("abcd");
	}
}
