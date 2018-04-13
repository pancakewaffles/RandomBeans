import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class App {
	
	public static void main(String[] args) {
		Graph g = new Graph(4);
		g.addEdge(1, 2,3);
		g.addEdge(1, 3,4);
		g.addEdge(1, 4,10);
		g.addEdge(2, 4,8);
		

		g.BFS(1);
		
		int[] a = g.dijkstra(1);
		for(int i = 0;i< a.length;i++){
			System.out.print(a[i] + " ");
		}
		

	}

}

class Graph {
	final int V;
	final int[][] adj;
	final boolean[] visited;
	final Queue<Integer> Q;
	final int[] level;
	final int[] parent;
	
	// for dijkstra's usage
	final boolean[] burnt;
	final int[] estimatedBurnTime;
	
	Graph(int v){
		this.V =v;
		visited = new boolean[v+1];
		adj = new int[v+1][v+1];
		Q = new LinkedList<Integer>();
		level = new int[v+1];
		Arrays.fill(level, -1);
		parent = new int[v+1];
		Arrays.fill(parent, -1);
		
		// for dijkstra's usage
		burnt = new boolean[v+1];
		estimatedBurnTime = new int[v+1];
		Arrays.fill(burnt, false);
		Arrays.fill(estimatedBurnTime, Integer.MAX_VALUE);
	}
	
	public void addEdge(int v, int w){
		adj[v][w] = 1;
		adj[w][v] = 1;
	}
	public void addEdge(int v, int w, int cost){
		adj[v][w] = cost;
		adj[w][v] = cost;
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
	
	public int[] dijkstra(int i){
		System.out.println("Dijkstra's Algorithm, starting from node "+i);
		
		estimatedBurnTime[i] = 0;
		
		for(int j = 1; j< V+1;j++){
			
			int minIndex = findNodeWithMinBurnTime();
			
			// Failsafe
			if(minIndex == -1){
				return estimatedBurnTime;
			}
			
			
			burnt[minIndex] = true;
			
			for(int k = 0 ; k < adj[minIndex].length;k++){
				if(adj[minIndex][k] != 0 && burnt[k] == false ){
					if(estimatedBurnTime[minIndex] + adj[minIndex][k] < estimatedBurnTime[k]){
						estimatedBurnTime[k] = estimatedBurnTime[minIndex] + adj[minIndex][k];
					}
				}
			}
			
			
		}
		
		
		
		
		return estimatedBurnTime;
	}

	private int findNodeWithMinBurnTime() {
		int minIndex = -1;
		int min = Integer.MAX_VALUE;
		for(int i = 0;i<estimatedBurnTime.length;i++){
			if(estimatedBurnTime[i] < min && burnt[i] == false){
				min = estimatedBurnTime[i];
				minIndex = i;
			}
		}
		return minIndex;
	}
	
	
	
}
