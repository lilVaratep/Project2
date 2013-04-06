// Varatep Buranintu
// Austin Myers
// Project 2

// Node.java

import java.util.*;

public class Node {
	int spookiness;
	int roomNumber;
	int work;
	
	ArrayList<Integer> sharedWalls = new ArrayList<Integer>();
	public Node() {
		spookiness = 0;
		roomNumber = 0;
		sharedWalls = null;
	}
	public Node(int roomNumber, int spookiness, int[] sharedWallsArray) {
		this.spookiness = spookiness;
		this.roomNumber = roomNumber;
		setUpWalls(sharedWallsArray);
	}
	public void setUpWalls(int[] sharedWallsArray) {
		for (int i = 0; i < sharedWallsArray.length; i++) {
			if (sharedWallsArray[i] != roomNumber)
				sharedWalls.add(sharedWallsArray[i]);
		}
	}
	
	public void setSpookiness(int n) {
		spookiness = n;
	}
	public int getSpookiness() {
		return spookiness;
	}
	
	public void setRoomNumber(int n) {
		roomNumber = n;
	}
	public int getRoomNumber() {
		return roomNumber;
	}
	public ArrayList<Integer> getSharedWalls() {
		return sharedWalls;
	}
}