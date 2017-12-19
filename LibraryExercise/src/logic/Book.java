package logic;

public class Book extends Item {

	private boolean isHardBack;
	private String publisher;

	public Book(String title, String author, String publisher, int yearPublished, int numberOfPages, boolean isHardBack,
			int copies) {
		super(title, author, yearPublished, numberOfPages, copies);
		this.isHardBack = isHardBack;
		this.publisher = publisher;
	}

	public boolean isHardBack() {
		return isHardBack;
	}

	public void setHardBack(boolean isHardBack) {
		this.isHardBack = isHardBack;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	@Override
	public boolean update(String... params) {

		/*
		 * String title 0, String author 1, String publisher 2, int yearPublished 3, int
		 * numberOfPages 4, boolean isHardBack 5, int copies 6
		 */

		if (params.length != 7)
			return false;

		if (!isNumber.matcher(params[3]).matches())
			return false;
		if (!isNumber.matcher(params[4]).matches())
			return false;
		if (!params[5].matches("true|false"))
			return false;
		if (!isNumber.matcher(params[6]).matches())
			return false;

		this.setTitle(params[0]);
		this.setAuthor(params[1]);
		this.setPublisher(params[2]);
		this.setYearPublished(Integer.parseInt(params[3]));
		this.setNumberOfPages(Integer.parseInt(params[4]));
		this.setHardBack(params[5].equalsIgnoreCase("true"));
		this.setStockLevel(Integer.parseInt(params[6]));

		return true;
	}

	@Override
	public String toFileFormat() {
		return String.format("book@%d@%s@%s@%s@%d@%d@%b@%d", getID(), getTitle(), getAuthor(), getPublisher(),
				getYearPublished(), getNumberOfPages(), isHardBack(), getStockLevel());
	}

}
