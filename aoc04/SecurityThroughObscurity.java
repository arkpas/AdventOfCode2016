package aoc04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SecurityThroughObscurity {
	public static void main (String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("src/aoc04/rooms.txt"), "utf-8"));


		try {
			System.out.println("Sum of real rooms IDs: " + checkRooms(reader));
			
		}
		catch (IOException a) {System.out.println("IOException!!!");}
		
		reader.close();
	}
	
	public static long checkRooms (BufferedReader reader) throws IOException {
		long idSum = 0;
		String line = "";
		ArrayList<String> decryptedRooms = new ArrayList<>();
		while ((line=reader.readLine())!=null) {
			Room room = new Room(line);
			idSum+=room.validate(decryptedRooms);
		}
		
		for (String a : decryptedRooms) {
			if (a.contains("north"))
				System.out.println(a);
		}
		return idSum;
		
	}
 
}
