package aoc25;

public class Instruction {
	private String arg0 = "";
	private char charArg1;
	private char charArg2;
	private int intArg1;
	private int intArg2;
	
	public Instruction (String instruction) {
		String[] split = instruction.split(" ");
		arg0 = split[0];
		charArg1 = split[1].charAt(0);
		if (!Character.isLetter(charArg1))
			intArg1 = Integer.parseInt(split[1]);
		if (split.length > 2) {
			charArg2 = split[2].charAt(0);
			if (!Character.isLetter(charArg2))
				intArg2 = Integer.parseInt(split[2]);
		}
	}
	
	public String getCommand () {
		return arg0;
	}
	
	public int getInt1 () {
		return intArg1;
	}
	
	public int getInt2 () {
		return intArg2;
	}
	
	public char getChar1 () {
		return charArg1;
	}
	
	public char getChar2 () {
		return charArg2;
	}
	
	public boolean isArg1Letter () {
		return Character.isLetter(charArg1);
	}
	
	public boolean isArg2Letter () {
		return Character.isLetter(charArg2);
	}
	
	public String toString () {
		StringBuilder str = new StringBuilder();
		str.append(arg0 + " ");
		if (isArg1Letter())
			str.append(charArg1 + " ");
		else
			str.append(intArg1 + " ");
		
		if (isArg2Letter())
			str.append(charArg2);
		else
			str.append(intArg2);
		
		return str.toString();
	}
}
