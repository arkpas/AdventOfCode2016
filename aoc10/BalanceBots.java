package aoc10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BalanceBots {
	public static void main (String[] args) throws IOException{
		String str = "bot 0 gives low to output 2 and high to output 0";
		System.out.println(str.substring(4,str.indexOf(" gives")));
		BufferedReader reader = new BufferedReader (new InputStreamReader (new FileInputStream("src/aoc10/bots.txt"), "utf-8"));
		ArrayList<String> instructionList = listInstructions(reader);
		try {
			getInstructions(instructionList);
		}
		catch (NumberFormatException nfexc) {System.out.println("Error parsing string");}
		catch (IndexOutOfBoundsException ioobexc) {System.out.println("IOOB!!!");};
		
		
	}
	
	public static void getInstructions (ArrayList<String> instructionList) throws NumberFormatException {
		Bot[] bots = new Bot[countBots(instructionList)];
		int[] outputs = new int[30];
		for (int i = 0; i<bots.length; i++)
			bots[i] = new Bot(i);
			
		while (!instructionList.isEmpty()) {
			for (String instruction : instructionList) {
				if (instruction.startsWith("value")) {
					int value, bot;
					value = Integer.parseInt(instruction.substring(6, instruction.indexOf(" goes")));
					bot = Integer.parseInt(instruction.substring(instruction.indexOf("bot ")+4));
					bots[bot].addValue(value);
				}
				else if (instruction.startsWith("bot")) {
					String[] splitArr = instruction.split("bot | gives low to output | and high to output | gives low to bot | and high to bot ");
					int bot = Integer.parseInt(splitArr[1]);
					if (!bots[bot].canTrade())
						continue;
					int low = Integer.parseInt(splitArr[2]);
					int high = Integer.parseInt(splitArr[3]);
					
					if (!instruction.contains("output")) {
						bots[low].addValue(bots[bot].giveLow());
						bots[high].addValue(bots[bot].giveHigh());
						
					}
					else {
						if (instruction.contains("low to output")) {
							if (instruction.contains("high to output")) {
								outputs[low] = bots[bot].giveLow();
								outputs[high] = bots[bot].giveHigh();
							}
							else {
								outputs[low] = bots[bot].giveLow();
								bots[high].addValue(bots[bot].giveHigh());
							}
						}
						else {
							bots[low].addValue(bots[bot].giveLow());
							outputs[high] = bots[bot].giveHigh();
						}
					}
				bots[bot].clearValues(17,61);
				}
				instructionList.remove(instruction);
				break;
			}
		}
		System.out.println("finished!");
		System.out.println("Outputs multiply: " + outputs[0]*outputs[1]*outputs[2]);
	}
	
	public static int countBots (ArrayList<String> instructionList) {
		int highest = 0;
		int present = 0;
		for (String instruction : instructionList) {
			if (instruction.startsWith("bot "))
				try {
				present = Integer.parseInt(instruction.substring(4, instruction.indexOf(" gives")));
				if (present>highest)
					highest = present;
				}
				catch (NumberFormatException nfexc) {System.out.println("error parsing number in countBots");};
		}
		return highest+1;
	}
	
	public static ArrayList<String> listInstructions (BufferedReader reader) {
		ArrayList<String> instructions = new ArrayList<>();
		String instruction;
		try {
			reader.mark(1);
			instruction = (int)reader.read() + "";
			if (Integer.parseInt(instruction)<128)
				reader.reset();
			while ((instruction=reader.readLine())!=null) {
				instructions.add(instruction);
			}
		 
		}
		catch (IOException ioexception) {System.out.println("IOEXCEPTION"); return null;}
		catch (NumberFormatException nfexception) {System.out.println("error parsing string, number format exception"); return null;};
		
		return instructions;
	
		
	}
	
}
