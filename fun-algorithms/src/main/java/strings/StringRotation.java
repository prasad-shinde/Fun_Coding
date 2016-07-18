package strings;

public class StringRotation {
	public boolean isRotated(String a,String b) {
		return (a+a).contains(b);
	}
}
