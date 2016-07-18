package amazontest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/*
 * DEPENDENCY TREE:
 * 
 * A factory produces many different parts. It needs to perform a different set of operations on each type of part that it produces.
 *  Each operation is identified by its unique name, and a list of the names of its prerequisite operations. 
 *  Implement a class which accepts a list of operations, then processes a part by calling the correct sequence of operations in order. 
 *  No operation should be performed twice on the same part. 
 *  Please describe the time complexity of your solution in the comments, along with any interesting details of your implementation.
 */

/* NOTE 1 : Since no constraints was mentioned in the input/output form. I have assumed the following input and output formats
 * NOTE 2 : I'm assuming integer names to the operations, with small modification we can get it work for strings as well
 * 
 * Input : 
 * operationName:dependentOp1,dependentOp2,dependentOp3
 * operationName:dependentOp1,dependentOp2,dependentOp3
 * operationName:dependentOp1,dependentOp2,dependentOp3
 * 
 * Input Example :
 * 1:2,4,5
 * 2:3,4
 * 3:
 * 4:5
 * 5:
 * 
 * Output Format:
 * operationName operationName operationName operationName
 * Left to Right the order in which they should be performed
 *  
 * Output Example:
 * Performing 2 on part : xyz
 * 3 5 4 2
 * 
 * Time Complexity of Perform Operation : O(V+E) where E = total number of operations and E = total number of dependency between operations.
 *   
 */
public class Solution {
    // This problem to me looks like a DAG for the operations to be performed. Where we need to process a particular node. 
	// But to do that we need to process all the node that appear before it in a topological order. 
	// I'm assuming here that there are no cyclic dependencies as with a cycle in a graph there can be no topological order.
	
    private int[][] adjMatrix;
	private int numOfVertices;
    
    public Solution(String[] operations) {
    	numOfVertices = operations.length+1;
    	adjMatrix = new int[numOfVertices][numOfVertices];
    	initialize(operations);
    }
    
    private void initialize(String[] dependencyList) {
    	
    	for(int i=0;i<numOfVertices;i++) {
    		for(int j=0;j<numOfVertices;j++) {
    			adjMatrix[i][j] = 0;
    		}
    	}
    	
    	for(String dependency:dependencyList) {
    		String[] splits = dependency.split(":");
    		if(splits.length<=1)
    			continue;
    		
    		int operation = Integer.parseInt(splits[0]);
    		for(String op:splits[1].split(",")) {
    			adjMatrix[operation][Integer.parseInt(op)] = 1;
    		}
    	}
    }

    /**
	 * @param vertex
	 * @return
	 * TC = O(|num of operations|)
	 */
	private Set<Integer> prerequisiteOf(int vertex) {
		Set<Integer> neighbours = new HashSet<Integer>();
		for(int i=0;i<numOfVertices;i++) {
			if(i != vertex && adjMatrix[vertex][i] != 0) {
				neighbours.add(i);
			}
		}
		return neighbours;
	}
    
    public List<Integer> topologicalSort(int operation) {
		boolean[] visited = new boolean[this.numOfVertices];
		List<Integer> processingOrder = new ArrayList<Integer>();
		
		Arrays.fill(visited, false);
		Stack<Integer> topologicalOrder = new Stack<Integer>();
		
		dfsUtil(operation,visited,topologicalOrder);
		
		while(!topologicalOrder.isEmpty()) {
			processingOrder.add(0,topologicalOrder.pop());
		}
		
		return processingOrder;
	}
	
	private void dfsUtil(int u,boolean[] visited,Stack<Integer> topologicalOrder) {
		if(visited[u])
			return;
		
		visited[u] = true;
		for(Integer neighbour:prerequisiteOf(u)) {
			if(!visited[neighbour])
				dfsUtil(neighbour, visited, topologicalOrder);
		}
		
		topologicalOrder.push(u);
	}
    
    public void perform(String part,int operation) {
    	System.out.println("Performing " + operation + " on part : " + part);
    	List<Integer> processingOrder = topologicalSort(operation);
    	for(Integer op:processingOrder) {
    		System.out.print(op + " ");
    	}
    }
    
    
    /*
     * 
     */
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
    	Solution s = new Solution(args);
    	s.perform("xyz", 2);
    }
}