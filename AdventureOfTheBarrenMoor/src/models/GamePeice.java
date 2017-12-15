package models;

public abstract class GamePeice {
	private int xPosition;
	private int yPosition;

	public GamePeice(int xPosition, int yPosition) {
		super();
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}

	public int getXPosition() {
		return xPosition;
	}

	public int getYPosition() {
		return yPosition;
	}

	public void move(int dx, int dy) {

		int newX = xPosition + dx;
		int newY = yPosition + dy;

		if (newX < -BarrenMoor.MOOR_SIZE / 2 || newX > BarrenMoor.MOOR_SIZE / 2 || newY < -BarrenMoor.MOOR_SIZE / 2
				|| newY > BarrenMoor.MOOR_SIZE / 2) {
			System.out.println("One does not simply walk out of the Barren Moor!");
		}

		else {
			this.xPosition = newX;
			this.yPosition = newY;
		}
	}

	public double getDistFromPeice(GamePeice other) {
		int x_2 = (this.getXPosition() - other.getXPosition()) * (this.getXPosition() - other.getXPosition());
		int y_2 = (this.getYPosition() - other.getYPosition()) * (this.getYPosition() - other.getYPosition());

		return Math.sqrt(x_2 + y_2);
	}

	public boolean onTopOf(GamePeice other) {
		return getXPosition() == other.getXPosition() && getYPosition() == other.getYPosition();
	}
}
