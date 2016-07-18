package classic.problems;

/**
 * @author Prasad
 * The vertex cover problem : http://en.wikipedia.org/wiki/Vertex_cover
 * finding a vertex sets such that, for every edge (u,v) of the graph either u or v or both belongs to the vertex set.
 * 
 * Finding the minimum vertex set is a NP-Complete problem
 */
public class VertexCover {

	/*
	 * Approximate algorithm (from the wiki link mentioned above):
	 * APPROX VERTEX COVER(G)
		C= null
		E'= G.E
		while E' is not empty
			let (u,v) be an arbitrary edge of E'
			C = C U {u,v}
			remove from E' every edge incident on either u or v
		return C
	 */
	
	/*
	 * Algortihm (My version, inspired from the above algorithm) :
	 * Vertex Cover(G)
	 * 	C = null
	 * 	E' = G.E
	 * 	V' = Descending order of sorted vertices G.V on the basis of degree // This could be a max heap
	 * 	
	 * 	while E' is not empty
	 * 		u = ExtractMax(V')
	 * 		C = C U u
	 * 		remove all edges from E' that has u as one of the vertex
	 * 	end while
	 * 
	 * 	return C
	 */
}
