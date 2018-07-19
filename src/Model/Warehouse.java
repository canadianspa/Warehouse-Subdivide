package Model;

import java.util.ArrayList;

public class Warehouse extends Directory {

	
	char[] directions;
	int[] magnitude;
	int[] zoneLocs;
	
	public Warehouse(String name, char[] directions, int[] magnitude, int[] zoneLocs) {
		super(name);
		this.directions = directions;
		this.magnitude = magnitude;
		this.zoneLocs = zoneLocs;
	}

	public int[] getZoneLocs() {
		return zoneLocs;
	}

	public void setZoneLocs(int[] zoneLocs) {
		this.zoneLocs = zoneLocs;
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
