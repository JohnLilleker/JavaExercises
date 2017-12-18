package logic;

public class Newspaper extends Item {
	private int day;
	private int month;

	public Newspaper(String title, String author, int day, int month, int yearPublished, int numberOfPages,
			int copies) {
		super(title, author, yearPublished, numberOfPages, copies);
		this.day = day;
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	@Override
	public boolean update(String... params) {
		return false;
	}
}
