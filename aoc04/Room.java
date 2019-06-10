package aoc04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class Room {
	private String name = "";
	private String nameWithDashes = "";
	private int id;
	private String checksum = "";
	
	public Room (String roomData) {
		String[] splitted = roomData.split("\\[");
		String newData = "";
		this.checksum = splitted[splitted.length-1];
		this.checksum = this.checksum.substring(0, this.checksum.length()-1);
		for (int i=0;i<splitted.length-1;i++) {
			newData+=splitted[i];
		}
		splitted = newData.split("-");
		this.id = Integer.parseInt(splitted[splitted.length-1]);
		for (int i=0;i<splitted.length-1;i++) {
			this.nameWithDashes += splitted[i] + "-";
			this.name += splitted[i];
		}
		this.nameWithDashes=this.nameWithDashes.substring(0,this.nameWithDashes.length()-1);
	
		
	}
	
	public void viewData () {
		System.out.printf("Name of the room: %s\nId: %d\nChecksum: %s", name, id, checksum);
	}
	
	public int validate (ArrayList<String> decryptedRooms) {
		char[] nameArray = name.toLowerCase().toCharArray();
		Arrays.sort(nameArray);
		checksum=checksum.toLowerCase();
		LinkedHashMap<Character , Integer> letters = new LinkedHashMap<>();
		
		int counter=0;
		char letter=nameArray[0];
		for (char a : nameArray) {
			if (letter==a)
				counter++;
			else {
				letters.put(letter, counter);
				counter=1;
				letter=a;
			}
		}
		letters.put(letter, counter);
		
		String realChecksum = "";
		int highest = 0;
		letter = '?';
		for (int i = 0; i<checksum.length(); i++) {
			for (Map.Entry<Character, Integer> entry : letters.entrySet()) {
				if (highest<entry.getValue()) {
					highest = entry.getValue();
					letter = entry.getKey();
				}
			}
			realChecksum+=letter;
			letters.remove(letter);
			highest=0;
			letter = '?';
		}
		if (realChecksum.equals(checksum)) {
			decrypt(decryptedRooms);
			return id;
		}	
		else
			return 0;
		
		
	}
	
	private void decrypt (ArrayList<String> decryptedRooms) {
		char[] nameArray = nameWithDashes.toLowerCase().toCharArray();
		String decryptedName = "";
		int key = this.id%26;
		for (char a : nameArray) {
			if (a=='-')
				decryptedName += " ";
			else if ((int)a + key > (int)'z')
				decryptedName += (char)((int)a + key - 26);
			else
				decryptedName += (char)((int)a + key);
			
		}
		decryptedRooms.add(decryptedName + ":" + id);
		
	}
	
	
	
}
