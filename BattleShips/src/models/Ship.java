package models;

public class Ship {
	private int length;
	private char id;
	private int lives;

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
}
