package uk.ac.cam.ct538.prejava.ex3;

public class ArrayLife {
	public static boolean getCell(boolean[][] world, int col, int row) {
		   if (row < 0 || row > world.length - 1) return false;
		   if (col < 0 || col > world[row].length - 1) return false;

		   return world[row][col];
		
	}
	public static void setCell(boolean[][] world, int col, int row, boolean value) {
		if (row < 0 || row > world.length - 1) return;
		if (col < 0 || col > world[row].length - 1) return;
		
		world[row][col] = value;
		
	}
	public static int countNeighbours(boolean[][] world, int col, int row) {
		int noofLiveNeighbours = 0;
		for(int i = row-1; i <= row + 1;i++) {
			for(int j = col-1;j<=col+1;j++) {
				if(isValid(i,j,world) && !isselectedCell(i,j,row,col) && getCell(world,j,i)) {
							noofLiveNeighbours++;		
				}
			}
		}
		return noofLiveNeighbours;
		
	}
	private static boolean isselectedCell(int i, int j, int row, int col) {
		if(i == row && j == col) {
			return true;
		}else {
			return false;
		}
	}
	public static boolean computeCell(boolean[][] world, int col, int row) {
		   // liveCell is true if the cell at position (col,row) in world is live
		   boolean liveCell = getCell(world, col, row);
		    
		   // neighbours is the number of live neighbours to cell (col,row)
		   int neighbours = countNeighbours(world, col, row);

		   // we will return this value at the end of the method to indicate whether 
		   // cell (col,row) should be live in the next generation
		   boolean nextCell = false;
		    
		   //A live cell with less than two neighbours dies (underpopulation)
		   if (liveCell == true && neighbours < 2) {
		      nextCell = false;
		   }
		 
		   //A live cell with two or three neighbours lives (a balanced population)
		   //TODO: write a if statement to check neighbours and update nextCell
		   if(liveCell == true && (neighbours == 2 || neighbours == 3)) {
			   nextCell = true;
		   }

		   //A live cell with with more than three neighbours dies (overcrowding)
		   //TODO: write a if statement to check neighbours and update nextCell
		   if(liveCell == true && neighbours > 3) {
			   nextCell = false;
		   }

		   //A dead cell with exactly three live neighbours comes alive
		   //TODO: write a if statement to check neighbours and update nextCell
		   if(liveCell == false && neighbours == 3) {
			   nextCell = true;
		   }
		   
		   return nextCell;
		
	}
	public static boolean[][] nextGeneration(boolean[][] world) {
		boolean[][] new_world = new boolean[world.length][world[0].length];
		
		for(int i = 0;i<world.length;i++) {
			for(int j = 0;j<world[0].length;j++) {
				setCell(new_world,i,j,computeCell(world,i,j));
			}
		}
		return new_world;
	
	}
	public static boolean isValid(int row, int col , boolean[][] world) {
		if(row < 0 || col < 0 || row >world.length-1 || col > world[0].length-1) {
			return false;
		}else {
			return true;
		}
	}
	public static void play(boolean[][] world) throws java.io.IOException {
		   int userResponse = 0;
		   while (userResponse != 'q') {
		      print(world);
		      userResponse = System.in.read();
		      world = nextGeneration(world);
		   }
		}
	public static void print(boolean[][] world) { 
		   System.out.println("-"); 
		   for (int row = 0; row < 8; row++) { 
		      for (int col = 0; col < 8; col++) {
		         System.out.print(getCell(world, col, row) ? "#" : "_"); 
		      }
		      System.out.println(); 
		   } 
		}
	public static boolean getFromPackedLong(long packed, int position) {
        return ((packed >>> position) & 1) == 1;
}
	public static void main(String[] args) throws java.io.IOException {
		   int size = Integer.parseInt(args[0]);
		   long initial = Long.decode(args[1]);
		   boolean[][] world = new boolean[size][size];
		   //place the long representation of the game board in the centre of "world"
		   for(int i = 0; i < 8; i++) {
		      for(int j = 0; j < 8; j++) {
		         world[i+size/2-4][j+size/2-4] = getFromPackedLong(initial,i*8+j);
		      }
		   }
		   play(world);
		//System.out.println(computeCell(0x20A0600000000000L,4,6));
		
	}
	

}
