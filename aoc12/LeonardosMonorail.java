package aoc12;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class LeonardosMonorail {
	static HashMap<Character, Integer> map = new HashMap<>();
	
	public static void main (String[] args) throws IOException {
		
		BufferedReader reader = new BufferedReader (new InputStreamReader (new FileInputStream("src/aoc12/instructions.txt"), "utf-8"));
		int a=0,b=0,c=1,d=0;
		map.put('a', a);
		map.put('b', b);
		map.put('c', c);
		map.put('d', d);
		
		reader.mark(1);
		if ((int)reader.read()<200)
			reader.reset();
		
		String line = "";
		ArrayList<String> instructions = new ArrayList<>();
		
		while ((line=reader.readLine())!=null) {
			instructions.add(line);	
		}
		execute(instructions);
		System.out.println(Integer.parseInt("-50"));
		
		reader.close();
	}
	
	public static void execute (ArrayList<String> instructions) {
		int position = 0;
		
		while (position<instructions.size()) {
			System.out.println(map.get('a') + "pos: " + position);
			String[] split = instructions.get(position).split(" ");
			switch (split[0]) {
				case "inc": {
					map.put(split[1].charAt(0), map.get(split[1].charAt(0))+1);
					position++;
					break;
				}
				
				case "dec": {
					map.put(split[1].charAt(0), map.get(split[1].charAt(0))-1);
					position++;
					break;
				}
				case "cpy": {
					if (Character.isLetter(split[1].charAt(0)))
						map.put(split[2].charAt(0),map.get(split[1].charAt(0)));
					else 
						map.put(split[2].charAt(0),Integer.parseInt(split[1]));
					position++;
					break;
				}
				
				case "jnz": {
					if (Character.isLetter(split[1].charAt(0))) {
						if (map.get(split[1].charAt(0))>0)
							position+=Integer.parseInt(split[2]);
						else
							position++;
					}
					else 
						if (Integer.parseInt(split[1])>0)
							position+=Integer.parseInt(split[2]);
						else 
							position++;
					break;
				}

			}

		}
		
			
	}
		
		
}
