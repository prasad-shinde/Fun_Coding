package dynamicprogramming.simplified;

/**
 * @author Prasad
 * Knapsack : A knapsack of size W. We have to fill it to maximize the value.
 * We have set of N items with weight wi and value vi.
 * 
 * Link : http://www.cs.berkeley.edu/~vazirani/algorithms/chap6.pdf
 * There can be 2 main cases of this knapsack problem :
 * 
 * i) Repetition allowed :
 * We can insert more then one quantities of a particular item if available.
 * 
 * Subproblem : For a weight W, what is the max value obtained by trying all the items
 * 
 * Recurrence : 
 * K(w) = max{K(w-wi) + vi}		0<=i<N and w>wi
 * prev(w) = i
 * 
 * Base case : 
 * K(0) = 0
 * prev(0) = -1
 * 
 * Final Ans :
 * Max Value : K(W)
 * 
 * ii) Repetitions not allowed
 * We cannot pick more then 1 item of the same type
 * 
 * Subproblem : for a weight W, is a item i included in the knapsack or not
 * 
 * Recurrence :
 * 
 * K(w,i) = max{K(w-wi,i-1) + vi,K(w,i-1)}	if w>wi
 * isIncluded(w,i) = True	if max = K(w-wi,i-1) + vi
 * isIncluded(w,i) = False	if max = K(w,i-1)
 * 
 * k(w,i) = K(w,i-1)						if w<wi
 * isIncluded(w,i) = False
 * 
 * Base case :
 * K(0,i) = 0
 * isIncluded(0,i) = False
 * K(i,0) = 0
 * isIncluded(i,0) = False
 * 
 * Final Answer :
 * Max Value : K(W,N)
 * 
 */
public class Knapsack {
	public static int knapsackRepeat(int W,int N,int[] weight,int[] value) {
		int[] k = new int[W+1];
		int[] prev = new int[W+1];
		
		// base case
		k[0] = 0;
		prev[0] = -1;
		
		for(int i=1;i<=W;i++) {
			k[i] = k[i-1];
			prev[i] = i-1;
			for(int j = 0;j<N;j++) {
				if(i >= weight[j]) {
					if(k[i] < k[i-weight[j]] + value[j]) {
						k[i] = k[i-weight[j]] + value[j];
						prev[i] = j;
					}
				}
			}
		}
	
		int i = W;
		while(i!=0) {
			if(prev[i] != -1 && k[i] == k[i-1])
				i--;
			else {
				System.out.println("Item : " + (prev[i] + 1) + " Value : " + value[prev[i]]);
				i -=weight[prev[i]];
			}
		}
		return k[W];
	}
	
	
	public static int knapsackWithoutRepeat(int W,int N,int[] weight,int[] value) {
		int[][] k = new int[W+1][N+1];
		boolean[][] isIncluded = new boolean[W+1][N+1];
		
		// base case
		for(int i=0;i<=N;i++) {
			k[0][i] = 0;
			isIncluded[0][i] = false;
		}
		
		for(int i=0;i<=W;i++) {
			k[i][0] = 0;
			isIncluded[i][0] = false;
		}
		
		for(int w=1;w<=W;w++) {
			for(int j=1;j<=N;j++) {
				if(w>=weight[j-1]) {
					if(k[w][j-1] > (k[w-weight[j-1]][j-1] + value[j-1])) {
						k[w][j] = k[w][j-1];
						isIncluded[w][j] = false;
					} else {
						k[w][j] = k[w-weight[j-1]][j-1] + value[j-1];
						isIncluded[w][j] = true;
					}
					//k[w][j] = Math.max(k[w][j-1], k[w-weight[j-1]][j-1] + value[j-1]);  if we just need the max value and not the items
				} else {
					k[w][j] = k[w][j-1];
					isIncluded[w][j] = false;
				}
			}
		}
		int i = W,j = N;
		
		while(i!=0 && j!=0) {
			if(isIncluded[i][j]) {
				System.out.println("Item : " + j + "value : " + value[j-1]);
				i -= weight[j];
				j--;
			} else {
				j--;
			}
		}
		
		return k[W][N];
	}
	
	
	public static void main(String[] args) {
		int W = 10,N = 4;
		int[] weight = {6,3,4,2};
		int[] value = {30,14,16,9};
		System.out.print("\nKnapsack with repeat\n");
		System.out.print("\nMax value : " + knapsackRepeat(W, N, weight, value));
		System.out.print("\nKnapsack without repeat\n");
		System.out.print("\nMax value without repeat: " + knapsackWithoutRepeat(W, N, weight, value));
	}
}
