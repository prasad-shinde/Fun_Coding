package math;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GCDTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		assertEquals("",2,GCD.gcd(10, 24));
		assertEquals("",2,GCD.gcd1(10, 24));
	}

}
