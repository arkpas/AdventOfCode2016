package aoc21;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ScrambledLetters {
	public static void main (String[] args) throws IOException{
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("src/aoc21/instructions.txt"), "utf-8"));
		reader.readLine();
		Password pass = new Password("abcdefgh");
		
		scramble(pass, reader);
		System.out.println(pass.toString());
		reader.close();
		
		reader = new BufferedReader(new InputStreamReader(new FileInputStream("src/aoc21/instructions.txt"), "utf-8"));
		reader.readLine();
		Password pass2 = new Password("fbgdceah");
		unscramble(pass2, reader);
		System.out.println(pass2.toString());
		reader.close();
	}
	
	public static void scramble (Password password, BufferedReader reader) throws IOException {
		String instruction = "";
		while ((instruction = reader.readLine()) != null) {

			password.doInstruction(instruction);

		}
	}
	
	public static void unscramble (Password password, BufferedReader reader) throws IOException {
		String instruction = "";
		List<String> instructions = new ArrayList<>();
		while ((instruction = reader.readLine()) != null) {
				
			instructions.add(instruction);
		}
		
		while (!instructions.isEmpty()) {
			password.doInstructionReverse(instructions.remove(instructions.size()-1));
		}
		
		
		
	} 

}
