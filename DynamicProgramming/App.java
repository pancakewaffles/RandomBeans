import java.util.HashMap;

/*
 * Dynamic Programming: Memoisation
 * 
 * Memoisation is all about building a memory table to store values that you will use in solving subproblems to a bigger overall problem. 
 * It is simply faster and more efficient than mere recursion. 
 * It's the upgraded version of recursion.
 * 
 * In general it flows like this:
 * 
 * function f(x,y,z):
 * 	if ftable[x][y][z]
 * 		return ftable[x][y][z];
 * 	
 * 	value = expression in terms of subproblems
 * 	
 * 	ftable[x][y][z] = value;
 * 	
 * 	return value;
 * 
 * 
 * Dynamic Programming: Dynamic Programming
 * 
 * All about dependencies. More specifically, building from dependencies.
 * What we do is we solve subproblems in topological order of dependency. (* Dependencies must form a Directed Acyclic Graph (DAG) *)
 * Iterative evaluation.
 */
public class App {

	public static void main(String[] args) {
		// Fibonacci - the recursive way
		long t0 = System.nanoTime();
		Fibonacci_nonDP f = new Fibonacci_nonDP();
		System.out.println(f.fib(42));
		System.out.println("Elapsed time: "+ (double)(System.nanoTime() - t0)/1000000 + "ms" );
		
		// Fibonacci - the memoisation way
		t0 = System.nanoTime();
		Fibonacci_Memoisation f2 = new Fibonacci_Memoisation();
		System.out.println(f2.fib(42));
		System.out.println("Elapsed time: "+ (double)(System.nanoTime() - t0)/1000000 + "ms" );
		
		// Fibonacci - the dynamic programming way
		t0 = System.nanoTime();
		Fibonacci_DP f3 = new Fibonacci_DP();
		System.out.println(f3.fib(42));
		System.out.println("Elapsed time: "+ (double)(System.nanoTime() - t0)/1000000 + "ms" );

	}

}

class Fibonacci_nonDP{
	public Fibonacci_nonDP() {
		
	}
	public int fib(int n) {
		if(n == 0) {
			return 0;
		}
		if(n == 1) {
			return 1;
		}
		return fib(n-1) + fib(n-2);
	}
}

class Fibonacci_Memoisation{
	
	static HashMap<Integer,Integer> memorytable;
	
	public Fibonacci_Memoisation() {
		memorytable = new HashMap<Integer,Integer>();
	}
	public int fib(int n) {
		
		if(memorytable.containsKey(n)) {
			return memorytable.get(n);
		}
		
		if(n == 0) {
			memorytable.put(0, 0);
			return 0;
		}
		if(n == 1) {
			memorytable.put(1, 1);
			return 1;
		}
		//int value = fib(n-1) + fib(n-2);
		memorytable.put(n, fib(n-1) + fib(n-2));
		
		return fib(n-1) + fib(n-2);
		
		
		
	}
}

class Fibonacci_DP{
	
	int[] fibArray;
	
	public Fibonacci_DP() {
		
	}
	
	public int fib(int n) {
		fibArray = new int[n+1];
		fibArray[0] = 0;
		fibArray[1] = 1;
		
		for(int i = 2;i<=n;i++) {
			fibArray[i] = fibArray[i-1] + fibArray[i-2];
		}
		
		
		return fibArray[n];
	}
}
