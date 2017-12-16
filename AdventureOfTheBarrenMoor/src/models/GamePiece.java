package models;

public abstract class GamePiece {
	private int xPosition;
	private int yPosition;

	public GamePiece(int xPosition, int yPosition) {
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

	// takes the barren moor for bounds checking
	public void move(int dx, int dy, BarrenMoor barrenMoor) {

		int newX = xPosition + dx;
		int newY = yPosition + dy;

		if (newX < -barrenMoor.getWidth() / 2 || newX > barrenMoor.getWidth() / 2 || newY < -barrenMoor.getHeight() / 2
				|| newY > barrenMoor.getHeight() / 2) {
			System.out.println("One does not simply walk out of the Barren Moor!");
		}

		else {
			this.xPosition = newX;
			this.yPosition = newY;
		}
	}

	public double getDistFromPeice(GamePiece other) {
		int x_2 = (this.getXPosition() - other.getXPosition()) * (this.getXPosition() - other.getXPosition());
		int y_2 = (this.getYPosition() - other.getYPosition()) * (this.getYPosition() - other.getYPosition());

		return Math.sqrt(x_2 + y_2);
	}

	public boolean onTopOf(GamePiece other) {
		if (other == null)
			return false;
		return getXPosition() == other.getXPosition() && getYPosition() == other.getYPosition();
	}
}
