package trees.binarytree;


 
/**
 * 298. Binary Tree Longest Consecutive Sequence
 * Given a binary tree, find the length of the longest consecutive sequence path.
 * The path refers to any sequence of nodes from some starting node to any node in 
 * the tree along the parent-child connections. The longest consecutive path need to 
 * be from parent to child (cannot be the reverse).
 * 
 * For example,
   1
    \
     3
    / \
   2   4
        \
         5
	Longest consecutive sequence path is 3-4-5, so return 3.
 * 
 * Reference : 
 * https://leetcode.com/problems/binary-tree-longest-consecutive-sequence/
 * 
 * @author prasshinde
 *
 */
public class LongestConsecutiveSequence {
    Integer max = 1;
    public int longestConsecutive(TreeNode root) {
        if(root == null)
            return 0;
        longestConsecutive(root,Integer.MIN_VALUE,1);
        return max;
    }
    
    private void longestConsecutive(TreeNode node,int prevValue,int currentMax) {
        if(node == null)
            return;
        if(node.val == (1+prevValue)) {
            currentMax++;
        } else {
            currentMax = 1;
        }
        max = Math.max(currentMax,max);
        longestConsecutive(node.left,node.val,currentMax);
        longestConsecutive(node.right,node.val,currentMax);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}