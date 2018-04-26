package data;

import java.util.ArrayList;

/**
 * This class is used to store information about a room.
 * @author Connor Stewart
 */
public class Room {
	
	private int numOfWorkstations;
	
	private int numOfBreakoutSeats;
	
	private ArrayList<Equiptment> equiptment;
	
	private String name;

	public Room(int numOfWorkstations, int numOfBreakoutSeats, ArrayList<Equiptment> equiptment, String name) {
		this.numOfWorkstations = numOfWorkstations;
		this.numOfBreakoutSeats = numOfBreakoutSeats;
		this.equiptment = equiptment;
		this.name = name;
	}

	/**
	 * @return the numOfWorkstations
	 */
	public int getNumOfWorkstations() {
		return numOfWorkstations;
	}

	/**
	 * @return the numOfBreakoutSeats
	 */
	public int getNumOfBreakoutSeats() {
		return numOfBreakoutSeats;
	}

	/**
	 * @return the equiptment
	 */
	public ArrayList<Equiptment> getEquiptment() {
		return equiptment;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	

}
