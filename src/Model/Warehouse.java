package Model;

public class Warehouse extends Directory {

	
	char[] directions;
	int[] magnitude;
	
	public Warehouse(String name, char[] directions, int[] magnitude) {
		super(name);
		this.directions = directions;
		this.magnitude = magnitude;
	}

	public char[] getDirections() {
		return directions;
	}

	public void setDirections(char[] directions) {
		this.directions = directions;
	}

	public int[] getMagnitude() {
		return magnitude;
	}

	public void setMagnitude(int[] magnitude) {
		this.magnitude = magnitude;
	}
	
	
	

}
