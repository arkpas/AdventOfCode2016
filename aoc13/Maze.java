package aoc13;

public class Maze {
	
	static final int DESIGNERS_NUMBER = 1352;
	
	public static void main (String[] args) {
		
		buildMaze(50,50);
	}
	
	public static void buildMaze (int width, int height) {
		char[][] maze = new char[height][width];
		for (int y=0;y<maze.length;y++) {
			for (int x=0;x<maze[0].length;x++) {

				long result = (x*x) + (3*x) + (2*x*y) + y + (y*y) + DESIGNERS_NUMBER;
				if (x==31 && y==39)
					maze[y][x] = '0';
				else if (Long.bitCount(result)%2==0)
					maze[y][x] = '.';
				else
					maze[y][x] = '#';
				System.out.print(maze[y][x] + " ");
			}
			System.out.println();
		}
		
	}
}
