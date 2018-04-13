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
		

		g.DFS(1);
		
		System.out.println(g.post[1]);

	}

}

class Graph {
	final int V;
	final int[][] adj;
	final boolean[] visited;
	// Useful for tracing a path
	final int[] parent;
	// Useful for DFS numbering, an advantage unique to DFS
	final int[] pre;
	final int[] post;
	int count;
	
	Graph(int v){
		this.V =v;
		visited = new boolean[v+1];
		adj = new int[v+1][v+1];
		parent = new int[v+1];
		Arrays.fill(parent, -1);
		pre = new int[v+1];
		Arrays.fill(pre, -1);
		post = new int[v+1];
		Arrays.fill(post, -1);
		count = 0;
	}
	
	public void addEdge(int v, int w){
		adj[v][w] = 1;
		adj[w][v] = 1;
	}
	
	public void DFS(int i){
		System.out.println("Starting traversal from node "+i);
		pre[i] = count;
		count++;
		visited[i] = true;
		
		
		for(int j = 1; j < adj[i].length;j++){
			if(adj[i][j] == 1){
				if(!visited[j]){
					System.out.println("Visiting "+j);
					visited[j] = true;
					parent[j] = i;
					pre[j] = count;
					DFS(j);
					post[j] = count;
					count++;
				}
				
			}
		}
		post[i] = count;
		
	}
	
	
	
}
