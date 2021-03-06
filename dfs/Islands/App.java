/*
 * Find the number of islands | Set 1 (Using DFS)
Given a boolean 2D matrix, find the number of islands. A group of connected 1s forms an island. For example, the below matrix contains 5 islands.
Input : mat[][] = {{1, 1, 0, 0, 0},
                   {0, 1, 0, 0, 1},
                   {1, 0, 0, 1, 1},
                   {0, 0, 0, 0, 0},
                   {1, 0, 1, 0, 1} 
Output : 5

This is an variation of the standard problem: �Counting number of connected components in a undirected graph�.

Before we go to the problem, let us understand what is a connected component. 
A connected component of an undirected graph is a subgraph in which every two vertices are connected to each other by a path(s), 
and which is connected to no other vertices outside the subgraph.

A graph where all vertices are connected with each other, has exactly one connected component, consisting of the whole graph. 
Such graph with only one connected component is called as Strongly Connected Graph.

The problem can be easily solved by applying DFS() on each component. 
In each DFS() call, a component or a sub-graph is visited. 
We will call DFS on the next un-visited component. 
The number of calls to DFS() gives the number of connected components.

A group of connected 1s forms an island. A cell in 2D matrix can be connected to 8 neighbors. 
So, unlike standard DFS(), where we recursively call for all adjacent vertices, here we can recursive call for 8 neighbors only. 
We keep track of the visited 1s so that they are not visited again.

The idea is simple. For each cell in a M x N grid, we check if it hasn't been visited. If so, we mark it, and then recur for all of its connected neighbours.
Treat it as exploring the neighbours to its full extent. When the recursions end, we have found our first island. 

Then we iterate onto the next cell, and check if it has been visited (if it has it means it is part of the first island, and can be skipped.).
By this logic, we will be able to explore all islands and count the number of islands.

 */
public class App {
	// Driver method
	public static void main(String[] args) {
		int[][] M = new int[][] {{1, 1, 0, 0, 0},
								{0, 1, 0, 0, 1},
								{1, 0, 0, 1, 1},
								{0, 0, 0, 0, 0},
								{1, 0, 1, 0, 1}
								};
		Islands I = new Islands();
		System.out.println("Number of islands is: " + I.countIslands(M));
		

	}

}

class Islands{
	static final int ROW = 5, COL = 5;
	// Make a boolean array to mark visited cells.
	// Initially all cells are unvisited.
	boolean visited[][] = new boolean[ROW][COL];
	
	// A function to check if a given cell (row,col) can be included in DFS
	boolean isSafe(int[][] M,int row, int col) {
		
		// row number is in range, column number is in range
		// and value is 1 and not yet visited
		return (row>=0) && (row<ROW) && // row in range
				(col>=0) && (col<COL) && // col in range
				(M[row][col]==1 && !visited[row][col]); // value is 1, and not yet visited
	}
	
	// A utility function to do DFS for a 2D boolean matrix. 
	// It only considers the 8 neighbours as adjacent vertices.
	void DFS(int[][] M, int row, int col) {
		
		// These arrays are used to get row and column numbers of the 8 neighbors of a given cell.
		// SMART!
		int[] rowNbr = new int[] { -1, -1, -1,  0, 0,  1, 1, 1};
		int[] colNbr = new int[] { -1,  0,  1, -1, 1, -1, 0, 1};
		
		// Mark this cell as visited.
		visited[row][col] = true;
		
		// Recur for all 8 connected neighbours
		for(int k = 0; k < 8; k++) {
			if(isSafe(M,row + rowNbr[k], col + colNbr[k])) {
				DFS(M,row + rowNbr[k], col + colNbr[k]);
			}
		}
		
	}
	
	// The main function that returns count of islands in a given boolean 2D matrix.
	int countIslands(int[][] M) {
		
		// Initialise count as 0 and traverse through all cells of the given matrix.
		int count = 0;
		for(int i = 0; i < ROW; i++) {
			for(int j = 0;j < COL;j++) {
				if(M[i][j] == 1 && !visited[i][j]) { 
					// *** If a cell with value 1 is not visited yet, then a new island has been found.
					// We then visit all cells in this island and increment island count.
					
					DFS(M,i,j);
					count++;
					
				}
			}
		}
		
		return count;
		
		// Easy, right?
		
		
	}
}
