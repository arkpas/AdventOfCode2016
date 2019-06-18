package aoc11;

import java.util.ArrayDeque;
import java.util.HashSet;





public class Rtg {

	
	public static void main (String[] args) {
		Building building = new Building(
				new Floor(new Generator("S"), new Microchip("S"), new Generator("P"), new Microchip("P"), new Generator("E"), new Microchip("E"), new Generator("D"), new Microchip("D")),
				new Floor(new Generator("T"), new Generator("R"), new Microchip("R"), new Generator("C"), new Microchip("C")),
				new Floor(new Microchip("T")),
				new Floor()
				);
	
		
		ArrayDeque<Building> queue = new ArrayDeque<>();
		queue.add(building);
		HashSet<Integer> knownBuildings = new HashSet<>();
		
		long loops = 0;
		long checks = 0;
		long time = System.currentTimeMillis();
		
		
		while (!queue.isEmpty()) {
			loops++;
			Building currentBuilding = queue.pop();
			if (currentBuilding.isCompleted()) {
				time = System.currentTimeMillis() - time;
				System.out.println("Steps: " + currentBuilding.getSteps());
				System.out.println("Loops: " + loops);
				System.out.println("Comparisons: " + checks);
				System.out.println("Time: " + (double)time/1000);
				building = currentBuilding;
				break;
			}
			else {
				for (Building b : currentBuilding.getListOfAllMoves()) {
					checks++;
					if (b.isSafe() && knownBuildings.add(b.hashCode()))
						queue.add(b);
				}
			}
		}
		
		
		/* 
		 * if you want to see every step
		 
		while (building.getAncestor() != null) {
			building = building.getAncestor();
			System.out.println(building);
			System.out.println();
		}
		*/
	
	
	}
}
