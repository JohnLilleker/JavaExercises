package models;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HumanPlayer extends Player {

	private Scanner scan;
	private int[] move;

	// check the validity of a player's move
	// basically a letter from A to L followed by a number 1 to 12
	private static Pattern validMove = Pattern.compile("^([A-L])([1-9]|1[0-2])$");

	public HumanPlayer(String name) {
		super(name);
		scan = new Scanner(System.in);
		move = new int[2];
	}

	@Override
	public void setBoard() {

		ArrayList<Ship> fleet = getBoard().getFleet();

		for (Ship boat : fleet) {

			// tell them what they have to place
			getBoard().showBoard(true, false);
			System.out.println("\nPlacing a " + boat.getLength() + "x1");

			boolean wrong = true;

			// til they get it right
			while (wrong) {
				System.out.print("Enter a coordinate: ");
				String input = scan.nextLine().toUpperCase().replaceAll(" ", "");

				// valid coordinate?
				if (validateInput(input)) {
					// empty space?
					if (getBoard().getStatus(move[0], move[1]) == Board.EMPTY) {
						// good, pick a direction
						System.out.println("Placing at " + Board.coordinatesToString(move));
						System.out.print("Choose a direction; up, down, left or right: ");
						String direction = scan.nextLine();
						int dx = 0, dy = 0;
						if (direction.equalsIgnoreCase("up"))
							dy = 1;
						else if (direction.equalsIgnoreCase("right"))
							dx = 1;
						if (direction.equalsIgnoreCase("down"))
							dy = -1;
						else if (direction.equalsIgnoreCase("left"))
							dx = -1;

						// they have chosen a valid direction
						if (dx != 0 || dy != 0) {
							// board accepts the placement
							if (getBoard().placeShip(boat, move[0], move[1], dx, dy)) {
								wrong = false;
							} else {
								System.out.println("Bad placement");
							}
						} else {
							System.out.println("Invalid direction");
						}

					} else {
						System.out.println("Space not empty");
					}
				} else {
					System.out.println("Invalid coordinate");
				}
			}

		}
		setGameReady(true);

	}

	@Override
	public int[] play(Board enemy) {

		int[] coords = new int[2];

		// show boards
		System.out.println("Your board");
		getBoard().showBoard(true, true);
		System.out.println();
		System.out.println("Enemy board");
		enemy.showBoard(false, true);

		boolean correct = false;
		do {
			System.out.print("\nPlease enter your move: ");
			String input = scan.nextLine().toUpperCase().replaceAll(" ", "");

			// user doesn't wish to play any more
			if (input.equals("QUIT")) {
				if (sureOfQuit()) {
					coords[0] = -1;
					correct = true;
				}
				// user would like assistance
			} else if (input.equals("HELP")) {
				printHelp();
				// is this a valid move?
			} else if (validateInput(input)) {
				// have they already targeted it?
				if (enemy.isEmpty(move[0], move[1])) {
					coords = move;
					correct = true;
				} else {
					System.out.println("Cell not empty");
				}
			} else {
				System.out.println("Invalid input, please type help of you're struggling");
			}

		} while (!correct);

		return coords;
	}

	private void printHelp() {
		System.out.println("Please enter your move in the following format");
		System.out.println("\tXY");
		System.out.println("Where X is a letter and y is a number");
	}

	private boolean sureOfQuit() {
		System.out.print("Are you sure? (y/n) ");
		String yesNo = scan.nextLine();
		if (yesNo.isEmpty())
			return false;
		return yesNo.charAt(0) == 'y' || yesNo.charAt(0) == 'Y';
	}

	private boolean validateInput(String input) {
		Matcher match = validMove.matcher(input);

		// valid move
		if (match.matches()) {
			// letter
			String x = match.group(1);
			// number
			String y = match.group(2);
			// ACSII maths
			this.move[0] = x.charAt(0) - 'A';
			// this should cause no exceptions due to regex pattern check first
			this.move[1] = Integer.parseInt(y) - 1;

			return true;
		}

		return false;
	}

}
