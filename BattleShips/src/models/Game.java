package models;

public class Game {

	public void game(Player p1, Player p2) {

		if (!p1.hasSetBoard())
			p1.setBoard();
		if (!p2.hasSetBoard())
			p2.setBoard();

		boolean quit = false;
		boolean play1 = true;

		while (!p1.hasLost() && !p2.hasLost()) {
			Player attack = (play1) ? p1 : p2;
			Player defend = (play1) ? p2 : p1;

			System.out.println(attack.getName() + "'s move");

			int[] move = attack.play(defend.getBoard());

			if (move[0] == -1) {
				quit = true;
				break;
			}

			System.out.println(attack.getName() + " moves at " + Board.coordinatesToString(move));
			System.out.println();

			int status = defend.callHit(move[0], move[1]);

			if (status == Board.DEAD)
				System.out.println("Ship Destroyed!");
			else if (status == Board.HIT)
				System.out.println("Ship Hit!");
			else
				System.out.println("Shot Missed!");

			System.out.println();
			play1 = !play1;
		}

		if (quit) {
			System.out.println("Match forfet...");
		} else {

			String winner = (p1.hasLost()) ? p2.getName() : p1.getName();
			System.out.println(winner + " has Won!!");
		}
	}
}
