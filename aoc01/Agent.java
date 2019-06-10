package aoc01;
import java.util.HashSet;
import java.util.Set;



public class Agent {
	public final static char[] KIERUNKI = new char[] {'N','E','S','W'};
	private int nrKierunku;
	private int x;
	private int y;
	private Set<String> koordynaty = new HashSet<>();
	private Integer xOdwiedzonyPonownie=null;
	private Integer yOdwiedzonyPonownie;
	
	
	
	
	public Agent () {
		this.nrKierunku = 0;
		this.x = 0;
		this.y = 0;
	}
	
	public boolean rusz (String instrukcja) {
		char kierunek;
		int dystans;
		instrukcja=instrukcja.toUpperCase();
		if (instrukcja.length()>=2 && (instrukcja.startsWith("L") || instrukcja.startsWith("R"))) {
			kierunek = instrukcja.charAt(0);
			dystans = Integer.parseInt(instrukcja.substring(1));
			switch (nrKierunku) {
			case 0: {
				if (kierunek=='L')
					idzX(-dystans);
				else if (kierunek=='R') 
					idzX(dystans);
				break;
				}
			
			case 1: {
				if (kierunek=='L')
					idzY(dystans);
				else if (kierunek=='R') 
					idzY(-dystans);
				break;
				}
			
			case 2: {
				if (kierunek=='L')
					idzX(dystans);
				else if (kierunek=='R') 
					idzX(-dystans);
				break;
				}
			
			case 3: {
				if (kierunek=='L')
					idzY(-dystans);
				else if (kierunek=='R') 
					idzY(dystans);
				break;
				}
			}
			
			
			if (kierunek=='L') {
				zmienKierunek(-1);	
			}
			else {
				zmienKierunek(1);
				
			}
			
		}
		else
			return false;
		return true;
		
	}
			
		
		
			
	
	private void zmienKierunek (int kierunek) {
		nrKierunku+=kierunek;
		if (nrKierunku<0)
			nrKierunku=3;
		else if (nrKierunku>3)
			nrKierunku=0;
	
	}
	
	private void idzX (int dystans) {
		if (xOdwiedzonyPonownie==null) {
			if(dystans<0)
				for (int i=0;i>dystans;i--) {
					x--;
					if (!koordynaty.add(x+","+y)){
						xOdwiedzonyPonownie=x;
						yOdwiedzonyPonownie=y;
						System.out.println("Ilosc pozycji w secie: " + koordynaty.size());
					}
				}
			else
				for (int i=0;i<dystans;i++) {
					x++;
					if (!koordynaty.add(x+","+y)){
						xOdwiedzonyPonownie=x;
						yOdwiedzonyPonownie=y;
						System.out.println("Ilosc pozycji w secie: " + koordynaty.size());
					}
				
				}
			
		}
		else
			x=x+dystans;
		
	}
	
	private void idzY (int dystans) {
		if (xOdwiedzonyPonownie==null) {
			if(dystans<0)
				for (int i=0;i>dystans;i--) {
					y--;
					if (!koordynaty.add(x+","+y)){
						xOdwiedzonyPonownie=x;
						yOdwiedzonyPonownie=y;
						System.out.println("Ilosc pozycji w secie: " + koordynaty.size());
					}
				}
			else
				for (int i=0;i<dystans;i++) {
					y++;
					if (!koordynaty.add(x+","+y)){
						xOdwiedzonyPonownie=x;
						yOdwiedzonyPonownie=y;
						System.out.println("Ilosc pozycji w secie: " + koordynaty.size());
					}
				}
			
		}
		else
			y=y+dystans;
		
	}
	
	public int obliczDroge () {
		return Math.abs(x)+Math.abs(y);
	}
	
	public int obliczDroge2 () {
		if (xOdwiedzonyPonownie==null)
			return 0;
		else
			return Math.abs(xOdwiedzonyPonownie)+Math.abs(yOdwiedzonyPonownie);
	}
	
}
