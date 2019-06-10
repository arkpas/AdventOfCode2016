package aoc02;

public class Keypad {
	public static final char[][] KEYS = new char[][] {
		{'0','0','1','0','0'},
		{'0','2','3','4','0'},
		{'5','6','7','8','9'},
		{'0','A','B','C','0'},
		{'0','0','D','0','0'}
	};
	
	private int row = 1;
	private int column = 1;
	private String secretCode = "";
	
	public void up () {
		if(row>0 && KEYS[row-1][column]!='0')
			row--;
	}
	
	public void down () {
		if(row<4 && KEYS[row+1][column]!='0')
			row++;
	}
	
	public void right () {
		if(column<4 && KEYS[row][column+1]!='0')
			column++;
	}
	
	public void left () {
		if(column>0 && KEYS[row][column-1]!='0')
			column--;
	}
	
	public void save () {
		secretCode += KEYS[row][column];
	}
	
	public String getSecretCode () {
		return secretCode;
	}

}
