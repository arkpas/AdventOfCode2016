package aoc09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

public class ExplosivesInCyberspaceBAD {
	static int length = 0;
	
	public static void main (String[] args) throws IOException {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("src/aoc09/compressed2.txt"),"utf-8"));
		reader.readLine();
		decryptLength(reader);
		System.out.println(length);
		
		
		reader.close();
	}
	
	public static void decryptLength (BufferedReader reader) throws IOException {
		int currentRead = 0, countdown = 0;
		char currentChar;
		boolean bracket = false;
		String inBrackets = "";
		String recurse = "";
		boolean toRecurse = false;
		while ((currentRead=reader.read())!=-1) {
			currentChar = (char)currentRead;
			if (countdown>0) {
				countdown--;
				if (currentChar=='(')
					toRecurse = true;
		
				if (toRecurse)
					recurse+=currentChar+"";
			}	
			else if (countdown==0 && toRecurse) {
				toRecurse = false;
				decryptLength(new BufferedReader(new StringReader(recurse)));
				recurse = "";
			}
			else if (currentChar=='(' && bracket==false)
				bracket = true;
			else if (currentChar==')' && bracket==true) {
				bracket = false;
				countdown = getCountdown(inBrackets);
				inBrackets = "";
			}
			else if (bracket) 
				inBrackets+=currentChar+"";
			else if (!bracket)
				length++;
				
		}
		
	}
	
	public static int getCountdown (String str) {
		if (str.length()<3)
			return 0;
		String[] arr = str.split("x");
		length+=Integer.parseInt(arr[0])*Integer.parseInt(arr[1]);
		
		return Integer.parseInt(arr[0]);
		
	}

}
