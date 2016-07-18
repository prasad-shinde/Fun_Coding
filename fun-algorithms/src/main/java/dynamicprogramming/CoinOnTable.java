package dynamicprogramming;

import java.util.*;

public class CoinOnTable {
    char[][] board;
    int n,m;
    int steps;
    int min;
    int[][] dp;
    int destI,destJ;
    
    public void initialize() {
        dp = new int[n][m];
        for(int i=0;i<n;i++) {
            for(int j = 0;j<m;j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
    }
    
    boolean atDestination(int i,int j) {
        return board[i][j] == '*';
    }
    
    public int minCount() {
        initialize();
        return findSolution(0,0,0,steps);
    }
    
    public int findSolution(int i,int j,int changeCount,int movesCount) {
        if(i < 0 || j<0 || i>=n || j>=m) {
        	return changeCount;
        }
        if(atDestination(i,j)) {
        	System.out.print(changeCount + " " + dp[i][j]);
        	return changeCount;
        }
        if(movesCount<=0) {
            return changeCount;
        }
       
        if(dp[i][j] != Integer.MAX_VALUE)
        	if(dp[i][j] > changeCount)
        		dp[i][j] = changeCount;
        	else
        		return dp[i][j];
        else
        	dp[i][j] = changeCount;

        int r,l,u,d;
        r = findSolution(i,j+1,changeCount + ((board[i][j] != 'R')?1:0),movesCount-1);
        l = findSolution(i,j-1,changeCount + ((board[i][j] != 'L')?1:0),movesCount-1);
        d = findSolution(i+1,j,changeCount + ((board[i][j] != 'D')?1:0),movesCount-1);
        u = findSolution(i-1,j,changeCount + ((board[i][j] != 'U')?1:0),movesCount-1);
        
        dp[i][j] = min(r,l,d,u);
        return dp[i][j];
    }
    
    int min(int a,int b,int c,int d) {
        return Math.min(a,Math.min(b,Math.min(c,d)));
    }    
    
    public void input() {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String[] split = line.split(" ");
        n = Integer.parseInt(split[0]);
        m = Integer.parseInt(split[1]);
        steps = Integer.parseInt(split[2]);
        board = new char[n][m];
        for(int i=0;i<n;i++) {
            line = sc.nextLine();
            for(int j=0;j<m;j++) {
                board[i][j] = line.charAt(j);
            }
        }
        sc.close();
    }
    /*
     * Input : 
     * 20 17 47
DRLULLRRRLRDLRRLU
URUURLRLLLDULRRRR
DRDLDDLDRRDRURRLR
DRRRRRRLDULDDUDLD
DULRDDULDUDULRDUD
LRURUDURRUURDDUDL
URURDLRUULRRDLDLR
DLRUDLDRUUDULUDUU
*ULLURDRDUURLDRDR
ULDUDUUULLURURURR
LDRDLDRRLDDRRLRLD
RDDLURRRDDLRDURLD
ULRLRLRRLLLRRURRL
RLLLDRDURLRURLUDD
DRLDURRLURUULLRDU
RURRUDLLLDDDRUUUD
UUUUDDLRURULRRDRD
URDUUDRDLDRLLULRU
DRDUUULUUDURULDDL
LLULDRLRRRUDLDRRU

Output: 3*/
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    	CoinOnTable  s = new CoinOnTable();
        s.input();
        System.out.println(s.minCount());
        
    }
}



/*
import java.util.Scanner;

public class CoinOnTheTable {

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);

        int n = cin.nextInt();
        int m = cin.nextInt();
        int K = cin.nextInt();
        int x = 0, y = 0;
        char a[][] = new char[n][];
        for (int i=0; i<n; i++) {
            a[i] = cin.next().toCharArray();
            for (int j=0; j<m; j++)
                if (a[i][j] == '*') {
                    x = i;
                    y = j;
                }
        }


        int f[][][] = new int[K + 1][n][m];
        int ans = 1 << 29;
        for (int k=0; k<=K; k++)
            for (int i=0; i<n; i++)
                for (int j=0; j<m; j++) 
                    if (k == 0) f[k][i][j] = i == 0 && j == 0 ? 0 : 1 << 29;
                    else {
                        int res = 1 << 29;
                        if (i > 0)     res = Math.min(res, f[k - 1][i - 1][j] + (a[i - 1][j] == 'D' ? 0 : 1));
                        if (i < n - 1) res = Math.min(res, f[k - 1][i + 1][j] + (a[i + 1][j] == 'U' ? 0 : 1));
                        if (j > 0)     res = Math.min(res, f[k - 1][i][j - 1] + (a[i][j - 1] == 'R' ? 0 : 1));
                        if (j < m - 1) res = Math.min(res, f[k - 1][i][j + 1] + (a[i][j + 1] == 'L' ? 0 : 1));

                        f[k][i][j] = res;
                    }

        for (int k=0; k<=K; k++)
            ans = Math.min(ans, f[k][x][y]);

        if (ans < (1 << 29)) System.out.println(ans);
        else System.out.println(-1);

        cin.close();
    }
}

*/