package strings;

public class StringMath {
	public static String add(String a,String b) {
        StringBuffer sb = new StringBuffer();
        int carry = 0;
        int i =0;
        for(;i<a.length() && i<b.length();i++) {
            int num = getInt(a.charAt(i)) + getInt(b.charAt(i)) + carry;
            if(num > 9) {
                carry = num - 9;
                num = num - 10;
            }
            sb.append(num);
        }
        
        for(;i<a.length();i++) {
            int num = getInt(a.charAt(i)) + carry;
            if(num > 9) {
                carry = num - 9;
                num = num - 10;
            }
            sb.append(num);
        }
        
        for(;i<b.length();i++) {
            int num = getInt(b.charAt(i)) + carry;
            if(num > 9) {
                carry = num - 9;
                num = num - 10;
            }
            sb.append(num);
        }
        if(carry > 0)
            sb.append(carry);
        return sb.toString();
    }
	
	public static int getInt(char c) {
        return Integer.parseInt(c+"");
    }
}
