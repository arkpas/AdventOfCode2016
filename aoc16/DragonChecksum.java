package aoc16;

import java.util.Arrays;
import java.util.Collections;

public class DragonChecksum {
	
	static final int LENGTH = 35651584;
	
	public static void main (String[] args) {
		
		String input = "10001110011110000";
		
		System.out.println(getChecksum(fillDisc(input)));		
		
	}
	
	public static String fillDisc (String input) {
		
		while (input.length()<LENGTH) {
			input = dragonCurve(input);
		}
		
		return input.substring(0, LENGTH);
	}
	
	public static String dragonCurve (String input) {
		
		StringBuilder reversed = new StringBuilder(input);
		reversed.reverse();
		String secondPart = reversed.toString();
		secondPart=secondPart.replaceAll("1", "z");
		secondPart=secondPart.replaceAll("0", "1");
		secondPart=secondPart.replaceAll("z", "0");
		
		
		return input+"0"+secondPart;
	}
	
	public static String getChecksum (String input) {
		
		
		do {
			StringBuilder checksum = new StringBuilder();
			for (int i=0;i<input.length();i+=2) {
				if (input.charAt(i)==input.charAt(i+1))
					checksum.append(1);
				else
					checksum.append(0);
			}
			input=checksum.toString();
		}
		while (input.length()%2==0);
		
		return input;
	}
}
