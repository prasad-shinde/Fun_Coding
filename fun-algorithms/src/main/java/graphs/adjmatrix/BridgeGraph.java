package graphs.adjmatrix;

/**
 * Bridges in Graph : 
 * In graph theory, a bridge, isthmus, cut-edge, or cut arc is an edge of a graph whose deletion increases its number of connected components.
 * Equivalently, an edge is a bridge if and only if it is not contained in any cycle. A graph is said to be bridgeless or isthmus-free if it 
 * contains no bridges.
 * Algorithm :
 * 1. Find a spanning forest of G
 * 2. Create a rooted forest F from the spanning tree
 * 3. Traverse the forest F in preorder and number the nodes. Parent nodes in the forest now have lower numbers than child nodes.
 * 4. For each node v in preorder, do:
 * 		4.1 Compute the number of forest descendants ND(v) for this node, by adding one to the sum of its children's descendants.
 * 		4.2 Compute L(v), the lowest preorder label reachable from v by a path for which all but the last edge stays within the
 * 			subtree rooted at v. This is the minimum of the set consisting of the values of L(w) at child nodes of v and of the 
 * 			preorder labels of nodes reachable from v by edges that do not belong to F.
 * 		4.3 Similarly, compute H(v), the highest preorder label reachable by a path for which all but the last edge stays within 
 * 			the subtree rooted at v. This is the maximum of the set consisting of the values of H(w) at child nodes of v and of 
 * 			the preorder labels of nodes reachable from v by edges that do not belong to F.
 * 5. For each node w with parent node v, if L(w) = w and H(w) <  w + ND(w) then the edge from v to w is a bridge.
 * 
 * References: 1. Bridge Graph Theory : https://en.wikipedia.org/wiki/Bridge_(graph_theory)
 * @author Prasad
 *
 */
public class BridgeGraph {

}
