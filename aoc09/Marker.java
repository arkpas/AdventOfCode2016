package aoc09;

public class Marker {
	private int length;
	private int multiplier;
	private int markerLength;
	
	public Marker (String marker) {
		
		String[] arr = marker.split("x");
		try {
			length = Integer.parseInt(arr[0]);
			multiplier = Integer.parseInt(arr[1]);
			markerLength = marker.length()+2; // adding +2 because brackets of the marker are not passed in string
		}
		catch (NumberFormatException e) {System.out.println("ERROR PARSING INT");};
	}
	
	public int getLength () {
		return length;
	}
	
	public int getMultiplier () {
		return multiplier;
	}
	
	public int getMarkerLength () {
		return markerLength;
	}
	
	
}
