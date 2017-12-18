package logic;

import java.util.regex.Pattern;

public abstract class Item implements Storable {
	private String title;
	private int yearPublished;
	private int numberOfPages;
	private String author;
	private int id;
	// stock level
	private int stockLevel;

	private boolean isCheckedOut = false;

	public Item(String title, String author, int yearPublished, int numberOfPages, int stockLevel) {
		super();
		this.title = title;
		this.author = author;
		this.yearPublished = yearPublished;
		this.numberOfPages = numberOfPages;
		this.stockLevel = stockLevel;
		id = -1;

	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setYearPublished(int yearPublished) {
		this.yearPublished = yearPublished;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public String getTitle() {
		return title;
	}

	public int getYearPublished() {
		return yearPublished;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public int getStockLevel() {
		return stockLevel;
	}

	public void setStockLevel(int stockLevel) {
		this.stockLevel = stockLevel;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public boolean isCheckedOut() {
		return isCheckedOut;
	}

	public void checkOut(boolean isCheckedOut) {
		this.isCheckedOut = isCheckedOut;
	}

	// in update, the values are given as strings. This helps prevent "dog" being
	// given to parseInt()
	//
	// Read as "at the start of the String, find 1 or more numbers, then the end of
	// the String"
	protected static final Pattern isNumber = Pattern.compile("^\\d+$");

}
