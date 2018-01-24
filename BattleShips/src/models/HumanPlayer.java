package models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

		boolean placed = false;

		// repeat everything
		do {
			System.out.println("Do you want to place the fleet manually or from a file? ");

			System.out.println("1 - Manually using command line");
			System.out.println("2 - From a file");

			System.out.print("Please choose an option: ");

			String input = scan.nextLine();

			if (input.contains("1")) {
				placeShipsCMD();

				System.out.print("Do you want to save the ship positions to a file? ");

				input = scan.nextLine().trim().toLowerCase();

				if (input.charAt(0) == 'y') {
					// write to file
					System.out.print("What do you want to call the file? ");
					input = scan.nextLine();
					writeFleetToFile(input);
				}

				placed = true;

			} else if (input.contains("2")) {
				System.out.print("Enter the file path: ");
				input = scan.nextLine();
				String status = placeShipsFile(input);

				if (status.equals("")) {
					getBoard().printBoard(true, false);
					System.out.print("\nAre you happy with this configuration? ");
					input = scan.nextLine().trim().toLowerCase();
					if (input.charAt(0) == 'y') {
						placed = true;
					}
				} else {
					getBoard().reset();
					System.out.println("There was a problem with the file\n" + status);
				}
			} else {
				System.out.println("Invalid input");
			}
		} while (!placed);

		setGameReady(true);

	}

	private void writeFleetToFile(String file) {
		BufferedWriter bw = null;

		try {
			bw = new BufferedWriter(new FileWriter(file));

			for (Ship boat : getBoard().getFleet()) {
				bw.write(getBoard().boatName(boat) + " " + boat.getLocation() + " " + boat.getDirection());
				bw.newLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bw != null)
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	@Override
	public int[] play(Board enemy) {

		// show boards
		System.out.println("Your board");
		getBoard().printBoard(true, true);
		System.out.println();
		System.out.println("Enemy board");
		enemy.printBoard(false, true);

		String input;
		do {
			System.out.print("\nPlease enter your move: ");
			input = scan.nextLine().toUpperCase().replaceAll(" ", "");

			// true if the user gives a string that indicates turn is over
		} while (!parseInput(input, enemy));

		return move;
	}

	private boolean parseInput(String input, Board enemy) {
		// user doesn't wish to play any more
		if (input.equals("QUIT")) {
			if (sureOfQuit()) {
				move[0] = -1;
				return true;
			}
			// user would like assistance
		} else if (input.equals("HELP")) {
			printHelp();
			// is this a valid move?
		} else if (validateInput(input)) {
			// have they already targeted it?
			if (enemy.isEmpty(move[0], move[1])) {
				return true;
			} else {
				System.out.println("Cell not empty");
			}
		} else {
			System.out.println("Invalid input, please type help of you're struggling");
		}
		return false;
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

	// gets the user input from a scanner to place the ships
	private void placeShipsCMD() {
		ArrayList<Ship> fleet = getBoard().getFleet();

		for (Ship boat : fleet) {

			// tell them what they have to place
			getBoard().printBoard(true, false);
			System.out.println("\nPlacing a " + boat.getLength() + "x1 " + getBoard().boatName(boat));

			boolean wrong = true;

			// til they get it right
			while (wrong) {
				wrong = !tryToPlaceShip(boat);
			}

		}
	}

	private boolean tryToPlaceShip(Ship boat) {
		System.out.print("Enter a coordinate: ");
		String input = scan.nextLine().toUpperCase().replaceAll(" ", "");

		// valid coordinate?
		if (validateInput(input)) {
			// empty space?
			if (getBoard().getStatus(move[0], move[1]) == Board.EMPTY) {
				// good, pick a direction
				System.out.println("Placing at " + Board.coordinatesToString(move));
				System.out.print("Choose a direction; up, down, left or right: ");
				String direction = scan.nextLine().toUpperCase();

				try {
					if (placeShip(boat, move[0], move[1], Direction.valueOf(direction))) {
						boat.setDirection(direction);
						boat.setLocation(input);
						return true;
					} else {
						System.out.println("Bad placement");
					}
				} catch (Exception e) {
					System.out.println("Invalid direction");
				}

			} else {
				System.out.println("Space not empty");
			}
		} else {
			System.out.println("Invalid coordinate");
		}
		return false;
	}

	// sets the ship positions from a text file
	// this would be even better using a gui
	public String placeShipsFile(String file) {
		// file format
		// shipname xy direction

		BufferedReader br = null;
		// valid patterns

		try {

			br = new BufferedReader(new FileReader(file));

			String input;
			int line = 1;

			while ((input = br.readLine()) != null) {
				String lineinfo = line + " : " + input;

				String[] information = input.toUpperCase().split(" ");

				if (information.length != 3)
					return "Not enough information on line " + lineinfo;

				// make the information clearer
				String name = information[0], coords = information[1], direction_s = information[2];

				// get the relevant ship [0]
				// information[0] = {Patrol, Destroyer, Submarine, Battleship, Carrier}
				Ship boat = getShip(name);

				if (boat == null)
					return "Invalid ship identifier on line " + lineinfo;

				// get the coordinates [1], run validateInput([1])
				if (!validateInput(coords))
					return "Bad coordinates on line " + lineinfo;

				// get the direction [2]
				Direction direction;
				try {
					direction = Direction.valueOf(direction_s);
				} catch (Exception e) {
					return "Bad direction on line " + lineinfo;
				}

				if (!placeShip(boat, move[0], move[1], direction)) {
					getBoard().printBoard(true, false);
					return "Failed to place the ship specified on line " + lineinfo;
				}

				boat.setDirection(direction_s);
				boat.setLocation(coords);

				line++;
			}

			for (Ship boat : getBoard().getFleet()) {
				if (boat.getLocation().equals("")) {
					return "Not all boats have been placed! You are missing a " + getBoard().boatName(boat);
				}
			}

			return "";

		} catch (IOException e) {
			return e.getMessage();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private Ship getShip(String shipName) {
		// given a string of get the
		// relevant ship
		// filter by shipsNotPlaced as well

		char[] ids = new char[2];

		switch (shipName) {
		case "CARRIER":
			ids[0] = '7';
			break;
		case "BATTLESHIP":
			ids[0] = '6';
			break;
		case "SUBMARINE":
			ids[0] = '5';
			break;
		case "DESTROYER":
			ids[0] = '4';
			ids[1] = '3';
			break;
		case "PATROL":
			ids[0] = '2';
			ids[1] = '1';
			break;
		default: // unknown ship name
			return null;
		}
		Ship search = null;
		for (Ship s : getBoard().getFleet()) {
			if ((s.getID() == ids[0] || s.getID() == ids[1]) && s.getLocation().equals("")) {
				search = s;
				break;
			}
		}

		return search;
	}

	private boolean placeShip(Ship boat, int x, int y, Direction direction) {
		int dx = 0, dy = 0;
		switch (direction) {
		case DOWN:
			dy = -1;
			break;
		case LEFT:
			dx = -1;
			break;
		case RIGHT:
			dx = 1;
			break;
		case UP:
			dy = 1;
			break;

		}

		return getBoard().placeShip(boat, move[0], move[1], dx, dy);
	}

}
