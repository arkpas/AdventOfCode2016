package aoc01;

public class DrogaDoKwatery {
	public static void main (String[] args) {
		Agent jaAgent = new Agent();
		rozpocznijMisje ("R4, R4, L1, R3, L5, R2, R5, R1, L4, R3, L5, R2, L3, L4, L3, R1, R5, R1, L3, L1, R3, L1, R2, R2, L2, R5, L3, L4, R4, R4, R2, L4, L1, R5, L1, L4, R4, L1, R1, L2, R5, L2, L3, R2, R1, L194, R2, L4, R49, R1, R3, L5, L4, L1, R4, R2, R1, L5, R3, L5, L4, R4, R4, L2, L3, R78, L5, R4, R191, R4, R3, R1, L2, R1, R3, L1, R3, R4, R2, L2, R1, R4, L5, R2, L2, L4, L2, R1, R2, L3, R5, R2, L3, L3, R3, L1, L1, R5, L4, L4, L2, R5, R1, R4, L3, L5, L4, R5, L4, R5, R4, L3, L2, L5, R4, R3, L3, R1, L5, R5, R1, L3, R2, L5, R5, L3, R1, R4, L5, R4, R2, R3, L4, L5, R3, R4, L5, L5, R4, L4, L4, R1, R5, R3, L1, L4, L3, L4, R1, L5, L1, R2, R2, R4, R4, L5, R4, R1, L1, L1, L3, L5, L2, R4, L3, L5, L4, L1, R3", jaAgent);
		System.out.println("Najkrotsza droga ma odleglosc: " + jaAgent.obliczDroge());
		System.out.println("Droga od punktu, ktory jako pierwszy byl odwiedzony 2 razy: " + jaAgent.obliczDroge2());
	}
	
	public static void rozpocznijMisje (String mapa, Agent detektyw) {
		String instrukcja = "brak";
		mapa = mapa.toUpperCase();
		
		for (int i=0;i<mapa.length();i++) {
			if (mapa.charAt(i) == 'L' || mapa.charAt(i) == 'R') {
				if (instrukcja.contains("brak"))
					instrukcja = mapa.charAt(i) + "";
				else {
					detektyw.rusz(instrukcja);
					instrukcja = mapa.charAt(i) + "";
				}
			}
			else if (Character.isDigit(mapa.charAt(i))) {
				instrukcja+=mapa.charAt(i);
			}
			else
				continue;
			
			if (i==mapa.length()-1)
				detektyw.rusz(instrukcja);
		}
		
	}
	
	
	
}