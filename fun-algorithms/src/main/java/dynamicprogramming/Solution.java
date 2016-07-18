package dynamicprogramming;

/*import java.util.ArrayList;


/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */
/*
class Solution {
  
  public static int paths(int size) {
    int[][] dp = new int[size][size];
    int i,j;
    
    dp[size-1][size-1] = 1;
    for(i = 0;i<size-1;i++) {
      dp[size-1][i] = 1;
      dp[i][size-1] = 1;
    }
    
    for(i = size-2;i>=0;i--) {
      for(j = size-2;j>=0;j--) {
        dp[i][j] = dp[i+1][j] + dp[i][j+1] + dp[i+1][j+1];
      }
    }
    
    for(i = 0;i<size;i++) {
      System.out.println();
      for(j = 0;j<size;j++) {
        System.out.print(dp[i][j] + " ");
      }
    }
    
    return dp[0][0];
  }
  
  public static void maxOnes(int[] arr) {
    int i=0;
    while(arr[i] == 1)
      i++;
    
    int max_range = 0;
    int max_l,max_r=-1;
    int current_l;
    int max = -1;
    
    max_l = i;
    current_l = i;
    max_r = i;
    
    
    while(i<arr.length) {
      if(arr[i] == 0)
        max_range++;
      else {
        max_range--;
      }
      if(max_range > max) {
          max_l = current_l;
          max_r = i;
          max = max_range;
      } 
      if(max_range < 0) {
        current_l = i;
      }
      i++;
    }
    
    System.out.print("L :" + max_l + "R :" + max_r);
    
  }
  
  public static void main(String[] args) {
    ArrayList<String> strings = new ArrayList<String>();
    strings.add("Hello, World!");
    strings.add("Welcome to CoderPad.");
    strings.add("This pad is running Java 8.");

    for (String string : strings) {
      System.out.println(string);
    } 
    System.out.println("Number of paths : " + paths(3));
    int[] arr = {1,1,0,0,0,1,0,1,0};
    System.out.println("Number of paths : ");
    maxOnes(arr);
  }
}
*/



import java.util.*;

public class Solution {
    int max;
    private int levelStart(int level) {
        return (int)Math.pow((double)2,(double)level)-1;
    }
    
    private int numOfNodesAtLevel(int level) {
        return (int)Math.pow((double)2,(double)level);
    }
    
    private int left(int index) {
        return 2*index+1;
    }
    
    private int right(int index) {
        return 2*index+2;
    }
    
    public void printSolutions(int[][] listOfTrees) {
        for(int[] tree:listOfTrees) {
            max = 0;
            maxSumPath(tree,0,0);
            System.out.println(max);
        }
    }
    
    public void maxSumPath(int[] tree,int index,int sum) {
        int left = left(index);
        int right = right(index);
        
        if(left>= tree.length && right >=tree.length) {
            if(max < sum) {
                max = sum;
            }
            return;
        }
        
        if(left<tree.length) {
            maxSumPath(tree,left,sum + tree[index]);
        }
        if(right<tree.length) {
            maxSumPath(tree,left,sum + tree[index]);
        }
    }
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int numOfCases = Integer.parseInt(sc.nextLine()); 
        int[][] listOfTrees = new int[numOfCases][];
        int index = 0;
        for(int i = 0; i < numOfCases; i++) {
            int height = Integer.parseInt(sc.nextLine());
            int[] tree = new int[(int)Math.pow((double)2,(double)height-1) +1];
            int ind = 0;
            while(height>0) {
                String[] split = sc.nextLine().split(" ");
                for(String s:split) {
                    tree[ind] = Integer.parseInt(s);
                    ind++;
                }
                height--;
            }
            listOfTrees[index] = tree;
            index++;
        }
        
        Solution s = new Solution();
        s.printSolutions(listOfTrees);
    }
}