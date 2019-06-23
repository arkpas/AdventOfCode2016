package aoc22;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class GridComputing {
	public static void main (String[] args) throws IOException {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("src/aoc22/nodes.txt"), "utf-8"));
		reader.readLine(); reader.readLine(); reader.readLine();
		
		List<Node> nodes = getNodes(reader);
		
		Grid grid = new Grid(nodes);
		Grid winner = moveEmptyNearTarget(grid);
		System.out.println(winner);
		System.out.println("Part 1: Pair count: " + countPairs(nodes));
		System.out.println("Part 2: Steps to move target data to left upper corner: " + (winner.getStepsToMoveEmpty() + 1 + ((Node.getHighestX()-1) * 5)));
		/*
		 *  little explanation here:
		 *	when we move empty data to the left side of the target data, we can use formula: (current steps + (5*(X-1)) + 1), where X is number of data cells in each row
		 *	moving target data by one to the left require us to do 5 moves, because we have to move empty cell around target cell, which takes 4 moves and then move target cell to empty cell = 5 moves
		*/
		
		
	}
	
	public static Grid moveEmptyNearTarget (Grid source) {
		ArrayDeque<Grid> queue = new ArrayDeque<Grid>();
		HashSet<String> knownCombinations = new HashSet<>();
		queue.add(source);
		knownCombinations.add(source.emptyPos());
		while (!queue.isEmpty()) {
			Grid current = queue.pop();
			if (current.isEmptyNearTarget())
				return current;
			List<Grid> possibleMoves = new ArrayList<>();
			possibleMoves.addAll(current.possibleMoves());
			for (Grid g : possibleMoves) {
				if (knownCombinations.add(g.emptyPos()))
						queue.add(g);
			}
		}
		return null;
	}
	
	public static List<Node> getNodes (BufferedReader reader) throws IOException {
		List<Node> nodes = new ArrayList<>();
		String data = "";
		while ((data = reader.readLine()) != null) {
			nodes.add(new Node(data));
		}
		
		return nodes;
	}
	
	public static int countPairs (List<Node> nodes) {
		int count = 0;
		for (int i = 0; i < nodes.size() -1; i++) {
			Node current = nodes.get(i);
			for (int j=i+1; j < nodes.size(); j++)
				count += current.howManyFits(nodes.get(j));
		}
		return count;
	}
	

}
