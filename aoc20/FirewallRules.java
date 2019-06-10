package aoc20;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class FirewallRules {
	
	public static final Range LAST_IP = new Range("4294967295-4294967295");

	public static void main (String[] args) throws IOException {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("src/aoc20/ips.txt"),"utf-8"));
		reader.readLine();
		System.out.println(getLowest(reader));
	}
	
	public static long getLowest (BufferedReader reader) throws IOException {
		ArrayList<Range> ranges = new ArrayList<>();
		String input = "";
		Range global = null;
		while ((input = reader.readLine())!=null) {
			Range inputRange = new Range(input);
			System.out.println(inputRange);	
			if (inputRange.from()==0)
				global = new Range(input);
			else
				ranges.add(new Range(input));
		}
		ranges.add(LAST_IP);
		if (global == null)
			return 0;
		
		Collections.sort(ranges);
		
		while (!ranges.isEmpty()) {
			System.out.println("Global: " + global.toString());
			if (global.extendRange(ranges.get(0)))
				ranges.remove(0);
		}
		
		return Range.allowedCount();
		
	}

		
	
}
