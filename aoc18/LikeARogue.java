package aoc18;

public class LikeARogue {
	
	final static String pattern = "\\^\\^\\.|\\.\\^\\^|\\.\\.\\^|\\^\\.\\.";
	final static int rows = 400000;
	public static void main (String[] args) {
		String test = "..^^.";
		String input = ".^^..^...^..^^.^^^.^^^.^^^^^^.^.^^^^.^^.^^^^^^.^...^......^...^^^..^^^.....^^^^^^^^^....^^...^^^^..^";
		System.out.println(countSafePlaces(input));
		
	}
	
	public static int countSafePlaces (String input) {
		String[] maze = new String[rows];
		for (int i=0; i<maze.length;i++)
			maze[i]="";
		maze[0]=input;			
		int row = 1;
		while (row<rows) {
			String str = maze[row-1];
			maze[row]+=isTrap("."+str.substring(0,2));
			for (int i=1;i<str.length()-1;i++) {
				maze[row]+=isTrap(str.substring(i-1,i+2));
			}
			maze[row]+=isTrap(str.substring(str.length()-2)+".");
			row++;
		}
		
		
		return count(maze);
		
	}
	
	public static int count (String[] maze) {
		int counter = 0;
		for(String a : maze) {
			counter+=a.replaceAll("\\^", "").length();
		}
		return counter;
	}
	
	public static char isTrap (String str) {
		if (str.matches(pattern))
			return '^';
		return '.';
	}
}
