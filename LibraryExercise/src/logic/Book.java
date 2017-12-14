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

}