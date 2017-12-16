package models;

public class Monster extends GamePiece {

	private boolean isSleeping = true;

	public Monster(int xPosition, int yPosition) {
		super(xPosition, yPosition);
	}

	public void moveToPlayer(Player player, BarrenMoor barrenMoor) {
		if (!isSleeping) {
			int xDist = player.getXPosition() - this.getXPosition();
			int yDist = player.getYPosition() - this.getYPosition();
			if (Math.abs(xDist) > Math.abs(yDist)) {
				int dx = (xDist > 0) ? 1 : -1;
				move(dx, 0, barrenMoor);
			} else {
				int dy = (yDist > 0) ? 1 : -1;
				move(0, dy, barrenMoor);
			}
		}
	}

	public boolean isAsleep() {
		return isSleeping;
	}

	public void wake() {
		isSleeping = false;
	}

}
