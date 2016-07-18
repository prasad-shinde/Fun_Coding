package quora.util;

public class AgeGenerator {
	private long age;
	private static AgeGenerator instance = null;
	
	private AgeGenerator() {
		age = 0;
	}
	
	public static AgeGenerator getInstance() {
		if(instance == null) {
			instance = new AgeGenerator();
		}
		
		return instance;
	}
	
	public long age() {
		++age;
		return age;
	}
	
	public void reset() {
		age = 0;
	}
}
