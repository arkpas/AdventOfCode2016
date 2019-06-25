package aoc25;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class ClockSignal {
	private static HashMap<Character, Integer> map = new HashMap<>();
	
	public static void main (String[] args){
		double time = System.currentTimeMillis();
		try (BufferedReader reader = new BufferedReader (new InputStreamReader (new FileInputStream("src/aoc25/instructions.txt"), "utf-8")))
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
			
			System.out.print("Part 1: ");
			map.put('a', 0);
			map.put('b', 0);
			map.put('c', 0);
			map.put('d', 0);
			
			int i = 0;
			map.put('a', i);
			while(!execute(instructions, 100000)) {
				i++;
				map.put('a', i);
			}
			System.out.println(i);
			
			
			
			time = System.currentTimeMillis() - time;
			System.out.printf("Time: %.4f", time/1000);
			}
		catch (IOException e) {e.printStackTrace();}

	}
	
	public static boolean execute (ArrayList<Instruction> instructions, int maxIterations) {
		int position = 0;
		int counter = 0;
		int previous = 1;
		while (position<instructions.size() && counter < maxIterations) {
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
				
				case "out": {
					int out = map.get(instruction.getChar1());
					if (out != 0 && out != 1)
						position = Integer.MAX_VALUE;
					else if (out != previous) {
						counter++;
						previous = out;
						position++;
					}
					else
						position = Integer.MAX_VALUE;
						
				}

			}

		}
		return counter == maxIterations ? true : false;
			
	}
		
		
}
