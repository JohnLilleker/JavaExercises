package models;

import java.util.ArrayList;
import java.util.Collections;

public class RandomPlayer extends Player {

	private ArrayList<int[]> allMoves;

	public RandomPlayer(String name) {
		super(name);
		allMoves = new ArrayList<>();
	}

	@Override
	public void setBoard() {

		// get every possible location
		setAllMoves();

		// mix it up
		Collections.shuffle(allMoves);

		// an index for choosing a location
		// this means every attempt will try at a different
		int locationIndex = 0;

		// for each ship
		for (Ship boat : getBoard().getFleet()) {

			int x, y;

			// while the ship placement is unsuccessful
			do {
				int[] pos = allMoves.get(locationIndex);
				locationIndex++;
				x = pos[0];
				y = pos[1];
				// try to place it at a different random location
			} while (!tryShip(boat, x, y));

			// this is definitely a more elegant solution than my c++ attempt
		}

		setGameReady(true);
	}

	// given a location this tries to add a boat to the grid in any of the 4
	// directions
	private boolean tryShip(Ship boat, int x, int y) {
		// vertical or horizontal placement
		int dx = (Math.random() > 0.5) ? 1 : 0;
		int dy = (dx == 1) ? 0 : 1;

		// A way to check all 4 directions when trying to place a Ship
		// start at the direction chosen above
		// if it doesn't work, do the opposite direction, i.e. down instead of up, left
		// instead of right
		// that it doesn't work, go the other direction, so if the original was up, this
		// will be right
		// if that doesn't work flip direction again, left instead of right
		int[][] attempts = { { dx, dy }, { -dx, -dy }, { dy, dx }, { -dy, -dx } };
		// if all fail still, another location is picked

		// for all four directions
		for (int i = 0; i < 4; i++) {
			// try to place the ship
			if (getBoard().placeShip(boat, x, y, attempts[i][0], attempts[i][1])) {
				// it it works, great. now move onto the next
				return true;
			}
			// otherwise keep trying
		}
		return false;
	}

	// overridden for extra functionality
	public void resetBoard() {
		// clear the board and game ready
		super.resetBoard();
		// clear all stored moves
		this.allMoves.clear();
	}

	@Override
	public int[] play(Board enemy) {

		// mix up moves
		Collections.shuffle(allMoves);
		// take the last (more efficient than removing from front)
		int[] move = allMoves.remove(allMoves.size() - 1);
		// no need for checks since any spaces used are removed from the array list
		return move;
	}

	private void setAllMoves() {
		// get every possible location
		for (int x = 0; x < Board.GRID_SIZE; x++) {
			for (int y = 0; y < Board.GRID_SIZE; y++) {
				// store them for future reference in playing later
				allMoves.add(new int[] { x, y });
			}
		}
	}

}
