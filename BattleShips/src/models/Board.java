package models;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {

	// constants

	// size of grid
	public static final int GRID_SIZE = 12;

	// ship destroyed
	public static final int DEAD = 3;
	// ship hit (red peg) and returned if ship not dead
	public static final int HIT = 2;
	// missed hit (white peg)
	public static final int MISS = 1;
	// nothing in this cell
	public static final int EMPTY = 0;
	// Error
	public static final int ERROR = -1;

	private int[][] grid;

	private ArrayList<Ship> fleet;

	public Board() {
		grid = new int[GRID_SIZE][GRID_SIZE];
		fleet = new ArrayList<Ship>(Arrays.asList(new Ship(2, '1'), new Ship(2, '2'), new Ship(3, '3'),
				new Ship(3, '4'), new Ship(3, '5'), new Ship(4, '6'), new Ship(5, '7')));
		// 1, 2 = patrol boats
		// 3, 4 = battleships
		// 5 = submarine
		// 6 = destroyer
		// 7 = carrier

		// sort the ships in descending size order
		// this will help with placement as the smaller ships are easier to fit
		fleet.sort((Ship s1, Ship s2) -> Integer.compare(s2.getLength(), s1.getLength()));

	}

	public boolean isDead() {
		return fleet.stream().allMatch((Ship ship) -> ship.isDead());
	}

	public ArrayList<Ship> getFleet() {
		return fleet;
	}

	/**
	 * Prints a representation of the board
	 * 
	 * @param showShips
	 *            Option to show ships, they won't be shown if false
	 * @param showFleetHealth
	 *            Toggle for an additional piece of information at the bottom of the
	 *            board describing the number of ships left and their 'health'
	 */
	public void printBoard(boolean showShips, boolean showFleetHealth) {

		int lineSize = 4 + GRID_SIZE * 2;
		for (int i = 0; i < lineSize; i++)
			System.out.print('-');
		System.out.println();
		for (int y = GRID_SIZE - 1; y >= 0; y--) {
			System.out.printf("%-2d |", (y + 1));
			for (int x = 0; x < GRID_SIZE; x++) {
				String print = "";
				switch (getStatus(x, y)) {
				case HIT:
					print = "X|";
					break;
				case MISS:
					print = "o|";
					break;
				case EMPTY:
					print = " |";
					break;
				default:
					if (showShips)
						print = "@|";
					else
						print = " |";
					break;
				}
				System.out.print(print);
			}
			System.out.println();
			for (int i = 0; i < lineSize; i++)
				System.out.print('-');
			System.out.println();
		}
		System.out.print("   |");
		for (int i = 0; i < GRID_SIZE; i++)
			System.out.printf("%c|", 'A' + i);

		System.out.println("\n");

		if (showFleetHealth) {
			// show the ships
			for (Ship boat : fleet) {
				String name = boatName(boat);

				String health = "";
				for (int i = 0; i < boat.getLength(); i++) {
					if (i < boat.getLives())
						health += "=";
					else
						health += "X";
				}
				System.out.println(name + ": " + health);
			}
		}

	}

	public String boatName(Ship boat) {
		String name = "";
		switch (boat.getID()) {
		case '1':
		case '2':
			// patrol boat
			name = "Patrol";
			break;
		case '3':
		case '4':
			// 'battleship'
			name = "Destroyer";
			break;
		case '5':
			// submarine
			name = "Submarine";
			break;
		case '6':
			// 'destroyer'
			name = "Battleship";
			break;
		case '7':
			// carrier
			name = "Carrier";
			break;
		}
		return name;
	}

	/**
	 * Attempts to place a ship on the board
	 * 
	 * @param ship
	 *            The ship object
	 * @param x
	 *            location of one end
	 * @param y
	 *            location of one end
	 * @param dx
	 *            the direction of the rest of the ship from the initial location
	 * @param dy
	 *            the direction of the rest of the ship from the initial location
	 * @return true if the ship has been placed correctly. If the ship overlaps
	 *         anything or leaves the grid this returns false
	 */
	public boolean placeShip(Ship ship, int x, int y, int dx, int dy) {

		for (int i = 0; i < ship.getLength(); i++) {
			int nx = x + (dx * i);
			int ny = y + (dy * i);

			int status = getStatus(nx, ny);

			if (status != EMPTY)
				return false;
		}

		for (int i = 0; i < ship.getLength(); i++)
			setCell(x + (dx * i), y + (dy * i), ship.getID());

		return true;
	}

	/**
	 * Looks up the int stored in a particular cell
	 * 
	 * @param x
	 *            x coordinate
	 * @param y
	 *            y coordinate
	 * @return the number stored or Board.ERROR if out of range
	 */
	public int getStatus(int x, int y) {
		if (outOfRange(x, y))
			return ERROR;
		return grid[y][x];
	}

	/**
	 * A check for invalid coordinates
	 * 
	 * @param x
	 *            An x coordinate
	 * @param y
	 *            A y coordinate
	 * @return true if the coordinates given are out of the grid
	 */
	public boolean outOfRange(int x, int y) {
		return (x < 0 || x >= GRID_SIZE || y < 0 || y >= GRID_SIZE);
	}

	/**
	 * Sets a particular cell with a mark
	 * 
	 * @param x
	 *            the x coordinate
	 * @param y
	 *            the y coordinate
	 * @param mark
	 *            The int to place in the grid
	 */
	private void setCell(int x, int y, int mark) {
		if (!outOfRange(x, y))
			grid[y][x] = mark;
	}

	/**
	 * Anonymous checking of a cell's status, checks if a cell has been called
	 * before without revealing what's in it
	 * 
	 * @param x
	 *            the x coordinate
	 * @param y
	 *            the y coordinate
	 * @return false if the cell is marked with HIT or MISS, true otherwise
	 */
	public boolean isEmpty(int x, int y) {
		if (outOfRange(x, y))
			return true;
		int stat = getStatus(x, y);
		return stat != MISS && stat != HIT;
	}

	/**
	 * Checks if a cell has been marked with Board.HIT
	 * 
	 * @param x
	 *            the x coordinate
	 * @param y
	 *            the y coordinate
	 * @return true if getStatus(x, y) == Board.HIT
	 */
	public boolean isHit(int x, int y) {
		return getStatus(x, y) == Board.HIT;
	}

	/**
	 * Checks if a cell has been marked with Board.MISS
	 * 
	 * @param x
	 *            the x coordinate
	 * @param y
	 *            the y coordinate
	 * @return true if getStatus(x, y) == Board.MISS
	 */
	public boolean isMiss(int x, int y) {
		return getStatus(x, y) == Board.MISS;
	}

	/**
	 * Calls the move on a cell and returns the result. This also marks the grid as
	 * well
	 * 
	 * @param x
	 *            move x coordinate
	 * @param y
	 *            move y coordinate
	 * @return either HIT {ship hit}, MISS {no hit} or DEAD {ship destroyed}
	 */
	public int hitCell(int x, int y) {
		if (outOfRange(x, y)) {
			return ERROR;
		}
		int status = getStatus(x, y);

		int mark = (status == EMPTY) ? MISS : HIT;
		setCell(x, y, mark);

		if (mark == HIT) {
			for (Ship boat : fleet) {
				if (boat.getID() == status) {
					boat.hit();
					if (boat.isDead())
						mark = DEAD;
					break;
				}
			}
		}

		return mark;
	}

	/**
	 * Helper method, converts the result of Player.play() to a relevant String
	 * 
	 * @param m
	 *            an array of {x, y}
	 * @return A string representation such as "B7" for {1, 6}
	 */
	public static String coordinatesToString(int[] m) {
		char letter = (char) ('A' + m[0]);
		return letter + String.valueOf(m[1] + 1);
	}

	public void reset() {
		fleet.forEach((Ship ship) -> {
			ship.fix();
			ship.setDirection("");
			ship.setLocation("");
		});

		for (int y = 0; y < GRID_SIZE; y++) {
			for (int x = 0; x < GRID_SIZE; x++) {
				this.grid[y][x] = EMPTY;
			}
		}
	}
}
