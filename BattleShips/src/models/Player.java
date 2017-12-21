package models;

public abstract class Player {
	private String name;
	private Board board;
	private boolean gameReady = false;

	public Player(String name) {
		this.name = name;
		this.board = new Board();
	}

	public String getName() {
		return name;
	}

	public Board getBoard() {
		return board;
	}

	public int callHit(int[] m) {
		return board.hitCell(m[0], m[1]);
	}

	public abstract void setBoard();

	public abstract int[] play(Board enemy);

	public boolean hasLost() {
		return board.isDead();
	}

	public boolean hasSetBoard() {
		return gameReady;
	}

	protected void setGameReady(boolean ready) {
		gameReady = ready;
	}

	public void resetBoard() {
		board.reset();
		setGameReady(false);
	}
}
