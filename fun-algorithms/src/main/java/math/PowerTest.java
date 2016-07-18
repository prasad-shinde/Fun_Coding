package math;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PowerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testNaivePower() {
		assertEquals("Power 2^3",8,Power.naivePower(2, 3));
	}
	
	@Test
	public void testIndianPower() {
		assertEquals("Power 2^3",8,Power.indianPower(2, 3));
		assertEquals("Power 2^2",4,Power.indianPower(2, 2));
		assertEquals("Power 2^7",128,Power.indianPower(2, 7));
	}
	

}
