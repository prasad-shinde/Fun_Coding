package quora.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import quora.heap.Heap;

public class HeapTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		Heap h = new Heap();
		List<String> uids = new ArrayList<String>();
		h.add("user",1.0, "u1");
		h.add("user",1.0, "u2");
		h.add("topic",0.8, "t1");
		h.add("question",0.5, "q1");
		h.add("question",0.5, "q2");
		
		
		assertEquals("Heap size", 5, h.size());
		uids.add("u2");
		assertEquals("user ids", uids, h.topIds(1));
		uids.add("u1");
		assertEquals("user ids", uids, h.topIds(2));
		uids.add("t1");
		assertEquals("user ids", uids, h.topIds(3));
		uids.add("q2");
		assertEquals("user ids", uids, h.topIds(4));
		uids.add("q1");
		assertEquals("user ids", uids, h.topIds(5));
	}

}
