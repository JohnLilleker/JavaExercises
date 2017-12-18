package logic;

import java.util.regex.Pattern;

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

		if (!Pattern.matches("^\\d+$", params[3]))
			return false;
		if (!Pattern.matches("^\\d+$", params[4]))
			return false;
		if (!Pattern.matches("true|false", params[5]))
			return false;
		if (!Pattern.matches("^\\d+$", params[6]))
			return false;

		this.setTitle(params[0]);
		this.setAuthor(params[1]);
		this.setPublisher(params[2]);
		this.setYearPublished(Integer.parseInt(params[3]));
		this.setNumberOfPages(Integer.parseInt(params[4]));
		this.setHardBack(params[5].equals("true"));
		this.setStockLevel(Integer.parseInt(params[6]));

		return true;
	}

}
