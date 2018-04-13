import java.util.Random;

public class App {

	public static void main(String[] args) {
		int n = new Random().nextInt(1000);
		System.out.println("Original Number: "+ n);
		
		System.out.println("In Binary Form: "+ Integer.toBinaryString(n));
		int bitLength = (Integer.toBinaryString(n)).length();
		
		 int bitComparator = 1<<(bitLength-1);
		 
		 int count = 0;
		 
		 for(int i =0;i<= (bitLength-1);i++){
			 bitComparator = 1<<(bitLength-1-i);
			 if((n & (bitComparator)) == bitComparator){
					count++;
				 }
		 }
		 
		 System.out.println("Number of ones: "+count);
		 
		 
		 
		

	}

}
