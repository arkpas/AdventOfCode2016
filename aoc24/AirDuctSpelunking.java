package aoc24;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;




public class AirDuctSpelunking {
	public static void main (String[] args) {
		
		Maze maze = null;
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("src/aoc24/maze.txt"), "utf-8"))) 
		{
			maze = new Maze(reader);
		}
		catch (IOException e) {e.printStackTrace();}
		System.out.println(maze);
		
		
		double time = System.currentTimeMillis();
		System.out.print("Part 1: ");
		System.out.println(visitAll(maze, false));
		System.out.print("Part 2: ");
		System.out.println(visitAll(maze, true));
		time = (System.currentTimeMillis() - time)/1000;
		System.out.printf("Time: %.4f", time);

	}
	
	public static int findShortestPath (Maze maze, Node source, Node target) {
		ArrayDeque<Node> queue = new ArrayDeque<>();
		HashSet<Integer> knownNodes = new HashSet<>();
		queue.add(source);
		knownNodes.add(source.hashCode());
		
		while (!queue.isEmpty()) {
			
			Node current = queue.pop();
			if (current.equals(target)) {
				return current.getSteps();
			}
			else {
				HashSet<Node> neighbours = new HashSet<>();
				neighbours.addAll(maze.getValidNeighbours(current));
				for (Node n : neighbours) {
					if (knownNodes.add(n.hashCode())) {
						queue.add(n);
						
					}
				}
			}	
		}
		return -1;
	}
	
	public static int visitAll (Maze maze, boolean part2) {

		Map<Integer, Node> objects = new HashMap<>();
		objects.putAll(maze.getObjects());
		int[][] distances = new int[objects.size()][objects.size()];
		
		for (int i = 0; i < distances.length; i++)
			for (int j = 0; j < distances[i].length; j++)
				distances[i][j] = findShortestPath (maze, objects.get(i), objects.get(j));
		
		StringBuilder builder = new StringBuilder();
		for (int i : objects.keySet()) {
			if (i != 0)
				builder.append(i);
		}
		HashSet<String> permutations = permute(builder.toString());
		int lowestSum = Integer.MAX_VALUE;
		for (String s : permutations) {
			int sum = 0;
			s = "0" + s;
			if (part2)
				s = s + "0";
			for (int i = 0; i < s.length() - 1; i++) {
				int from = Integer.parseInt(s.charAt(i) + "");
				int to = Integer.parseInt(s.charAt(i+1) + "");
				sum += distances[from][to];
			}
			if (lowestSum > sum)
				lowestSum = sum;
		}
		
		return lowestSum;
	}
	
	public static HashSet<String> permute (String str) {
		
		HashSet<String> permutations = new HashSet<>();
		if (str == null)
			return null;
		if (str.length() <= 1) {
			permutations.add(str);
			return permutations;
		}
		
		char firstChar = str.charAt(0);
		String rest = str.substring(1);

		for (String n : permute(rest)) {
			for (int i = 0; i <= n.length(); i++) {
				StringBuilder builder = new StringBuilder();
				builder.append(n);
				builder.insert(i, firstChar);
				permutations.add(builder.toString());
			}
		}
		return permutations;
	}
}
