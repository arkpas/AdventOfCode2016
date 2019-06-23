package aoc12;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class LeonardosMonorail {
	private static HashMap<Character, Integer> map = new HashMap<>();
	
	public static void main (String[] args){
		double time = System.currentTimeMillis();
		try (BufferedReader reader = new BufferedReader (new InputStreamReader (new FileInputStream("src/aoc12/instructions.txt"), "utf-8")))
			{
		
			reader.mark(1);
			if ((int)reader.read()<200)
				reader.reset();
			
			String line = "";
			ArrayList<Instruction> instructions = new ArrayList<>();
			
			while ((line=reader.readLine())!=null) {
				Instruction ins = new Instruction(line);
				instructions.add(ins);
			}
			
			System.out.println("Part 1: ");
			map.put('a', 0);
			map.put('b', 0);
			map.put('c', 0);
			map.put('d', 0);
			execute(instructions);
			
			System.out.println("Part 2: ");
			map.put('a', 0);
			map.put('b', 0);
			map.put('c', 1);
			map.put('d', 0);
			
			execute(instructions);
			
			time = System.currentTimeMillis() - time;
			System.out.printf("Time: %.4f", time/1000);
			}
		catch (IOException e) {e.printStackTrace();}

	}
	
	public static void execute (ArrayList<Instruction> instructions) {
		int position = 0;
		
		while (position<instructions.size()) {
			Instruction instruction = instructions.get(position);

			switch (instruction.getCommand()) {
				case "inc": {
					map.put(instruction.getChar1(), map.get(instruction.getChar1())+1);
					position++;
					break;
				}
				
				case "dec": {
					map.put(instruction.getChar1(), map.get(instruction.getChar1())-1);
					position++;
					break;
				}
				case "cpy": {
					if (instruction.isArg1Letter())
						map.put(instruction.getChar2(),map.get(instruction.getChar1()));
					else 
						map.put(instruction.getChar2(),instruction.getInt1());
					position++;
					break;
				}
				
				case "jnz": {
					if (instruction.isArg1Letter()) {
						if (map.get(instruction.getChar1())>0)
							position+=instruction.getInt2();
						else
							position++;
					}
					else 
						if (instruction.getInt1()>0)
							position+=instruction.getInt2();
						else 
							position++;
					break;
				}

			}

		}
		System.out.println("A: " + map.get('a'));
			
	}
		
		
}
