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

		if (params.length != 7)
			return false;

		if (!isNumber.matcher(params[2]).matches())
			return false;
		if (!isNumber.matcher(params[3]).matches())
			return false;
		if (!isNumber.matcher(params[4]).matches())
			return false;
		if (!isNumber.matcher(params[5]).matches())
			return false;
		if (!isNumber.matcher(params[6]).matches())
			return false;

		setTitle(params[0]);
		setAuthor(params[1]);
		setDay(Integer.parseInt(params[2]));
		setMonth(Integer.parseInt(params[3]));
		setYearPublished(Integer.parseInt(params[4]));
		setNumberOfPages(Integer.parseInt(params[5]));
		setStockLevel(Integer.parseInt(params[6]));

		return true;
	}

	@Override
	public String toFileFormat() {
		return String.format("newspaper@%d@%s@%s@%d@%d@%d@%d@%d", getID(), getTitle(), getAuthor(), getDay(),
				getMonth(), getYearPublished(), getNumberOfPages(), getStockLevel());
	}
}
