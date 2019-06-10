package aoc08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class TwoFactorAuthentication {
	public static void main (String[] args) throws IOException {
		Screen screen = new Screen(50,6);
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("src/aoc08/commands.txt"),"utf-8"));
		String command;
		reader.read();
		while ((command=reader.readLine())!=null) {
			screen.getCommand(command);
		}
		screen.display();
		reader.close();
	
		
	}
}
