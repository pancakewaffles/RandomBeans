
public class App {

	public static void main(String[] args) {
		int A = 0; // $100
		int B = 0; // $20
		int C = 0; // $2
		
		while((A*A + B*B + C*C)<10404){
			
			/* Debug stuff
			System.out.println("A = "+A);
			System.out.println("B = "+B);
			System.out.println("C = "+C);
			System.out.println( (A*A + B*B + C*C) );
			System.out.println((100/new Float((A+1)*(A+1)-A*A)));
			System.out.println((20/new Float((B+1)*(B+1)-B*B)));
			System.out.println((2/new Float((C+1)*(C+1)-C*C)));
			
			
			System.out.println( Float.compare( (100/new Float((A+1)*(A+1)-A*A)) , (20/new Float((B+1)*(B+1)-B*B)) ) );
			
			*/
			
			if( Float.compare( (100/new Float((A+1)*(A+1)-A*A)) , (20/new Float((B+1)*(B+1)-B*B)) ) >= 0  ){
				if( Float.compare( (100/new Float((A+1)*(A+1)-A*A)) , (2/new Float((C+1)*(C+1)-C*C))) >= 0  ){
					A = A +1;
				}else{
					C = C +1;
				}
			}else if ( Float.compare( (20/new Float((B+1)*(B+1)-B*B)) , (2/new Float((C+1)*(C+1)-C*C)) ) >= 0 ){
				B = B +1;
			}else{
				C= C+1;
			}
		}
		System.out.println("A = "+A);
		System.out.println("B = "+B);
		System.out.println("C = "+C);

	}

}
