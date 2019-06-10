package aoc09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExplosivesInCyberspace {
	public static void main (String[] args) throws IOException {
		
		BufferedReader reader = new BufferedReader (new InputStreamReader (new FileInputStream("src/aoc09/compressed.txt"),"utf-8"));
		reader.readLine();
		String line = "";
		while ((line=reader.readLine())!=null) {
			System.out.println(decrypt(line));
			
		}
		reader.close();
		
	}
	
	public static long decrypt (String line) {
		long length = 0;
		System.out.println(line);
		for (int i=0;i<line.length();i++) {
			if (line.charAt(i)=='(') {
				
				Marker marker = new Marker (line.substring(i + 1, line.indexOf(")", i)));
				System.out.printf("i: %d marker: %d, %d, %d\n",i,marker.getLength(),marker.getMultiplier(),marker.getMarkerLength());
				i += marker.getMarkerLength();
				if (line.substring(i, i + marker.getLength()).contains("(")) {
					length += decrypt(line.substring(i, i + marker.getLength())) * marker.getMultiplier();
				}
				else {
				length += marker.getLength() * marker.getMultiplier();
				}
				i += marker.getLength()-1; //because "for" loop will increment i by 1 at the end of the loop
				
			}
			else
				length++;
			
			
			
		}
		
		
		return length;
		
	}
	
	public static int[] decryptMarker (String marker) {
		String[] arr = marker.split("x");
		
		return new int[] {Integer.parseInt(arr[0]), Integer.parseInt(arr[1]), marker.length()+1};
		
		
	}

}
