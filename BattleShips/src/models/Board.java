package models;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {
	public static final int GRID_SIZE = 12;

	// constants
	public static final int DEAD = 3;
	public static final int HIT = 2;
	public static final int MISS = 1;
	public static final int EMPTY = 0;
	public static final int ERROR = -1;

	private final static String[] X_AXIS = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L" };

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

	}

	public boolean isDead() {
		return fleet.stream().allMatch((Ship ship) -> ship.isDead());
	}

	public ArrayList<Ship> getFleet() {
		return fleet;
	}

	public void showBoard(boolean showShips) {

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
			System.out.printf("%s|", X_AXIS[i]);

		System.out.println();
	}

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

	public int getStatus(int x, int y) {
		if (outOfRange(x, y))
			return ERROR;
		return grid[y][x];
	}

	public boolean outOfRange(int x, int y) {
		return (x < 0 || x >= GRID_SIZE || y < 0 || y >= GRID_SIZE);
	}

	public void setCell(int x, int y, int mark) {
		if (!outOfRange(x, y))
			grid[y][x] = mark;
	}

	public boolean cellUsed(int x, int y) {
		if (outOfRange(x, y))
			return true;

		int stat = getStatus(x, y);

		return stat == MISS || stat == HIT;
	}

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

	public static String coordinatesToString(int[] m) {
		return X_AXIS[m[0]] + (m[1] + 1);
	}
}
