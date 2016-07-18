package dynamicprogramming;

/**
 * @author Prasad
 * Link : http://www.cs.rit.edu/~grd-665/Hw4/CSCI665_HW4_2145.pdf
 * 
 * Recurrence : 
 * C(i,j,k) = Max{C(i,k-1,p) + C(K+1,j,q) + 1} for i<p<=k-1 && k+1<=q<j and A[p] + A[q] >= 2*A[k]
 */
public class LongestConvexSubsequence {

}
