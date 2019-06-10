package aoc17;

public class Room {
	
	
	private int x;
	private int y;
	private String path = "";
	
	public Room (int x, int y) {
		this.x = x;
		this.y = y;
		
	}
	
	public Room (int x, int y, String path) {
		this.x = x;
		this.y = y;
		this.path = path;
		
	}
	
	public String getPath () {
		return path;
	}
	
	public int getX () {
		return x;
	}
	
	public int getY () {
		return y;
	}
	
	public boolean isValid(int x, int y) {
		
		if (this.x + x < TwoStepsForward.WIDTH && this.x + x >= 0 && this.y + y < TwoStepsForward.HEIGHT && this.y + y >= 0)
			return true;
		return false;
		
	}
	
	public Room moveBy (char a) {
		
		switch (a) {
		case 'U': return new Room(this.x, this.y-1, path+a );
		case 'D': return new Room(this.x, this.y+1, path+a );
		case 'L': return new Room(this.x-1, this.y, path+a );
		case 'R': return new Room(this.x+1, this.y, path+a );
		}
		return null;
	}
	
	public boolean compareRooms (Room room) {
		if (this.x == room.getX() && this.y == room.getY())
			return true;
		return false;
	}
	
}
