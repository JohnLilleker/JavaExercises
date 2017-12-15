package models;

import java.util.ArrayList;
import java.util.Scanner;

public class BarrenMoor {

	private Player player;
	private Treasure treasure;
	private ArrayList<Monster> monsters;
	private Scanner input;
	public static final int MOOR_SIZE = 20;

	public BarrenMoor() {

		input = new Scanner(System.in);
		initialiseTreasure();
		makeMonsters(MOOR_SIZE / 10);

		String name = getName();
		if (name.equalsIgnoreCase("quit")) {
			input.close();
			return;
		}
		player = new Player(name);

		if (introduction())
			gameLoop();

		input.close();
	}

	private void makeMonsters(int numberOfMonsters) {
		monsters = new ArrayList<>();

		for (int i = 0; i < numberOfMonsters; i++) {
			int monsterX;
			int monsterY;
			do {
				monsterX = (int) Math.round((Math.random() * MOOR_SIZE) - MOOR_SIZE / 2);
				monsterY = (int) Math.round((Math.random() * MOOR_SIZE) - MOOR_SIZE / 2);
			} while (monsterX == 0 && monsterY == 0);

			monsters.add(new Monster(monsterX, monsterY));
		}

	}

	// start story
	// boolean return means the player wishes to continue;
	private boolean introduction() {
		System.out.printf("Ok %s...", player.getName());
		pauseForEffect(750);
		System.out.println(" now SLEEP!");
		pauseForEffect(2500);

		System.out.println("You awaken to find yourself on a barren moor, called The Barren Moor\n");
		pauseForEffect(2500);
		System.out.println("You find you have the ability to look around, try it");
		Command c;
		do {
			System.out.println("Enter 'look'");
			c = getInput();
		} while (c != Command.LOOK && c != Command.QUIT);
		if (c == Command.QUIT)
			return false;

		System.out.println("You are surrounded by oppressive, close fog...");
		pauseForEffect(1500);
		System.out.println("You seem to be standing in water, dark and cold with no signs of animal life...");
		pauseForEffect(1500);
		System.out.println("Something catches your eye, a curious watch...");
		pauseForEffect(1500);
		System.out.println("It seems drawn to something, maybe a way out?");
		pauseForEffect(1500);
		System.out.println(
				"After overcoming the shock of being relocated to this desolation you find you can move freely, either north, south east or west");
		pauseForEffect(750);
		return true;
	}

	// get input, then create enum
	private Command getInput() {

		Command command = null;

		do {

			System.out.printf("%s > ", player.getName());
			String inputString = readConsole();
			for (Command c : Command.values()) {
				if (c.toString().startsWith(inputString.toUpperCase())) {
					command = c;
					break;
				}
			}
			if (command == null) {
				System.out.println("Not a valid Command");
			}
		} while (command == null);

		return command;
	}

	// prompt for name
	private String getName() {

		System.out.println("Welcome, player, to The Adventure of The Barren Moor!");
		System.out.println("Before I throw you to oblivion, I would like to know you better");
		System.out.println("What, pray tell, is your name?");
		String name;

		do {
			System.out.print("Please enter your name > ");
			name = readConsole();
		} while (name.equals(""));

		return name;
	}

	// place the treasure at a random coordinate
	private void initialiseTreasure() {
		int treasureX;
		int treasureY;
		do {
			treasureX = (int) Math.round((Math.random() * MOOR_SIZE) - MOOR_SIZE / 2);
			treasureY = (int) Math.round((Math.random() * MOOR_SIZE) - MOOR_SIZE / 2);
		} while (treasureX == 0 && treasureY == 0);
		treasure = new Treasure(treasureX, treasureY);

	}

	// while not game over, show state, get input, update game, repeat
	private void gameLoop() {

		while (!gameover()) {
			observe();
			Command command = getInput();
			if (!updateGame(command)) {
				// user quits
				break;
			}
		}
	}

	// player location and 'watch' reading
	private void observe() {
		double distance = player.getDistFromPeice(treasure);
		System.out.printf("\nPlayer is at %d, %d\n", player.getXPosition(), player.getYPosition());
		System.out.printf("The watch says %.3fm\n", distance);
	}

	private void printHelp() {
		System.out.println("Controls:");
		System.out.println(" north : move your character north");
		System.out.println(" east  : move your character east");
		System.out.println(" south : move your character south");
		System.out.println(" west  : move your character west");
		System.out.println(" look  : observe the surroundings, this could help in a fix");
		System.out.println(" help  : bring up this help message");
		System.out.println(" quit  : end the game");
		System.out.println();

	}

	// end of game cut scene (successful)
	private void endGame() {

		System.out.println("You found a Treasure Chest! You open it warily...");
		pauseForEffect(1500);
		System.out.println("It appears to be a stone of some kind...");
		pauseForEffect(1500);
		System.out.println("It draws you in...");
		pauseForEffect(1500);
		System.out.println("You reach for it...");
		pauseForEffect(1500);
		System.out.println("BANG!");
		pauseForEffect(2500);
		System.out.println("You find yourself in familiar surroundings again");
		pauseForEffect(750);
		System.out.println("Congratulations " + player.getName() + "! You escaped The Barren Moor!");

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

		for (Monster monster : monsters) {
			double distanceToMonster = player.getDistFromPeice(monster);
			int xDirection = monster.getXPosition() - player.getXPosition();
			int yDirection = monster.getYPosition() - player.getYPosition();

			// which direction is the treasure in?
			Command longitudinal = (yDirection > 0) ? Command.NORTH : Command.SOUTH;
			Command lateral = (xDirection > 0) ? Command.EAST : Command.WEST;
			Command direction = (Math.abs(xDirection) > Math.abs(yDirection)) ? lateral : longitudinal;

			if (distanceToMonster <= 5) {
				System.out.println("There is a large shape coming from the " + direction + ", move quickly");
			} else if (distanceToMonster <= 10 && monster.isAsleep()) {
				System.out.println("There is a prone figure to the " + direction + ", lets try to avoid it");
			} else if (distanceToMonster <= 10) {
				System.out.println("A shape is moving towards you from the " + direction + ", lets try to get away");
			} else if (distanceToMonster <= 25 && monster.isAsleep()) {
				System.out.println(
						"You have a feeling there is something big to the " + direction + ", probably best to avoid");
			} else if (distanceToMonster <= 25) {
				System.out.println("You can here noises to the " + direction + ", but it seems far away");
			}
		}

		double distanceToTreasure = player.getDistFromPeice(treasure);
		int xDirection = treasure.getXPosition() - player.getXPosition();
		int yDirection = treasure.getYPosition() - player.getYPosition();

		// which direction is the treasure in?
		Command longitudinal = (yDirection > 0) ? Command.NORTH : Command.SOUTH;
		Command lateral = (xDirection > 0) ? Command.EAST : Command.WEST;
		Command direction = (Math.abs(xDirection) > Math.abs(yDirection)) ? lateral : longitudinal;

		if (distanceToTreasure <= 5) {
			System.out.println("You can almost see a box to the " + direction);
		} else if (distanceToTreasure <= 10) {
			System.out.println("There is a strong light comming from the " + direction);
		} else if (distanceToTreasure <= 25) {
			System.out.println("There is a faint glow to the " + direction);
		} else {
			System.out.println("You are surrounded by dense fog");
		}

	}

	// return true if the game continues, false if the player quits
	// interprets the users command to update the game state
	private boolean updateGame(Command command) {
		switch (command) {
		case EAST:
			System.out.println("Heading East");
			player.move(1, 0);
			break;
		case LOOK:
			describeSurroundings();
			break;
		case NORTH:
			System.out.println("Heading North");
			player.move(0, 1);
			break;
		case QUIT:
			if (quit()) {
				System.out.println("Thanks for playing!");
				return false;
			}
			break;
		case SOUTH:
			System.out.println("Heading South");
			player.move(0, -1);
			break;
		case WEST:
			System.out.println("Heading West");
			player.move(-1, 0);
			break;
		case HELP:
			printHelp();
			break;
		case REVEAL:
			gameState();
			return true;

		}

		for (Monster monster : monsters) {
			monster.moveToPlayer(player);
		}

		return true;
	}

	private void gameState() {
		System.out.printf("Treasure is at %d, %d\n", treasure.getXPosition(), treasure.getYPosition());
		for (Monster m : monsters) {
			System.out.printf("Monster at %d, %d, asleep = %b\n", m.getXPosition(), m.getYPosition(), m.isAsleep());
		}
	}

	// checks for the end game state i.e. player has found the treasure
	private boolean gameover() {
		if (player.onTopOf(treasure)) {
			endGame();
			return true;
		}

		for (Monster monster : monsters) {
			if (monster.onTopOf(player)) {
				death();
				return true;
			}
		}

		return false;
	}

	// end game cut scene (failed via monster death)
	private void death() {
		System.out.println("A monster has caught you...");
		pauseForEffect(750);
		System.out.println("Your death is so sudden, your last thought is \"How am I to get home?\"...");
		pauseForEffect(1000);
		System.out.println("Game Over");
		pauseForEffect(500);
		System.out.println("Thanks for playing!");
	}

	// reset input and get next line
	private String readConsole() {
		input.reset();
		return input.nextLine();
	}

	// self explanatory. Thread.sleep with a try catch
	private void pauseForEffect(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
