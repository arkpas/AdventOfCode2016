package aoc06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SignalsAndNoise {
	public static void main (String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("src/aoc06/input.txt"),"utf-8"));
		String[] inputArr = new String[] {"","","","","","","",""};
		readFile(reader, inputArr);
		String password = "";
		for (String str : inputArr) {
			password += getCommonLetter(createMap(str));
		}
		System.out.println(password);
		reader.close();
	}
	
	public static void readFile (BufferedReader reader, String[] inputArr) throws IOException{
		String line;
		int index;
		line=reader.readLine();
		while ((line=reader.readLine())!=null) {
			index = 0;
			for (char a : line.toCharArray()) {
				if (index<inputArr.length) {
					inputArr[index] += a;
					index++;
				}
			}
		}
	}
	
	public static HashMap<Character, Integer> createMap (String line) {
		HashMap<Character, Integer> map = new HashMap<>();
		
		
		char[] arr = line.toCharArray();
		Arrays.sort(arr);
		int counter = 0;
		char letter = arr[0];
		for (char a : arr) {
			if (letter == a)
				counter++;
			else {
				map.put(letter, counter);
				counter=1;
				letter=a;
			}
		}
		map.put(letter, counter);
		
		return map;
	}
	
	public static char getCommonLetter (HashMap<Character, Integer> map) {
		int highest=Integer.MAX_VALUE;
		char letter='0';
		
		for (Map.Entry<Character, Integer> entry : map.entrySet()) {
			if (highest>entry.getValue()) {
				highest=entry.getValue();
				letter=entry.getKey();
			}
		}
		
		return letter;
		
	}
}
