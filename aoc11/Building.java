package aoc11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Building {
	
	private List<Floor> floors;
	private int elevatorFloor;
	private int previousElevatorFloor;
	private int steps = 0;
	private Building previous = null;
	
	
	public Building () {
		this.floors = new ArrayList<Floor>();
		this.elevatorFloor = 0;
	}
	
	public Building (Floor...floors) {
		this(0, floors);
	}
	
	public Building (int elevatorFloor, Floor...floors) {
		this.elevatorFloor = elevatorFloor;
		this.floors = new ArrayList<Floor>();
		
		this.floors.addAll(Arrays.asList(floors));
	}
	
	public Building (List<Floor> floors, int elevatorFloor) {
		this.elevatorFloor = elevatorFloor;
		this.floors.addAll(floors);
	}
	
	public void addFloor (Floor floor) {
		this.floors.add(floor);
	}
	
	public void sortFloors () {
		for (Floor f : floors) {
			f.sort();
		}
	}
	
	private Building copyBuilding () {
		Building building = new Building();
		for (int i = 0; i < floors.size(); i++) {
			building.addFloor(new Floor(floors.get(i).getDevices()));
		}
		building.elevatorFloor = this.elevatorFloor;
		building.steps = this.steps;
		building.previous = this;
		return building;
	}
	
	
	
	public List<Building> getListOfAllMoves () {
		List<Building> listOfAllMoves = new ArrayList<>();
		List<Building> helperList = new ArrayList<>();
		
		helperList = this.getDoubleMovesUp();
		listOfAllMoves.addAll(helperList);
		
		if (helperList.isEmpty()) {  
			helperList = this.getSingleMovesUp();
			listOfAllMoves.addAll(helperList);
		}
		// checks if any double moves were made - if yes, then we do not have to make single moves, because moving two objects is more important
		
		boolean areEmpty = true;
		for (int i = elevatorFloor - 1; i >= 0; i--)
			if (!floors.get(i).isEmpty())
				areEmpty = false;
		// if floors below are empty we do NOT have to move items there
		
		if (!areEmpty) {
			helperList = this.getSingleMovesDown();
			listOfAllMoves.addAll(helperList);
			if (helperList.isEmpty()) {
				helperList = this.getDoubleMovesDown();
				listOfAllMoves.addAll(helperList);
			}
			// similiar as moving two items up, but here instead we check if any single moves down were made - if yes, then we do not have to move two items down, because it is not efficient 
		}
		
		for (Building b : listOfAllMoves)
			b.sortFloors();
		
		return listOfAllMoves;
	}
	
	private List<Building> getSingleMovesUp () {
		List<Building> buildings = new ArrayList<>();
		int floorDeviceCount = floors.get(elevatorFloor).getDeviceCount();
		
		if (elevatorFloor < floors.size() - 1) {
			for (int i = 0; i < floorDeviceCount; i++) {
				Building building = this.copyBuilding();
				building.moveUp(i);
				buildings.add(building);
			}
		}
		return buildings;
	}
	
	private List<Building> getDoubleMovesUp () {
		List<Building> buildings = new ArrayList<>();
		int floorDeviceCount = floors.get(elevatorFloor).getDeviceCount();
		if (floorDeviceCount >= 2) {
			if (elevatorFloor < floors.size() - 1) {
				for (int i = 0; i < floorDeviceCount - 1; i++) {
					for (int j = i; j < floorDeviceCount - 1; j++) {
						Building building = this.copyBuilding();
						building.moveUp(i,j);
						buildings.add(building);
					}
				}
			}
		}
		return buildings;
	}
	
	private List<Building> getSingleMovesDown () {
		List<Building> buildings = new ArrayList<>();
		int floorDeviceCount = floors.get(elevatorFloor).getDeviceCount();
		
		if (elevatorFloor > 0) {
			for (int i = 0; i < floorDeviceCount; i++) {
				Building building = this.copyBuilding();
				building.moveDown(i);
				buildings.add(building);
			}
		}
		return buildings;
	}
	
	private List<Building> getDoubleMovesDown () {
		List<Building> buildings = new ArrayList<>();
		int floorDeviceCount = floors.get(elevatorFloor).getDeviceCount();
		if (floorDeviceCount >= 2) {
			if (elevatorFloor > 0) {
				for (int i = 0; i < floorDeviceCount - 1; i++) {
					for (int j = i; j < floorDeviceCount - 1; j++) {
						Building building = this.copyBuilding();
						building.moveDown(i,j);
						buildings.add(building);
					}
				}
			}
		}
		return buildings;
	}

	private void moveUp (int itemIndex) {
		if (floors.size() > elevatorFloor +1 && floors.get(elevatorFloor).getDeviceCount() != 2) {
			try {
				previousElevatorFloor = elevatorFloor;
				floors.get(elevatorFloor + 1).addDevice(floors.get(elevatorFloor).removeDevice(itemIndex));
				elevatorFloor++;
				steps++;
			}
			catch (IndexOutOfBoundsException e) {System.out.println("Wrong floor number!"); e.printStackTrace();}
		}
		
	}
	
	private void moveUp (int itemIndex1, int itemIndex2) {
		if (floors.size() > elevatorFloor +1) {
			try {
				previousElevatorFloor = elevatorFloor;
				floors.get(elevatorFloor + 1).addDevice(floors.get(elevatorFloor).removeDevice(itemIndex1));
				floors.get(elevatorFloor + 1).addDevice(floors.get(elevatorFloor).removeDevice(itemIndex2)); //
				elevatorFloor++;
				steps++;
			}
			catch (IndexOutOfBoundsException e) {System.out.println("Wrong floor number!"); e.printStackTrace();}
		}
		
	}
	
	private void moveDown (int itemIndex) {
		if (elevatorFloor - 1 >= 0) {
			try {
				previousElevatorFloor = elevatorFloor;
				floors.get(elevatorFloor - 1).addDevice(floors.get(elevatorFloor).removeDevice(itemIndex));
				elevatorFloor--;
				steps++;
			}
			catch (IndexOutOfBoundsException e) {System.out.println("Wrong floor number!"); e.printStackTrace();}
		}
		
	}
	

	private void moveDown (int itemIndex1, int itemIndex2) {
		if (elevatorFloor - 1 >= 0) {
			try {
				previousElevatorFloor = elevatorFloor;
				floors.get(elevatorFloor - 1).addDevice(floors.get(elevatorFloor).removeDevice(itemIndex1));
				floors.get(elevatorFloor - 1).addDevice(floors.get(elevatorFloor).removeDevice(itemIndex2));
				elevatorFloor--;
				steps++;
			}
			catch (IndexOutOfBoundsException e) {System.out.println("Wrong floor number!"); e.printStackTrace();}
		}
		
	}
	
	
	public boolean isSafe () {
		if (floors.get(elevatorFloor).isSafe() && floors.get(previousElevatorFloor).isSafe())
			return true;
		return false;
	}
	
	public boolean isCompleted () {
		
		for (int i = 0; i < floors.size() - 1; i++) {
			if (!floors.get(i).isEmpty())
				return false;
		}
		return true;
	}
	
	public Building getAncestor () {
		return previous;
	}
	
	public int getSteps () {
		return steps;
	}

	@Override
	public String toString () {
		StringBuilder result = new StringBuilder();
		floors.forEach(a -> {
			result.insert(0, a.getFloorRepresentation() + "\n");
		});
		
		return result.toString() + "E" + elevatorFloor;
	}
	
	@Override 
	public int hashCode () {
		return this.toString().hashCode();
	}
	
	
}
