package models;

public class Game {

	public Player game(Player p1, Player p2) {

		if (!p1.hasSetBoard())
			p1.setBoard();
		if (!p2.hasSetBoard())
			p2.setBoard();

		// players have to option to forfeit
		// "All games should have a way for the user to exit prematurely"
		// paraphrased from a book on game programming
		boolean quit = false;

		// decide who plays this turn
		// if true, player 1 else player 2
		boolean play1 = true;

		while (!gameOver(p1, p2)) {

			// swapping turns in an elegant manner
			Player attack = (play1) ? p1 : p2;
			Player defend = (play1) ? p2 : p1;

			System.out.println(attack.getName() + "'s move");

			// player calls a move
			int[] move = attack.play(defend.getBoard());

			// returning a -1 indicates an intention to forfeit
			if (hasQuit(move)) {
				quit = true;
				break;
			}

			System.out.println(attack.getName() + " moves at " + Board.coordinatesToString(move));
			System.out.println();

			// other tells them what happened
			int status = defend.callHit(move);

			// Say miss, hit or destroyed
			printMoveStatus(status);

			// player keeps hitting, they keep going
			if (status == Board.MISS)
				// switch roles, attacker becomes defender etc
				play1 = !play1;
		}

		if (quit) {
			return null;
		} else {
			return (p1.hasLost()) ? p2 : p1;
		}
	}

	private boolean hasQuit(int[] move) {
		return move[0] == -1;
	}

	private boolean gameOver(Player p1, Player p2) {
		return p1.hasLost() || p2.hasLost();
	}

	private void printMoveStatus(int status) {
		if (status == Board.DEAD)
			System.out.println("Ship Destroyed!");
		else if (status == Board.HIT)
			System.out.println("Ship Hit!");
		else
			System.out.println("Shot Missed!");

		System.out.println();
	}
}
