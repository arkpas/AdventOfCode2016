package aoc17;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class TwoStepsForward {

	static final String PASSCODE = "qtetzkpl";
	static final int WIDTH = 4;
	static final int HEIGHT = 4;
	static final Room VAULT = new Room(3,3);
	
	public static void main (String[] args) {
		
		System.out.println(findVault());
		System.out.println(findVaultLongest());
	}
	
	public static String findVault () {
		ArrayList<Room> queue = new ArrayList<>();
		Room current = new Room(0,0);
		queue.add(current);
		
		while (!queue.isEmpty()) {
			
			current = queue.remove(0);
			if (current.compareRooms(VAULT))
				return current.getPath();
			
			queue.addAll(getNeighbours(current));
			
			
		}
	
		return null;
		
		
		
	}
	
	public static int findVaultLongest () {
		ArrayList<Room> queue = new ArrayList<>();
		Room current = new Room(0,0);
		queue.add(current);
		ArrayList<Room> routes = new ArrayList<>();
		
		while (!queue.isEmpty()) {
			
			
			current = queue.remove(0);
			if (current.compareRooms(VAULT))
				routes.add(current);
			else
				queue.addAll(getNeighbours(current));
			
			
			
			
			
			
		}
		int longest = 0;
		if (!routes.isEmpty()) {
			for (Room a : routes) {
				if(a.getPath().length() > longest)
					longest = a.getPath().length();
			}
		}
		return longest;
		
		
		
	}
	
	
	public static String getHash (String input) {
		
		try {
		MessageDigest md = MessageDigest.getInstance("md5");
		byte[] digest = md.digest(input.getBytes());
		BigInteger generator = new BigInteger(1,digest);
		return String.format("%032x", generator);
		}
		catch (NoSuchAlgorithmException e) {System.out.println("MessageDigest failed to get an instance!");}
		
		return null;
	
	}
	
	public static ArrayList<Room> getNeighbours (Room current) {
		
		ArrayList<Room> neighbours = new ArrayList<>();
		String hash = getHash(PASSCODE+current.getPath());
		
		if (isValid(hash.charAt(0)) && current.isValid(0, -1))
			neighbours.add(current.moveBy('U'));
		if (isValid(hash.charAt(1)) && current.isValid(0, 1))
			neighbours.add(current.moveBy('D'));
		if (isValid(hash.charAt(2)) && current.isValid(-1, 0))
			neighbours.add(current.moveBy('L'));
		if (isValid(hash.charAt(3)) && current.isValid(1, 0))
			neighbours.add(current.moveBy('R'));
		
		return neighbours;
			
	}
	
	public static boolean isValid (char a) {
		if ("bcdef".indexOf(a) != -1)
			return true;
		return false;
	}
	
	
}	
