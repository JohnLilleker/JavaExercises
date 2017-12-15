package models;

public class Player extends GamePeice {
	private String name;

	public Player(String name) {
		super(0, 0);
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
