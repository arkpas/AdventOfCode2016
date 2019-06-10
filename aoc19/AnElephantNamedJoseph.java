package aoc19;

import java.util.ArrayList;

public class AnElephantNamedJoseph {
	
	public static void main (String[] args) {
		
		
		System.out.println(givePresents3(3018458));
		

		

		
	}
	
	
	public static int givePresents (int elves) {
		
		int step = 1;
		int elvis = elves;
		int first = 1;
		
		while (elvis>1) {

			step = step*2;
			if (elvis%2!=0)
				first+=step;
			elvis = elvis/2;
		}
		
		
		
		
		
		
		return first;
	}
	
	public static int givePresents2 (int elves) {
		
		ArrayList<Integer> list = new ArrayList<>();
		
		for (int i=1;i<=elves;i++) {
			list.add(i);
		}
		int index = 0;
		while (list.size()>1) {
			for (int i=0;i<list.size();) {
				index = (list.size()/2+i)%list.size();
				list.remove(index);
				if (index>i)
					i++;
			}

		}
		
		
		return list.get(0);
		
		
		

	}
	
	public static int givePresents3 (int elves) {
		
		

		
		ArrayList<Integer> PowThree = new ArrayList<>();
		for (long i=3 ; i<Integer.MAX_VALUE ;i=i*3) {
			PowThree.add((int)i);
		}
		int lesserPower = 0;
		
		for (int i=0; i<PowThree.size() ; i++) {
			if (PowThree.get(i)>elves) {
				lesserPower = PowThree.get(i)/3;
				break;
			}
			
		}
		if (elves==lesserPower)
			return lesserPower;
		if (elves<lesserPower*2)
			return elves-lesserPower;
		return ((elves-lesserPower*2)*2 + lesserPower);
		
	
		
		
		
	}
		
}


