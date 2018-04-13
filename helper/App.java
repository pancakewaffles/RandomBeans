import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			int T = 0; // T is the number of input cases
			
			while(T>0){ // The while loop
				String input = br.readLine();
				
				System.out.println(input + " is " + isValidDate(input)); // isValidDate checks if the date is valid, according to the requirements of the qns.
				
				
				T--;
			}
			
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
		/*
		
		Scanner key = new Scanner(System.in);
		String s = key.nextLine();
		String month = s.substring(0, 2);
		String day = s.substring(3,5);
		String year = s.substring(6);
		*/
		

	}

	private static String isValidDate(String input) {
		
		String[] s = input.split("/");
		int month = Integer.parseInt(s[0]);
		int day = Integer.parseInt(s[1]);
		int year = Integer.parseInt(s[2]);
		
		if(month < 1 || month > 12){
			return "not valid, because it is not a valid month.";
		}
		if(day < 1 || day > 31){
			return "not valid, because it is not a valid day.";
		}
		
		// Weeded out the simple cases, now check for complex cases (e.g. Feb 31 2016)
		
		if(month == 2){ // Special case for February
			boolean leapYear = isLeapYear(year);
			if(leapYear && day > 29){
				return "not valid, because February "+ year +" can only have 29 days.";
			}
			if(!leapYear && day > 28){
				return "not valid, because February "+ year +" can only have 28 days.";
			}
		}
		
		if(month%2==0 && day == 31  ){ // Even months cannot have 31 days.
			return "not valid, because " + month + " can only have 30 days.";
		}
		
		
		return "valid.";
	}

	private static boolean isLeapYear(int year) {
		if(year%400 == 0){
			return true;
		}
		if(year%4 == 0 && year%100 != 0){
			return true;
		}
		return false;
	}

}
