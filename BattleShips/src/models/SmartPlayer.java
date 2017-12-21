package models;

import java.util.ArrayList;
import java.util.Collections;

public class SmartPlayer extends RandomPlayer {

	// intelligence
	// looks for previous hits and moves on the surrounding cells
	// fatal to clustered fleets
	private ArrayList<int[]> followUpMoves;

	public SmartPlayer(String name) {
		super(name);
		followUpMoves = new ArrayList<>(Board.GRID_SIZE * Board.GRID_SIZE);
	}

	public int[] play(Board board) {

		// looks for hits to capitalise on
		observe(board);

		// if no moves to capitalise on, play as random
		if (followUpMoves.isEmpty()) {
			return playAsRandom(board);
		}

		Collections.shuffle(followUpMoves);
		int[] move = followUpMoves.get(0);

		return move;
	}

	private int[] playAsRandom(Board board) {

		int[] move;
		boolean moveDecided = true;
		do {
			// call PandomPlayer.move()
			move = super.play(board);
			// need check here to make sure they don't call a none empty space
			// smart moves aren't removed from the random move list yet
			if (board.isEmpty(move[0], move[1])) {
				// %2 filter for extra board coverage with minimal turns
				if ((move[0] + move[1]) % 2 == 1)
					moveDecided = false;
			}
		} while (moveDecided);
		return move;
	}

	private void observe(Board board) {
		// clear memory, things might have changed
		followUpMoves.clear();

		// right, up, left, down
		int[][] directions = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

		// for each cell
		for (int y = 0; y < Board.GRID_SIZE; y++) {
			for (int x = 0; x < Board.GRID_SIZE; x++) {
				// check it was a hit
				if (board.isHit(x, y)) {
					// look around it
					for (int d = 0; d < 4; d++) {
						int nx = x + directions[d][0], ny = y + directions[d][1];
						// if it is a valid move that is empty
						if (!board.outOfRange(nx, ny) && board.isEmpty(nx, ny)) {
							// store it
							followUpMoves.add(new int[] { nx, ny });
						}
					}
				}
			}
		}
	}
}
