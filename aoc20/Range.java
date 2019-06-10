package aoc20;

public class Range implements Comparable<Range> {

	private static int allowedCount = 0;
	private long from = 0;
	private long to = 0;
	
	
	public Range () {
		super();
	}
	public Range (String input) {
		try {
			int dash = input.indexOf("-");
			if (dash>0) {
				from = Long.parseLong(input.substring(0, dash));
				to = Long.parseLong(input.substring(dash+1));
			
			}
		}
		catch (NumberFormatException e) {
			System.out.println("NFE!!!" + input);
			from = -1;
			to = -1;
		};
		
	}
	
	public long from () {
		return from;
	}
	
	public long to () {
		return to;
	}
	
	public void setFrom (int value) {
		from = value;
	}
	
	public void setTo (int value) {
		to = value;
	}
	
	public boolean extendRange (Range range) {
		if (this.to + 1 >= range.from) {
			if (this.to < range.to)
				this.to = range.to;
			return true;
		}
		else {
			allowedCount++;
			this.to++;
			return false;
			
		}
	}
	
	
	public int compareTo(Range other) {
		return Long.compare(this.from, other.from);
	}
	
	public String toString() {
		return from + "-" + to;
	}
	
	public static int allowedCount () {
		return allowedCount;
	}
}
