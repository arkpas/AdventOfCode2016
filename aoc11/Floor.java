package aoc11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Floor {
	
	private List<Device> devices;
	private String floorRepresentation = "";
	
	public Floor () {
		devices = new ArrayList<>();
	}
	
	public Floor (Device...devices) {
		this.devices = new ArrayList<>();
		this.devices.addAll(Arrays.asList(devices));
	}
	
	public Floor (List<Device> devices) {
		this.devices = new ArrayList<>();
		this.devices.addAll(devices);
	}
	
	public void addDevice (Device device) {
		if (device != null)
			devices.add(device);
	}
	

	public Device removeDevice (int deviceIndex) {
		
		if (devices.size() > deviceIndex) {
			try {
				return devices.remove(deviceIndex);
			}
			catch (IndexOutOfBoundsException e) {System.out.println("Wrong item index!"); e.printStackTrace();}
		}
		return null;
	}
	
	public List<Device> getDevices () {
		return Collections.unmodifiableList(devices);
	}
	
	public int getDeviceCount () {
		return devices.size();
	}

	public boolean isEmpty () {
		return devices.isEmpty();
	}
	
	public boolean isSafe () {
		List<Device> microchips = new ArrayList<>();
		List<Device> generators = new ArrayList<>();
		floorRepresentation = "";
		
		devices.forEach(a -> {
			if (a.isGenerator())
				generators.add(a);
			else 
				microchips.add(a);
		});
		if (microchips.size() == 0 || generators.size() == 0)
			return true;
		if (microchips.size() <= generators.size()) {
			for (Device d : microchips) {
				if (!generators.contains(d))
					return false;
			}
			if (microchips.size() == generators.size())
				floorRepresentation = generators.size() + "MG"; //if there are only microchip and generator pairs on the floor we don't have to worry about their names, only pair count is important for us
			return true;
		}
		else 
			return false;
		
	}
	
	public void sort () {
		Collections.sort(devices);
	}
	
	public String getFloorRepresentation () {
		if (floorRepresentation != "")
			return floorRepresentation;
		return this.toString();	
	}
	
	@Override
	public String toString () {
		StringBuilder result = new StringBuilder();
		if (devices.isEmpty())
			return "---";
		devices.forEach(a -> {
			result.append(a.toString() + " | ");
		});
		
		return result.toString();
	}
	
	
	
	
}
