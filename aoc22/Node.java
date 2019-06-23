package aoc22;

import java.util.ArrayList;
import java.util.List;

public class Node {
	
	private static int highestX = 0;
	private static int highestY = 0;
	
	private int x;
	private int y;
	private String filesystem = "";
	private int size;
	private int used;
	private int avail;
	private int usePercent;
	
	public Node () {
		super();
	}
	
	public Node (Node node) {
		this.x = node.x;
		this.y = node.y;
		this.size = node.size;
		this.used = node.used;
	}
	
	public Node (String data) {
		List<String> split = splitData(data);
		this.filesystem = split.get(0);
		extractPosition(this.filesystem);
		this.size = extractNumber(split.get(1));
		this.used = extractNumber(split.get(2));
		this.avail = extractNumber(split.get(3));
		this.usePercent = extractNumber(split.get(4));
	}
	
	private ArrayList<String> splitData (String data) {
		ArrayList<String> list = new ArrayList<>();
		String[] split = data.split(" ");
		for(int i = 0; i < split.length; i++) {
			if (split[i].length() > 1)
				list.add(split[i]);
		}
		return list;
	}
		
	private int extractNumber (String data) {
		int number = 0;
		try {
			data = data.replaceAll("\\D+","");
			number = Integer.parseInt(data);
		}
		catch (NumberFormatException e) {System.out.println("NFE"); e.printStackTrace();}
		return number;
	}
	
	private void extractPosition (String data) {
		int indexOfX = data.indexOf('x');
		int indexOfY = data.indexOf('y');
		String helperX = "";
		String helperY = "";
		
		helperX = data.substring(indexOfX, indexOfY);
		helperY = data.substring(indexOfY);
		try {
		this.x = Integer.parseInt(helperX.substring(1, helperX.length() -1));
		this.y = Integer.parseInt(helperY.substring(1));
		
		if (Node.highestX < this.x)
			Node.highestX = this.x;
		if (Node.highestY < this.y)
			Node.highestY = this.y;
		}
		catch (NumberFormatException e) {System.out.println("Error parsing position coords"); e.printStackTrace();}
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
	
	public int getX () {
		return this.x;
	}
	
	public int getY () {
		return this.y;
	}
	
	public int getUsed () {
		return this.used;
		}
	
	public int getSize () {
		return this.size;
	}
	
	public int getAvail () {
		return this.avail;
	}
	
	public static int getHighestX () {
		return Node.highestX;
	}
	
	public static int getHighestY () {
		return Node.highestY;
	}
	
	public void setX (int x) {
		this.x = x;
	}
	
	public void setY (int y) {
		this.y = y;
	}
	
	public void setUsed (int used) {
		this.used = used;
	}
	
	public boolean isEmpty () {
		return (this.used == 0 ? true : false);
			
	}
	
	@Override
	public String toString () {
		return (String.format("%s\t%d\t%d\t%d\t%d\t", filesystem, size, used, avail, usePercent));
	}
	
	public String getUsedAndSize () {
		if (this.used == 0)
			return "_____";
		return this.used + "/" + this.size;
	}
	
}
