import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class App {
	
	public static void main(String[] args) {
		Graph g = new Graph(4);
		g.addEdge(1, 2);
		g.addEdge(1, 3);
		g.addEdge(1, 4);
		g.addEdge(2, 4);
		

		g.BFS(1);
		

	}

}

class Graph {
	final int V;
	final int[][] adj;
	final boolean[] visited;
	final Queue<Integer> Q;
	final int[] level;
	final int[] parent;
	
	Graph(int v){
		this.V =v;
		visited = new boolean[v+1];
		adj = new int[v+1][v+1];
		Q = new LinkedList<Integer>();
		level = new int[v+1];
		Arrays.fill(level, -1);
		parent = new int[v+1];
		Arrays.fill(parent, -1);
	}
	
	public void addEdge(int v, int w){
		adj[v][w] = 1;
		adj[w][v] = 1;
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
	
	
	
}
