package models;

public class Monster extends GamePeice {

	private boolean isSleeping = true;

	public Monster(int xPosition, int yPosition) {
		super(xPosition, yPosition);
	}

	public void moveToPlayer(Player player) {
		if (!isSleeping || getDistFromPeice(player) < 5) {

			if (isSleeping) {
				System.out.println("Suddenly, you hear a large growling noise...");
			}

			isSleeping = false;
			int xDist = player.getXPosition() - this.getXPosition();
			int yDist = player.getYPosition() - this.getYPosition();
			if (Math.abs(xDist) > Math.abs(yDist)) {
				int dx = (xDist > 0) ? 1 : -1;
				move(dx, 0);
			} else {
				int dy = (yDist > 0) ? 1 : -1;
				move(0, dy);
			}
		}
	}

	public boolean isAsleep() {
		return isSleeping;
	}

}
