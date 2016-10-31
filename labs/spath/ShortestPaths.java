package spath;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import spath.graphs.DirectedGraph;
import spath.graphs.Edge;
import spath.graphs.Vertex;
import timing.Ticker;
import spath.PriorityQueue;
import spath.Decreaser;
import spath.VertexAndDist;

/**
 * Compute shortest paths in a graph.
 * 
 * Your constructor should compute the actual shortest paths and
 * maintain all the information needed to reconstruct them.  The
 * returnPath() function should use this information to return the
 * appropriate path of edge ID's from the start to the given end.
 * 
 * Note that the start and end ID's should be mapped to vertices using
 * the graph's get() function.
 * 
 * You can ignore the input and startTime arguments to the constructor
 * unless you are doing the extra credit.
 */
public class ShortestPaths {

	private final static Integer inf = Integer.MAX_VALUE;
	private HashMap<Vertex, Decreaser<VertexAndDist>> map;
	private HashMap<Vertex, Edge> toEdge;
	private Map<Edge, Integer> weights;
	private Vertex startVertex;
	private final DirectedGraph g;

	public ShortestPaths(DirectedGraph g, Vertex startVertex, Map<Edge,Integer> weights) {

		this.map         = new HashMap<Vertex, Decreaser<VertexAndDist>>();
		this.toEdge      = new HashMap<Vertex, Edge>();
		this.weights     = weights;
		this.startVertex = startVertex;
		this.g           = g;
	}

	public void run() {
		PriorityQueue<VertexAndDist> pq = new PriorityQueue<VertexAndDist>(30000, new Ticker());

		// Initially all vertices are placed in the heap
		//   infinitely far away from the start vertex
		for (Vertex v : g.vertices()) {
			toEdge.put(v, null);
			VertexAndDist a = new VertexAndDist(v, inf);
			Decreaser<VertexAndDist> d = pq.insert(a);
			map.put(v, d);
		}

		// Now we decrease the start node's distance from itself to 0.
		// Note how we look up the decreaser using the map...
		Decreaser<VertexAndDist> startVertDist = map.get(startVertex);

		// and then decrease it using the Decreaser handle
		startVertDist.decrease(startVertDist.getValue().sameVertexNewDistance(0));


		// Extract nodes from the pq heap
		//   and act upon them as instructed in class and the text.
		//
		// IMPORTANT! 
		// You must write your pseudocode in comments first,
		// then write the code, leaving your pseudocode comments in place.
		// Code without pseudocode comments will receive NO CREDIT!
		
		//MY PSEUDOCODE
		//while pq is not empty
			//u=extractMin  //pull out the minimum node
			//z=u.dist		//set the distance traveled equal to the dist value of the min node
			//for every vertex v connected to u
				//get the map value for the edge
				//if z+map<v.dist
					//set v.dist=z+map
				//end
			//end
		//end

		// FIXME
		int z;
		while (!pq.isEmpty()){
			VertexAndDist u=pq.extractMin();
			z=u.getDistance();
			for (Edge e: u.getVertex().edgesFrom()){
				Decreaser<VertexAndDist> v = map.get(e.to);
				if (z+weights.get(e)<v.getValue().getDistance()){
					v.decrease(v.getValue().sameVertexNewDistance(z+weights.get(e)));
					toEdge.put(v.getValue().getVertex(), e);
				}
			}
		}

		// Tip: "for each vertex v such that v -> u" here is the Java code:
		// for (Edge e : u.getVertex().edgesFrom()) {
		//     Decreaser<VertexAndDist> v = map.get(e.to);

		// Note: make sure you update update the toEdge map so that the returnPath
		// function works!
	}

	/**
	 * Return a List of Edges forming a shortest path from the
	 *    startVertex to the specified endVertex.  Do this by tracing
	 *    backwards from the endVertex, using the map you maintain
	 *    during the shortest path algorithm that indicates which
	 *    Edge is used to reach a Vertex on a shortest path from the
	 *    startVertex.
	 * @param endVertex 
	 * @return
	 */
	public LinkedList<Edge> returnPath(Vertex endVertex) {
		LinkedList<Edge> path = new LinkedList<Edge>();

		// IMPORTANT! 
		// You must write your pseudocode in comments first,
		// then write the code, leaving your pseudocode comments in place.
		// Code without pseudocode comments will receive NO CREDIT!

		// FIXME
		//MY PSEUDOCODE
		//Start with the end vertex
		//Loop until the start vertex
			//Get the edge attached to the vertex
			//Add the path to the front of the linked list
			//Move to the previous vertex
		//end		
		
		Vertex v=endVertex;
		while (v!=startVertex){
			Edge e=toEdge.get(v);
			path.addFirst(e);
			v = e.from;
		}
		return path;
	}

	/**
	 * Return the length of all shortest paths.  This method
	 *    is completed for you, using your solution to returnPath.
	 * @param endVertex
	 * @return
	 */
	public int returnValue(Vertex endVertex) {
		LinkedList<Edge> path = returnPath(endVertex);
		int pathValue = 0;

		for(Edge e : path) {
			pathValue += weights.get(e);
		}
		return pathValue;
	}
}
