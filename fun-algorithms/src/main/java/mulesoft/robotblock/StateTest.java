package mulesoft.robotblock;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StateTest {
	private State s;
	@Before
	public void setUp() throws Exception {
		s = new State(8);
		s.move(7, 1);
		s.move(5, 1);
		s.move(1, 6);
		s.move(4, 3);
		s.move(1, 4);
		s.move(3, 1);
		s.move(5, 2);
		s.move(7, 5);
		s.move(4, 5);
	}

	@Test
	public void test() {
		System.out.print(s.toString());
	}

}
