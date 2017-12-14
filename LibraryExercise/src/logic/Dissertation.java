package logic;

public class Dissertation extends Item {
	private String university;
	private String course;

	public Dissertation(String title, String author, int yearPublished, int numberOfPages, String university,
			String course, int copies) {
		super(title, author, yearPublished, numberOfPages, copies);
		this.university = university;
		this.course = course;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}
}
