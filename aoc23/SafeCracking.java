package aoc23;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class SafeCracking {
	private static HashMap<Character, Integer> map = new HashMap<>();
	
	public static void main (String[] args){
		double time = System.currentTimeMillis();
		try (BufferedReader reader = new BufferedReader (new InputStreamReader (new FileInputStream("src/aoc23/instructions.txt"), "utf-8")))
			{
		
			reader.mark(1);
			if ((int)reader.read()<200)
				reader.reset();
			
			String line = "";
			List<Instruction> instructions = new ArrayList<>();
			List<Instruction> instructions2 = new ArrayList<>();
			
			while ((line=reader.readLine())!=null) {
				instructions.add(new Instruction(line));
				instructions2.add(new Instruction(line));
			}
			
			System.out.println("Part 1: ");
			map.put('a', 7);
			map.put('b', 0);
			map.put('c', 0);
			map.put('d', 0);
			execute(instructions);
			
			System.out.println("Part 2: ");
			map.put('a', 12);
			map.put('b', 0);
			map.put('c', 0);
			map.put('d', 0);
			execute(instructions2);
			
			
			time = System.currentTimeMillis() - time;
			System.out.printf("Time: %.4f", time/1000);
			}
		catch (IOException e) {e.printStackTrace();}

	}
	
	public static void execute (List<Instruction> instructions) {
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
						if (map.get(instruction.getChar1()) != 0) {
							if (instruction.isArg2Letter())
								position+=map.get(instruction.getChar2());
							else
								position+=instruction.getInt2();
						}
						else
							position++;
					}
					else 
						if (instruction.getInt1() != 0) {
							if (instruction.isArg2Letter())
								position+=map.get(instruction.getChar2());
							else
								position+=instruction.getInt2();
						}
						else 
							position++;
					break;
				}
				case "tgl": {
					instructions = toggleInstructions(instructions);
					position++;
					break;
				}

			}

		}
		System.out.println("A: " + map.get('a'));
			
	}
	
	public static List<Instruction> toggleInstructions (List<Instruction> instructions) {
		List<Instruction> toggled = new ArrayList<>();
		toggled.addAll(Collections.unmodifiableList(instructions));
		for (int i = 0; i < toggled.size(); i++) {
			if (toggled.get(i).getCommand().equals("tgl")) {
				int value = map.get(toggled.get(i).getChar1());
				if (i + value >= 0 && i + value < toggled.size()) {
					Instruction toToggle = toggled.get(i + value);
					switch (toToggle.getCommand()) {
						case "inc": {
							toToggle.setCommand("dec");
							break;
						}
						case "dec": {
							toToggle.setCommand("inc");
							break;
						}
						case "cpy": {
							toToggle.setCommand("jnz");
							break;
						}
						case "jnz": {
							toToggle.setCommand("cpy");
							break;
						}
						case "tgl": {
							toToggle.setCommand("inc");
							break;
						}
					}
				}
			}
		}
		
		return toggled;
		
	}
		
		
}
