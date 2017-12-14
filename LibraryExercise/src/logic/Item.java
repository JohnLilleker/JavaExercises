package logic;

public abstract class Item implements Storable {
	private String title;
	private int yearPublished;
	private int numberOfPages;
	private String author;
	private int id;
	// stock level
	private int stockLevel;

	private boolean isCheckedOut = false;
	private static int bookID = 0;

	public Item(String title, String author, int yearPublished, int numberOfPages, int stockLevel) {
		super();
		this.title = title;
		this.author = author;
		this.yearPublished = yearPublished;
		this.numberOfPages = numberOfPages;
		this.stockLevel = stockLevel;
		id = bookID;
		bookID++;

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

}
