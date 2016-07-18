package trees.suffix;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SuffixTrieTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		SuffixTrie st = new SuffixTrie();
		st.construct("abaaba");
		assertEquals("",true,st.hasSubstring("aba"));
		assertEquals("",false,st.hasSubstring("abad"));
		assertEquals("",true,st.hasSuffix("abaaba"));
		assertEquals("",false,st.hasSuffix("abaab"));
	}
	
	

}
