package aoc15;

public class TimingIsEverything {
	public static void main (String[] args) {
		
		Disc[] discs = {
			new Disc(15,17),
			new Disc(2,3),
			new Disc(4,19),
			new Disc(2,13),
			new Disc(2,7),
			new Disc(0,5),
			new Disc(0,11)
		};
		
		Disc[] discs2 = {
				new Disc(4,5),
				new Disc(1,2)
			};
		
		System.out.println(getTiming(discs));
		
	
		
	}
	
	public static int getTiming (Disc[] discs) {
		
		int interval = discs[0].getTimeAtZero();
		int time = interval-1;
		boolean gotThrough = false;
		
		while (!gotThrough) {
			int counter = 1;
			for (Disc a : discs) {
				if (!a.isZero(time+counter)) {
					gotThrough=false;
					break;
				}
				else {
					gotThrough=true;
				}
				counter++;
			}
			time+=interval;
			
		}
		
		
		return time-interval;
	}

}
