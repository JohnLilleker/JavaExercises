package logic;

import java.util.regex.Pattern;

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

	@Override
	public boolean update(String... params) {

		/*
		 * title 0, author 1, yearPublished 2, numberOfPages 3, university 4, course 5,
		 * copies 6
		 */

		if (params.length != 7)
			return false;

		if (!Pattern.matches("^\\d+$", params[2]))
			return false;
		if (!Pattern.matches("^\\d+$", params[3]))
			return false;
		if (!Pattern.matches("^\\d+$", params[6]))
			return false;

		setTitle(params[0]);
		setAuthor(params[1]);
		setYearPublished(Integer.parseInt(params[2]));
		setNumberOfPages(Integer.parseInt(params[3]));
		setUniversity(params[4]);
		setCourse(params[5]);
		setStockLevel(Integer.parseInt(params[6]));

		return true;
	}
}
