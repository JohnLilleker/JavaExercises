package runner;

import models.*;

public class Main {

	public static void main(String[] args) {

		Player p1 = new HumanPlayer("Tester");
		p1.setBoard();

		Player p2 = new SmartPlayer("Deep Mauve");
		p2.setBoard();

		Game battleships = new Game();
		Player winner = battleships.game(p1, p2);

		if (winner == null) {
			System.out.println("Match forfeit");
		} else {
			System.out.println(winner.getName() + " won");
		}

		System.out.println();
		System.out.println(p1.getName() + "'s board");
		p1.getBoard().showBoard(true, true);
		System.out.println();
		System.out.println(p2.getName() + "'s board");
		p2.getBoard().showBoard(true, true);

	}

}
