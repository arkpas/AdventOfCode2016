package aoc22;

import java.util.ArrayList;
import java.util.List;

public class Node {

	String filesystem = "";
	int size;
	int used;
	int avail;
	int usePercent;
	
	public Node (String data) {
		List<String> split = splitData(data);
		filesystem = split.get(0);
		size = extractNumber(split.get(1));
		used = extractNumber(split.get(2));
		avail = extractNumber(split.get(3));
		usePercent = extractNumber(split.get(4));
	}
	
	public ArrayList<String> splitData (String data) {
		ArrayList<String> list = new ArrayList<>();
		String[] split = data.split(" ");
		for(int i = 0; i < split.length; i++) {
			if (split[i].length() > 1)
				list.add(split[i]);
		}
		return list;
	}
		
	public int extractNumber (String data) {
		int number = 0;
		try {
			data = data.replaceAll("\\D+","");
			number = Integer.parseInt(data);
		}
		catch (NumberFormatException e) {System.out.println("NFE");}
		return number;
	}
	
	public int howManyFits (Node other) {
		int count = 0;
		if (this.used <= other.avail && this.used > 0) {
			count++;
		}
		if (other.used <= this.avail && other.used > 0) {
			count++;
		}
		return count;
	}
	
	@Override
	public String toString () {
		return (String.format("%s\t%d\t%d\t%d\t%d\t", filesystem, size, used, avail, usePercent));
	}
	
}
