package uk.ac.cam.ct538.prejava.ex1;

import java.util.Scanner;

public class TestBit {
	   public static void main(String[] args) {
	      long currentValue = Long.decode(args[0]);
	      int position = Integer.parseInt(args[1]);
	      boolean value = PackedLong.get(currentValue, position);
	      System.out.println(value);
	      

	   }
	}
