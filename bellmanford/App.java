import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class App {

	public static void main(String[] args) {
		Graph g = new Graph(8);
		
		g.addEdge(1, 2,10);
		g.addEdge(1, 8,8);
		
		g.addEdge(2, 6,2);
		
		g.addEdge(3, 2,1);
		g.addEdge(3, 4,1);
		
		g.addEdge(4, 5,3);
		
		g.addEdge(5, 6,-1);
		
		g.addEdge(6, 3,-2);
		
		g.addEdge(7, 2,-4);
		g.addEdge(7, 6,-1);
		
		g.addEdge(8, 7,1);
		
		
		
		// Time taken for adjacency Matrix
		double t1 = System.nanoTime();
		int[] d = g.bellmanford_adjMatrix(1);
		for(int i = 0;i<d.length;i++){
			System.out.println(d[i]);
		}
		double elapsedTime = System.nanoTime() - t1;
		System.out.println("BellmanFord Adjacency Matrix took : " +   elapsedTime/1000000.0 + " ms");
		
		// Time taken for edge List
		t1 = System.nanoTime();
		d = g.bellmanford_edgeList(1);
		for(int i = 0;i<d.length;i++){
			System.out.println(d[i]);
		}
		elapsedTime = System.nanoTime() - t1;
		System.out.println("BellmanFord Edge List took : " +   elapsedTime/1000000.0 + " ms");
		
	}

}

class Graph {
	final int V;
	final int[][] adj;
	final boolean[] visited;
	final Queue<Integer> Q;
	final int[] level;
	final int[] parent;
	
	
	/* for dijkstra's usage
	final boolean[] burnt;
	final int[] estimatedBurnTime;
	*/
	
	// for Bellman-Ford's usage
	final int[] distance;
	final LinkedList<LinkedList> edgeList;
	
	Graph(int v){
		this.V =v;
		visited = new boolean[v+1];
		adj = new int[v+1][v+1];
		Q = new LinkedList<Integer>();
		level = new int[v+1];
		Arrays.fill(level, -1);
		parent = new int[v+1];
		Arrays.fill(parent, -1);
		
		/* for dijkstra's usage
		burnt = new boolean[v+1];
		estimatedBurnTime = new int[v+1];
		Arrays.fill(burnt, false);
		Arrays.fill(estimatedBurnTime, Integer.MAX_VALUE);
		*/
		
		// for Bellman-Ford's usage
		distance = new int[v+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		edgeList = new LinkedList<LinkedList>();
		
		
		
		
		
	}
	
	public void addEdge(int v, int w){
		adj[v][w] = 1;
		adj[w][v] = 1;
	}
	public void addEdge(int v, int w, int cost){
		adj[v][w] = cost;
		//adj[w][v] = cost;
		
		LinkedList<Integer> edge = new LinkedList<Integer>();
		edge.add(v);
		edge.add(w);
		edge.add(cost);
		
		edgeList.add(edge);
		
	}
	
	public void BFS(int i){
		System.out.println("Starting traversal from node "+i);
		
		visited[i] = true;
		level[i] = 0;
		Q.add(i);
		
		while(Q.size() != 0){
			int v = Q.poll();
			System.out.println("Visiting node " + v);
			for(int j = 1;j<adj[v].length;j++){
				if(adj[v][j] == 1){
					if(!visited[j]){
						visited[j] = true;
						parent[j] = v;
						level[j] = 1 + level[v];
						Q.add(j);
					}
					
				}
			}
		}
		
	}
	
	public int[] bellmanford_adjMatrix(int i){
		distance[i] = 0;
		
		for(int count = 1; count < V ; count++){ // V-1 iterations
			
			for(int j = 1; j < V+1; j++){
				for(int k = 1; k < V+1;k++){
					if(adj[j][k] != 0){
						if(distance[j] != Integer.MAX_VALUE && distance[j] + adj[j][k] < distance[k] ){
							distance[k] = distance[j] + adj[j][k];
						}
					}
				}
			}
			
		}
		return distance;
		
		
	}
	
	public int[] bellmanford_edgeList(int i){
		distance[i] = 0;
		
		for(int count = 1; count < V ; count++){ // V-1 iterations
			
			for(int j = 0;j < edgeList.size();j++){
				LinkedList<Integer> edge =  edgeList.get(j);
				if(distance[edge.get(0)] != Integer.MAX_VALUE && distance[edge.get(0)] + edge.get(2) < distance[edge.get(1)]){
					distance[edge.get(1)] =  distance[edge.get(0)] + distance[edge.get(2)];
				}
			}
			
			
		}
		return distance;
		
		
	}


	
}
