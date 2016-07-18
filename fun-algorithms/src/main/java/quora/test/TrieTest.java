package quora.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import quora.trie.Trie;

public class TrieTest {
	Trie t = new Trie();
	
	@Before
	public void setUp() throws Exception {
		t.add("Adam D’Angelo","user", 1.0,"u1");
		t.add("Adam Black","user", 1.0, "u2");
		t.add("Adam D’Angelo dips","topic", 0.8, "t1");
		t.add("What does Adam D’Angelo do at Quora?","question", 0.5, "q1");
		t.add("How did Adam D’Angelo learn programming?","question", 0.5, "q2");
	}

	@Test
	public void testOrder() {
		List<String> list = new ArrayList<String>();
		list.add("u2");
		assertEquals("user ids", list, t.query(1, "adam"));
		list.add("u1");
		assertEquals("user ids", list, t.query(2, "adam"));
		list.add("t1");
		list.add("q2");
		list.add("q1");
		assertEquals("user ids", list, t.query(5, "adam"));
		
		list.clear();
		list.add("q2");
		assertEquals("user ids", list, t.query(1, "LEARN"));
	}

	@Test
	public void testGeneral() {
		List<String> list = new ArrayList<String>();
		list.add("q2");
		assertEquals("user ids", list, t.query(1, "LEARN"));
	}
	
	@Test
	public void testPartialText() {
		List<String> list = new ArrayList<String>();
		list.add("t1");
		list.add("q2");
		assertEquals("user ids", list, t.query(2, "di"));
	}
	
	@Test
	public void testCaseInsensitivity() {
		List<String> list = new ArrayList<String>();
		list.add("q2");
		assertEquals("user ids", list, t.query(1, "LEA"));
	}
	
	@Test
	public void testDelete() {
		List<String> list = new ArrayList<String>();
		t.delete("u2");
		list.add("u1");
		list.add("t1");
		
		assertEquals("user ids", list, t.query(2, "adam"));
	}
	
	@Test
	public void testWquery() {
		// WQUERY 2 1 topic:9.99 Adam D’A
		List<String> list = new ArrayList<String>();
		list.add("t1");
		list.add("u1");
		
		Map<String, Double> typeBoosts = new HashMap<String,Double>();
		typeBoosts.put("topic", 9.99);
		
		assertEquals("user ids", list, t.wquery(2, 1, typeBoosts, null, "Adam D’A"));
	}
	
	@Test
	public void testWquery2() {
		// WQUERY 10 0 Adam D’A
		List<String> list = new ArrayList<String>();
		list.add("u1");
		list.add("t1");
		list.add("q2");
		list.add("q1");
		
		assertEquals("user ids", list, t.wquery(10, 0, null, null, "Adam D’A"));
	}
}
