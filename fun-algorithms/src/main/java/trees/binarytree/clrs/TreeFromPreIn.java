package trees.binarytree.clrs;

public class TreeFromPreIn {
	static int preStart;
	
	public static Node buildTree(char[] pre,char[] in) throws Exception {
		preStart = 0;
		return buildTreeUtil(pre, in, 0, in.length-1);
	}
	
	private static Node buildTreeUtil(char[] pre,char[] in,int inStart,int inEnd) throws Exception {
		if(inStart>inEnd)
			return null;
		
		Node n = new Node(pre[preStart++],null);
		
		if(inStart == inEnd)
			return n;
		
		int inIndex = inorderIndex(in, pre[preStart]);
		if(inIndex == -1)
			throw new Exception("Invalid in/pre order");
		
		n.insertLeft(buildTreeUtil(pre, in, inStart, inIndex-1));
		n.insertRight(buildTreeUtil(pre, in, inIndex+1, inEnd));
		return n;
	}
	
	private static int inorderIndex(char[] in,char c) {
		for(int i=0;i<in.length;i++) 
			if(in[i] == c)
				return i;
		return -1;
	}
	
	public static void main(String[] args) {
		try {
			char[] pre = {'A', 'B', 'D', 'E', 'C', 'F'};
			char[] in = {'D', 'B', 'E', 'A', 'F', 'C'};
			buildTree(pre, in);
			System.out.println("Success!");
		} catch(Exception e) {
			System.out.println("try again!");
		}
	}
}
