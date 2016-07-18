package classic.problems;

public class TravellingSalesmanProblem {
	/*
	 * Travelling Salesman Problem : 
	 * Let V = 1,2,3,4....N be set to cities
	 * We have cost[i][j] = value for all i,j belonging to {1,2,3...N}
	 * 
	 * We have to find a TSP route starting and ending at city : 1
	 * A TSP is a hamiltonian cycle with minimum cost. The problem is NP Hard and has no linear/polynomial time solution
	 * 
	 * Naive Approach : 
	 * Algorithm : 
	 * 1. Let 1 be the starting vertex
	 * 2. For all the combinations of the remaining n-1 cities
	 * 		2.1 calculate cost of each arrangement C.
	 * 		2.2 maintian the minimum cost and route for the combinations.
	 * 3. return the minimum cost and route
	 * 
	 * Analysis :
	 * Different ways to arrange n-1 cities in a path is (n-1)!
	 * Time Complexity : O(n!)  -> realy really bad! Its impossible to find one.
	 * 
	 * Dynamic Programming Approach : 
	 * We first start with a subset of 2 cities and the increase the cities 1 by 1
	 * We assume that city 1 is the start city.
	 * 
	 * Recurrance for DP : We define the  function TSP(S,i) 
	 * where S is the subset of cities and i the the new vertex under consideration
	 * 
	 * TSP(S,i) = dist(1,i) for S containing just two elements {1,i}
	 * TSP(S,i) = min(TSP(S - {i},j) + dist(i,j)) where i!=j and i!=1 and j!=1
	 */
	
	
}
