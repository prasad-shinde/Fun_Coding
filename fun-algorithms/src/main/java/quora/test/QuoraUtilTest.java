package quora.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import quora.util.QuoraUtil;

public class QuoraUtilTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		List<String> first = new ArrayList<String>();
		List<String> second = new ArrayList<String>();
		List<String> third = new ArrayList<String>();
		List<List<String>> list = new ArrayList<List<String>>();
		List<String> result = new ArrayList<String>();
		
		result.add("abcd");
		result.add("why?");
		
		first.add("hello");
		first.add("abcd");
		first.add("asd/@#$");
		first.add("why?");
		
		second.add("helloasdf");
		second.add("abcd");
		second.add("helloerf");
		second.add("why?");
		second.add("hellosdf");
		
		third.add("ewfg");
		third.add("abcd");
		third.add("jdurf");
		third.add("why?");
		third.add("sdfgr");
		
		list.add(first);
		list.add(second);
		list.add(third);
		
		assertEquals("common from 2 lists",result, QuoraUtil.common(first,second));
		assertEquals("common of n lists",result, QuoraUtil.common(list));
	}
}
