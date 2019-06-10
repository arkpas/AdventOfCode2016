package aoc08;

public class Screen {
	private int pixelCounter=0;
	private int rows;
	private int columns;
	private char[][] display;
	
	public Screen (int x, int y) {
		this.rows = y;
		this.columns = x;
		display = new char[rows][columns];
		for (int i=0;i<rows;i++)
			for (int j=0;j<columns;j++)
				display[i][j]='.';
	}
	
	public void getCommand (String command) {
		String[] splitted;
		
		if (command.contains("rect")) {
			splitted=command.split("rect |x");
			try {
			rect(Integer.parseInt(splitted[1]), Integer.parseInt(splitted[2]));
			}
			catch (NumberFormatException e) {System.out.println("Error converting numbers: "+splitted[1]+" and "+splitted[2]);};		
		}
	
		else if (command.contains("rotate row")) {
			splitted=command.split("rotate row y=| by ");
			try {
			rotateRow(Integer.parseInt(splitted[1]), Integer.parseInt(splitted[2]));
			}
			catch (NumberFormatException e) {System.out.println("Error converting numbers: "+splitted[1]+" and "+splitted[2]);};
		}
		
		else if (command.contains("rotate column")) {
			splitted=command.split("rotate column x=| by ");
			try {
			rotateColumn(Integer.parseInt(splitted[1]), Integer.parseInt(splitted[2]));
			}
			catch (NumberFormatException e) {System.out.println("Error converting numbers: "+splitted[1]+" and "+splitted[2]);};
		}
		else
			System.out.println("bad command");
		
	}
	
	private void rect (int x, int y) {
		if (x<=columns && y<=rows)
			for(int i=0;i<y;i++) {
				for(int j=0;j<x;j++)
					display[i][j]='#';
		}
	}
	
	private void rotateRow (int row, int distance) {
		if (distance==columns || distance<=0 || row>rows)
			return;
		if (distance>columns)
			distance=distance%columns;
		String newLine = "";
		for (int i=columns-distance;i<columns;i++) {
			newLine+=display[row][i];
		}
		for (int i=0;i<columns-distance;i++) {
			newLine+=display[row][i];
		}
		display[row]=newLine.toCharArray();
	}
	
	private void rotateColumn (int column, int distance) {
		if (distance==rows || distance<=0 || column>columns)
			return;
		if (distance>rows)
			distance=distance%rows;
		String newLine = "";
		for (int i=rows-distance;i<rows;i++) {
			newLine+=display[i][column];
		}
		for (int i=0;i<rows-distance;i++) {
			newLine+=display[i][column];
		}
		for(int i=0;i<rows;i++) {
			display[i][column]=newLine.charAt(i);
		}
		
		
	}
	
	public void display () {
		for (int i=0;i<display.length;i++) {
			for (int j=0;j<display[0].length;j++) {
				System.out.print(display[i][j]);
				if(display[i][j]=='#')
					pixelCounter++;
			}
		System.out.println("");
		}
		System.out.println("\n"+pixelCounter);
	}
	


}
