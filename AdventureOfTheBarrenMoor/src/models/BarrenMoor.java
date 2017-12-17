package models;

import java.util.ArrayList;
import java.util.Scanner;

public class BarrenMoor {

	private Player player;
	private Treasure treasure;
	private ArrayList<Monster> monsters;
	private Scanner input;
	private int width;
	private int height;
	private boolean playerHasMap;
	private Map map;

	// where all the scenes and print strings are stored
	private Story scenes;

	public BarrenMoor(int width, int height, String script) {

		this.width = width;
		this.height = height;
		this.playerHasMap = false;
		scenes = new Story(script);

		input = new Scanner(System.in);
		initialiseTreasures();
		monsters = new ArrayList<>();
		makeMonsters(width / 10);

		String name = getName();
		if (name.equalsIgnoreCase("quit")) {
			input.close();
			return;
		}
		player = new Player(name);

		this.scenes.setVariable("name", player.getName());
		if (introduction())
			gameLoop();

		input.close();
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	// make a number of monsters and add them to The Barren Moor
	private void makeMonsters(int numberOfMonsters) {

		for (int i = 0; i < numberOfMonsters; i++) {
			int[] monsterPos = placePeice();

			monsters.add(new Monster(monsterPos[0], monsterPos[1]));
		}

	}

	// start story
	// boolean return means the player wishes to continue (true) or quit early on
	// (false)
	private boolean introduction() {
		showScene("intro1");
		String c;
		do {
			System.out.println("Enter 'look'");
			System.out.printf("%s > ", player.getName());
			c = readConsole();
		} while (!(c.equalsIgnoreCase("look") || c.equalsIgnoreCase("quit")));
		if (c.equalsIgnoreCase("quit"))
			return false;

		showScene("intro2");
		return true;
	}

	// get input, then create enum
	private Command getInput() {

		Command command = null;
		// tell them about "help" if they are struggling
		int wrongCount = 0;
		// prevent the loop condition being called multiple times, it's relatively
		// complex
		boolean wrong = true;

		do {
			if (wrongCount > 2)
				System.out.println("If you don't know what to do, type \"help\"");
			System.out.printf("%s > ", player.getName());
			String inputString = readConsole();
			if (inputString.equals(""))
				continue;
			for (Command c : Command.values()) {
				if (c.toString().startsWith(inputString.toUpperCase())) {
					command = c;
					break;
				}
			}
			wrong = command == null || (command == Command.MAP && !playerHasMap);
			if (wrong) {
				System.out.println("Not a valid Command");
				wrongCount++;
			}
		} while (wrong);

		return command;
	}

	// prompt for name
	private String getName() {

		showScene("welcome");
		String name;

		do {
			System.out.print("Please enter your name > ");
			name = readConsole();
		} while (name.equals(""));

		return name;
	}

	// place the treasure at random coordinates as well as any other objects
	private void initialiseTreasures() {
		int[] treasurePos = placePeice();
		treasure = new Treasure(treasurePos[0], treasurePos[1]);

		int[] mapPos = placePeice();
		map = new Map(mapPos[0], mapPos[1]);

	}

	// finds a random position on the moor that the player is not on
	private int[] placePeice() {
		int posX;
		int posY;
		do {
			posX = (int) Math.round((Math.random() * width) - width / 2);
			posY = (int) Math.round((Math.random() * height) - height / 2);
		} while (posX == 0 && posY == 0); // player always starts at 0,0
		return new int[] { posX, posY };
	}

	// while not game over, show state, get input, update game, repeat
	private void gameLoop() {

		while (!gameover()) {
			System.out.println();
			observe();
			Command command = getInput();
			if (!updateGame(command)) {
				// user quits
				break;
			}
		}
	}

	// 'watch' reading
	private void observe() {
		double distance = player.getDistFromPeice(treasure);

		if (!playerHasMap) {
			double distanceToMap = player.getDistFromPeice(map);

			// map closer than treasure
			if (distance >= distanceToMap) {
				distance = distanceToMap;
			}
		}
		scenes.setVariable("distance", String.format("%.3f", distance));
		showScene("readWatch");
	}

	// help menu, describes all relevant commands
	private void printHelp() {
		showScene("helpControls");
		if (playerHasMap)
			showScene("helpMap");
		showScene("helpOther");

	}

	// "Are you sure you want to quit?"
	private boolean quit() {
		System.out.println("Are you sure?");
		String yesOrNo = readConsole();
		return yesOrNo.toLowerCase().startsWith("y");
	}

	// called via > 'look'
	// writes something helpful, so either current nearby monsters or if you can see
	// 'the glow of treasure'
	private void describeSurroundings() {

		// each of these could be a scene
		for (Monster monster : monsters) {
			double distanceToMonster = player.getDistFromPeice(monster);
			scenes.setVariable("direction", directionOfPiece(monster));
			if (distanceToMonster <= 3) {
				showScene("monsterNear");
				;
			} else if (distanceToMonster <= 7 && monster.isAsleep()) {
				showScene("monsterMidSleep");
			} else if (distanceToMonster <= 7) {
				showScene("monsterMid");
			} else if (distanceToMonster <= 10) {
				// no need to check monster isAsleep, they wake when the player is < 5 distance
				showScene("monsterFar");
			}
		}

		double distanceToTreasure = player.getDistFromPeice(treasure);

		scenes.setVariable("direction", directionOfPiece(treasure));
		if (distanceToTreasure <= 2) {
			showScene("treasureClose");
		} else if (distanceToTreasure <= 4) {
			showScene("treasureMid");
		} else if (distanceToTreasure <= 7) {
			showScene("treasureFar");
		} else {
			showScene("farFromTreasure");
		}

	}

	// gets the direction of the given GamePiece from the player
	// returns either NORTH, SOUTH, EAST, WEST, NORTH / EAST, NORTH / WEST, SOUTH /
	// EAST or SOUTH / WEST
	private String directionOfPiece(GamePiece notPlayer) {

		String direction = "";

		int dx = notPlayer.getXPosition() - player.getXPosition();
		int dy = notPlayer.getYPosition() - player.getYPosition();

		// which direction is the treasure in?
		Command longitudinal = (dy > 0) ? Command.NORTH : Command.SOUTH;
		Command lateral = (dx > 0) ? Command.EAST : Command.WEST;

		int abs_dx = Math.abs(dx);
		int abs_dy = Math.abs(dy);

		if (3 * abs_dx / 4 > abs_dy || abs_dy == 0) {
			direction = lateral.toString();
		} else if (3 * abs_dy / 4 > abs_dx || abs_dx == 0) {
			direction = longitudinal.toString();
		} else {
			direction = longitudinal.toString() + " / " + lateral.toString();
		}

		return direction;
	}

	// return true if the game continues, false if the player quits
	// interprets the users command to update the game state
	private boolean updateGame(Command command) {

		switch (command) {

		case EAST:
			showScene("playerMoves-east");
			if (!player.move(1, 0, this)) {
				showScene("badMove");
			}
			break;

		case LOOK:
			describeSurroundings();
			break;

		case NORTH:
			showScene("playerMoves-north");
			if (!player.move(0, 1, this)) {
				showScene("badMove");
			}
			break;

		case QUIT:
			if (quit()) {
				showScene("playerQuits");
				return false;
			}
			return true;

		case SOUTH:
			showScene("playerMoves-south");
			if (!player.move(0, -1, this)) {
				showScene("badMove");
			}
			break;

		case WEST:
			showScene("playerMoves-west");
			if (!player.move(-1, 0, this)) {
				showScene("badMove");
			}
			break;

		case HELP:
			printHelp();
			// prevent monster eating the user while they learn how to play
			return true;

		case DEBUG:
			gameState();
			// this is a debug tool, the monsters can stay where they are
			return true;

		case MAP:
			// need to move here
			// otherwise, the map would show, then the monsters move
			// so you never see where they are, only where they were
			moveMonsters();
			showMoor();
			return true;
		}

		if (!playerHasMap && player.onTopOf(map)) {
			showScene("findMap");
			playerHasMap = true;
		}

		moveMonsters();

		return true;
	}

	// moves the monsters, waking them if needed
	private void moveMonsters() {
		for (Monster monster : monsters) {

			if (monster.isAsleep() && player.getDistFromPeice(monster) <= 5) {
				String direction = this.directionOfPiece(monster);
				scenes.setVariable("direction", direction);
				showScene("wakeMonster");
				monster.wake();
			}
			monster.moveToPlayer(player, this);
		}
	}

	// debugging by printing the state of the game
	private void gameState() {
		System.out.printf("Player is at %d, %d\n", player.getXPosition(), player.getYPosition());
		System.out.printf("Treasure is at %d, %d\n", treasure.getXPosition(), treasure.getYPosition());

		String tense = "is";
		String status = "";
		if (playerHasMap) {
			tense = "was";
			status = ", but it has been collected";
		}
		System.out.printf("Map %s at %d, %d%s\n", tense, map.getXPosition(), map.getYPosition(), status);

		for (Monster m : monsters) {
			System.out.printf("Monster at %d, %d, asleep = %b\n", m.getXPosition(), m.getYPosition(), m.isAsleep());
		}
	}

	// Prints The Barren Moor
	private void showMoor() {
		char[][] map = new char[height + 1][width + 1];

		addToMap(player, map, 'o');
		addToMap(treasure, map, 'X'); // x marks the spot

		for (Monster monster : monsters) {
			addToMap(monster, map, '@');
		}

		printMoor(map);
	}

	// actually print the moor, the previous method creates the grid
	private void printMoor(char[][] moor) {

		System.out.print(" ");
		for (int i = 0; i < width + 1; i++) {
			System.out.print('-');
		}
		System.out.println(" ");
		for (int y = moor.length - 1; y >= 0; y--) {
			System.out.print('|');
			for (int x = 0; x < moor[y].length; x++) {
				System.out.print(moor[y][x]);
			}
			System.out.print('|');
			// compass points
			if (y == (height / 2) + 1) {
				System.out.println("   N");
			} else if (y == height / 2) {
				System.out.println(" W   E");
			} else if (y == (height / 2) - 1) {
				System.out.println("   S");
			} else {
				System.out.println();
			}
		}
		System.out.print(" ");
		for (int i = 0; i < width + 1; i++) {
			System.out.print('-');
		}
		System.out.println(" ");
	}

	// adds a GamePiece to the map used for showing The Barren Moor
	// Parameters are
	// piece, either the player, treasure or a monster
	// map, the char map used for displaying the game state
	// mark, the char that will be used to show the piece on the map
	private void addToMap(GamePiece piece, char[][] map, char mark) {
		// convert peice.coordinates to index
		// add mark to map
		int x = piece.getXPosition();
		int y = piece.getYPosition();

		int ix = x + width / 2;
		int iy = y + height / 2;

		map[iy][ix] = mark;

	}

	// checks for the end game state, either the player has the treasure or has died
	// checks for the end game state i.e. player has found the treasure
	private boolean gameover() {
		if (player.onTopOf(treasure)) {
			showScene("endGame-treasure");
			;
			return true;
		}

		for (Monster monster : monsters) {
			if (monster.onTopOf(player)) {
				showScene("endGame-death");
				;
				return true;
			}
		}

		return false;
	}

	// clear scanner and nextLine()
	// reset input and get next line
	private String readConsole() {
		input.reset();
		return input.nextLine();
	}

	// uses the story file to show text descriptions of what is happening
	private void showScene(String scene) {
		ArrayList<String> lines = scenes.getScene(scene);

		for (String line : lines) {
			// a 'dramatic' pause
			if (line.matches("^pause \\d+$")) {
				String duration = line.split(" ")[1];
				long time = Long.parseLong(duration);
				try {
					Thread.sleep(time);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				continue;
			}
			System.out.println(line);
		}
	}

}
