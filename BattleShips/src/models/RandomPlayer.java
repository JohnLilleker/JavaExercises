package models;

public class RandomPlayer extends Player {

	public RandomPlayer(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setBoard() {
		// TODO Auto-generated method stub
		setGameReady(true);
	}

	@Override
	public int[] play(Board enemy) {
		// TODO Auto-generated method stub
		return null;
	}

}
