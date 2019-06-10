package aoc03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class SquaresWithThreeSides {
	public static void main (String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("src/aoc03/triangles.txt"),"utf-8"));
		try {
		System.out.println(checkTriangles(reader));
		}
		catch (IOException a) {System.out.println("IOEXCEPTION");}; 
		
	}
	
	private static int howManyValid (int[][] triangle) {
		int counter=0;
		for (int i=0;i<3;i++) {
			int a = triangle[0][i] , b = triangle[1][i], c = triangle[2][i];
			int helper;
			if (c<a) {
				helper = c;
				c = a;
				a = helper;
			}
			if (c<b) {
				helper = c;
				c = b;
				b = helper;
			}
			if(a+b>c)
				counter++;
		}
		return counter;
		
	}
	
	private static int[] getLengths (String line) throws IndexOutOfBoundsException {
		int[] triangle = new int[3];
		int index=0;
		String[] splitted = line.split("\\D+");
		for (String a : splitted) {
			if(!a.equals("")) {
				try {
				triangle[index]=Integer.parseInt(a);
				index++;
				}
				catch (NumberFormatException b) {System.out.println("Blad!!! Nie mozna zmienic na liczbe:" + a);};
			}
		}
		
		return triangle;
	}
	
	public static int checkTriangles (BufferedReader reader) throws IOException{
		int triangleCounter=0;
		String line1 = "";
		String line2 = "";
		String line3 = "";
		while((line1=reader.readLine())!=null && (line2=reader.readLine())!=null && (line3=reader.readLine())!=null ) {
			triangleCounter+=howManyValid(new int[][] {getLengths(line1),getLengths(line2),getLengths(line3)});
		}
		return triangleCounter;
	}

	

}
