package quora.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import quora.heap.Node;

public class NodeTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		Node n1 = new Node("user",1.0,"u1");
		Node n2 = new Node("user",1.0,"u2");
		
		assertEquals("user ids", "u1", n1.toString());
		assertEquals("user ids", "u2", n2.toString());
		
		assertEquals("n1 == n2", true, n1.equals(n2));
		assertEquals("n1 <= n2", true, n1.lessThanOrEquals(n2));
		assertEquals("n1 < n2", false, n1.lessThan(n2));
	}
}
