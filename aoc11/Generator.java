package aoc11;

public class Generator implements Device {
	private String name;
	
	public Generator (String name) {
		this.name = name;
	}

	public boolean isGenerator () {
		return true;
	}
	
	public String getName () {
		return name;
	}
	
	@Override
	public String toString() {
		return name + "G";
	}
	
	@Override
	public int compareTo (Device other) {
		if (this == other)
			return 0;
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
