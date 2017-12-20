package runner;

import models.Game;
import models.HumanPlayer;
import models.Player;

public class Main {

	public static void main(String[] args) {

		Player p1 = new HumanPlayer("Tester");
		p1.setBoard();

		Player p2 = new HumanPlayer("Developer");
		p2.setBoard();

		Game battleships = new Game();
		battleships.game(p2, p1);

	}

}
