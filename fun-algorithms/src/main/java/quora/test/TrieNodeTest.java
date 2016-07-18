package quora.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import quora.trie.TrieNode;

public class TrieNodeTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		// ADD user u2 1.0 Adam Black
		List<String> uids = new ArrayList<String>();
		TrieNode node = new TrieNode('A',"user",1.0,"u1");
		node.add("user",1.0, "u2");
		node.add("topic",0.8, "t1");
		node.add("question",0.5, "q1");
		node.add("question",0.5, "q2");
		
		uids.add("u2");
		uids.add("u1");
		assertEquals("user ids", uids, node.topIds(2));
	}

}
