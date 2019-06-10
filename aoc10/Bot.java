package aoc10;

public class Bot {
	int number;
	int value1 = -1;
	int value2 = -1;
	
	public Bot (int number) {
		this.number = number;
	}
	
	public void addValue (int value) {
		if(value1<0)
			value1 = value;
		else if (value2<0)
			value2 = value;
		else
			System.out.println("Error! Has 2 values already!");
		
		if (value1>value2) {
			int p = value1;
			value1=value2;
			value2=p;
		}
	}
	
	public boolean canTrade () {
		return (value1>-1 && value2>-1 ? true : false);
	}
	
	public int giveLow () {
		return value1;
	}
	
	public int giveHigh() {
		return value2;
	}
	
	public void clearValues(int wanted1, int wanted2) {
		if (value1==wanted1 && value2==wanted2)
			System.out.println("Found: " + number);
			
		value1 = -1;
		value2 = -1;
	}
}
