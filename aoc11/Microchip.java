package aoc11;

public class Microchip implements Device {
	
	private String name;
	
	public Microchip (String name) {
		this.name = name;
	}

	public boolean isGenerator () {
		return false;
	}
	
	public String getName () {
		return name;
	}
	
	@Override
	public String toString() {
		return name + "M";
	}
	
	@Override
	public int compareTo (Device other) {
		return this.getName().compareTo(other.getName());
	}
	
	@Override
	public boolean equals (Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Device))
			return false;
		Device device = (Device) o;
		
		return this.getName().equals(device.getName());
	}

}
