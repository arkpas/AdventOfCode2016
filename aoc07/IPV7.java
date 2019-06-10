package aoc07;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.IOException;

public class IPV7 {
	static int TLScounter=0;
	static int SSLcounter=0;
	
	public static void main (String[] args) throws IOException{
	
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("src/aoc07/ips.txt"),"utf-8"));
		getInput(reader);
		
		System.out.println("TLS: " + TLScounter + "\nSSL: " + SSLcounter);
	}
	
	public static void getInput (BufferedReader reader) throws IOException{
		reader.readLine();
		String line;
		while ((line=reader.readLine())!=null) {
			validateTLS(line);
			validateSSL(line);
		}
		
	}
	
	public static void validateTLS (String str) {
		char[] arr = str.toCharArray();
		boolean bracket = false;
		boolean isValid = false;
		for (int i=0;i<arr.length-3;i++) {
			if (arr[i]=='[')
				bracket=true;
			else if (arr[i]==']')
				bracket=false;
			else if (arr[i]==arr[i+3] && arr[i+1]==arr[i+2] && arr[i]!=arr[i+1]) {
				if (bracket==true) {
					isValid=false;
					break;
				}
				else
					isValid=true;
			}
		}
		if(isValid)
			TLScounter++;
	}
	
	public static void validateSSL (String str) {
		char[] arr = str.toCharArray();
		boolean bracket = false;
		ArrayList<String> inBrackets = new ArrayList<>();
		ArrayList<String> outBrackets = new ArrayList<>();
		for (int i=0;i<arr.length-2;i++) {
			if (arr[i]=='[')
				bracket=true;
			else if (arr[i]==']')
				bracket=false;
			else if (arr[i]==arr[i+2] && arr[i]!=arr[i+1]) {
				if (bracket==true) {
					inBrackets.add("" + arr[i+1] + arr[i] + arr[i+1]);
				}
				else
					outBrackets.add("" + arr[i] + arr[i+1] + arr[i]);
			}
		}
		
		for (String a : inBrackets) {
			if(outBrackets.contains(a)) {
				SSLcounter++;
				break;
			}
			
		}
			
	}
}
