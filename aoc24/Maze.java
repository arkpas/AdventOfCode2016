package aoc24;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Maze {
	private char[][] maze;
	private Node zero;
	private Map<Integer, Node> objects;
	
	
	public Maze (BufferedReader reader) throws IOException {
		String line = "";
		List<char[]> list = new ArrayList<>();
		objects = new HashMap<>();
		
		while ((line = reader.readLine()) != null) {
			list.add(line.toCharArray());
			if (line.matches(".*[0-9].*")) {
				System.out.println("y");
				for (int i = 0; i < 10; i++) {
					if (line.contains(i+""))
						objects.put(i, new Node(list.size() - 1, line.indexOf(i+"")));
				}
			}
			
		}
		maze = new char[list.size()][list.get(0).length];
		maze = list.toArray(maze);
		
	}
	
	public Node getZero () { return zero; }
	
	public char getValue (int x, int y) {
		if (x < maze.length && y < maze[0].length && x >= 0 && y >= 0)
			return maze[x][y];
		return '!';
	}
	
	public List<Node> getValidNeighbours (Node node) {
		
		List<Node> list = new ArrayList<>();
		Node helper = null;
		
		helper = new Node(node);
		helper.setX(helper.getX() + 1);
		helper.stepUp();
		if (this.isValid(helper))
			list.add(helper);
		
		helper = new Node(node);
		helper.setX(helper.getX() - 1);
		helper.stepUp();
		if (this.isValid(helper))
			list.add(helper);
		
		helper = new Node(node);
		helper.setY(helper.getY() + 1);
		helper.stepUp();
		if (this.isValid(helper))
			list.add(helper);
		
		helper = new Node(node);
		helper.setY(helper.getY() - 1);
		helper.stepUp();
		if (this.isValid(helper))
			list.add(helper);
		
			
		return list;
	}
	
	public boolean isValid (Node node) {
		int x = node.getX();
		int y = node.getY();
		if (x >= 0 && x < maze.length && y >= 0 && y < maze[0].length)
			if (this.getValue(x, y) != '#')
				return true;
		return false;
	}
	
	public Map<Integer, Node> getObjects () {
		return Collections.unmodifiableMap(objects);
	}
	
	@Override
	public String toString () {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++)
				str.append(maze[i][j]);
		str.append("\n");
		}
		return str.toString();
	}
}
