package aoc24;

public class Node {

	private int x;
	private int y;
	private int steps;
	
	public Node (Node other) {
		this(other.x, other.y, other.steps);
	}
	
	public Node (int x, int y) {
		this(x,y, 0);
	}
	
	public Node (int x, int y, int steps) {
		this.x = x;
		this.y = y;
		this.steps = steps;
	}
	
	public int getX () { return this.x; }
	
	public int getY () { return this.y; }
	
	public int getSteps () { return this.steps; }
	
	public void setX (int x) { this.x = x; }
	
	public void setY (int y) { this.y = y; }
	
	public void stepUp () { this.steps++; }
	
	@Override
	public boolean equals (Object other) {
		if (this == other)
			return true;
		if (other instanceof Node) {
			Node otherNode = (Node)other;
			if (this.x == otherNode.x && this.y == otherNode.y)
				return true;
		}
		return false;
	}
	
	@Override
	public String toString () {
		return this.x + "," + this.y;
	}
	
	@Override
	public int hashCode () {
		return this.toString().hashCode();
	}
}
