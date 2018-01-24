package models;

public class Ship {
	private int length;
	private char id;
	private int lives;

	private String location = "";
	private String direction = "";

	public Ship(int length, char id) {
		this.length = length;
		this.id = id;
		lives = length;
	}

	public int getLength() {
		return length;
	}

	public char getID() {
		return id;
	}

	public boolean isDead() {
		return lives < 1;
	}

	public void hit() {
		lives--;
	}

	public void fix() {
		lives = length;
	}

	public int getLives() {
		return lives;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
}
