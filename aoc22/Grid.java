package aoc22;

import java.util.ArrayList;
import java.util.List;

public class Grid {
	
	private Node[][] grid;
	private Node empty = null;
	private Node target = null;
	private int stepsToMoveEmpty;
	
	
	public Grid () {
		this.grid = new Node[Node.getHighestY() + 1][Node.getHighestX() + 1];
	}
	
	public Grid (List<Node> nodes) {
		
		this.grid = new Node[Node.getHighestY() + 1][Node.getHighestX() + 1];
		for (Node n : nodes) {
			grid[n.getY()][n.getX()] = n;
			if (n.isEmpty())
				empty = n;
		}
		target = grid[0][Node.getHighestX()];
	}
	
	
	public Grid copyGrid () {
		Grid newGrid = new Grid();
		for (int y = 0; y < grid.length; y++) {
			for (int x = 0; x < grid[0].length; x++ ) {
				Node n = new Node(this.grid[y][x]);
				newGrid.grid[y][x] = n;
				if (n.isEmpty())
					newGrid.empty = n;
				
			}
		}
		newGrid.target = new Node(this.target);
		newGrid.stepsToMoveEmpty = this.stepsToMoveEmpty;
		
		return newGrid;
	}
	
	public List<Grid> possibleMoves () {
		List<Grid> allMoves = new ArrayList<>();
		Grid newGrid;
		if (canMoveUp(empty)) {
			newGrid = this.copyGrid();
			newGrid.moveEmptyUp();
			allMoves.add(newGrid);
		}
		if (canMoveDown(empty)) {
			newGrid = this.copyGrid();
			newGrid.moveEmptyDown();
			allMoves.add(newGrid);
		}
		if (canMoveLeft(empty)) {
			newGrid = this.copyGrid();
			newGrid.moveEmptyLeft();
			allMoves.add(newGrid);
		}
		if (canMoveRight(empty)) {
			newGrid = this.copyGrid();
			newGrid.moveEmptyRight();
			allMoves.add(newGrid);
		}
		return allMoves;
	}
	
	private void moveEmptyUp () {
			Node nodeWithData = grid[empty.getY() - 1][empty.getX()];
			empty.setUsed(nodeWithData.getUsed());
			nodeWithData.setUsed(0);
			empty = nodeWithData;	
			stepsToMoveEmpty++;
	}
	
	private void moveEmptyDown () {
			Node nodeWithData = grid[empty.getY() + 1][empty.getX()];
			empty.setUsed(nodeWithData.getUsed());
			nodeWithData.setUsed(0);
			empty = nodeWithData;	
			stepsToMoveEmpty++;
	}
	
	private void moveEmptyLeft () {
			Node nodeWithData = grid[empty.getY()][empty.getX() - 1];
			empty.setUsed(nodeWithData.getUsed());
			nodeWithData.setUsed(0);
			empty = nodeWithData;
			stepsToMoveEmpty++;
	}
	
	private void moveEmptyRight () {
			Node nodeWithData = grid[empty.getY()][empty.getX() + 1];
			empty.setUsed(nodeWithData.getUsed());
			nodeWithData.setUsed(0);
			empty = nodeWithData;
			stepsToMoveEmpty++;
	}
	
	private boolean canMoveUp (Node node) {
		if (node.isEmpty() && node.getY() > 0) {
			Node targetNode = grid[node.getY() - 1][node.getX()];
			if (targetNode.getUsed() <= node.getSize())
				return true;
		}
		return false;
	}
	
	private boolean canMoveDown (Node node) {
		if (node.isEmpty() && node.getY() < Node.getHighestY()) {
			Node targetNode = grid[node.getY() + 1][node.getX()];
			if (targetNode.getUsed() <= node.getSize())
				return true;
		}
		return false;
	}
	
	private boolean canMoveLeft (Node node) {
		if (node.isEmpty() && node.getX() > 0) {
			Node targetNode = grid[node.getY()][node.getX() - 1];
			if (targetNode.getUsed() <= node.getSize())
				return true;
		}
		return false;
	}
	
	private boolean canMoveRight (Node node) {
		if (node.isEmpty() && node.getX() < Node.getHighestX()) {
			Node targetNode = grid[node.getY()][node.getX() + 1];
			if (targetNode.getUsed() <= node.getSize())
				return true;
		}
		return false;
	}
	
	public boolean isEmptyNearTarget() {
		if (empty.getX() == target.getX() - 1 && empty.getY() == target.getY())
			return true;
		return false;
	}
	
	public String emptyPos () {
		return empty.getX() + ":" + empty.getY();
	}
	
	public int getStepsToMoveEmpty () {
		return stepsToMoveEmpty;
	}
	
	@Override
	public String toString () {
		StringBuilder result = new StringBuilder();
		for (int y = 0; y < grid.length; y++) {
			for (int x = 0; x < grid[y].length; x++ ) {
				result.append(String.format("%7s ", grid[y][x].getUsedAndSize()));
			}
			result.append("\n\n");
		}
		return result.toString();
	}
	
	@Override 
	public int hashCode () {
		return this.toString().hashCode();
	}
	
	
}
