package aoc15;

public class Disc {
	
	int maxPosition;
	int position;
	
	
	public Disc (int position, int maxPosition) {
		this.position = position;
		this.maxPosition = maxPosition;
		
	}

	public int getTimeAtZero () {
		
		return (position==0 ? maxPosition : (maxPosition-position));
	}
	
	public boolean isZero (int time) {
		
		return (position+time)%maxPosition==0 ? true : false;
		
	}
}


//Disc #6 has 5 positions; at time=0, it is at position 0.