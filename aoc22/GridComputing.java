package aoc22;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GridComputing {
	public static void main (String[] args) throws IOException {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("src/aoc22/nodes.txt"), "utf-8"));
		reader.readLine(); reader.readLine(); reader.readLine();
		
		List<Node> nodes = getNodes(reader);
		for (Node n : nodes)
			System.out.println(n.toString());
		
		System.out.println(countPairs(nodes));
	
		
		
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
