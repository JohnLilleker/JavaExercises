package logic;

// Parser class that takes a String and turns it into a User, Book, Newspaper or Dissertation 
public class StringToStorable {
	public static Storable parseToStorable(String info) throws Exception {

		Storable obj = null;
		String[] contents = info.split("@");

		// TODO the building of objects
		try {

			switch (contents[0]) {

			case "book": // getID(), getTitle(), getAuthor(), getPublisher(), getYearPublished(),
							// getNumberOfPages(), isHardBack(), getStockLevel(), isCheckedOut()
				// btw, Boolean.valueOf(string) is basically string.equalsIgnoreCase("true")
				break;

			case "dissertation": // getID(), getTitle(), getAuthor(), getYearPublished(), getNumberOfPages(),
									// getUniversity(), getCourse(), getStockLevel(), isCheckedOut()
				break;

			case "newspaper": // getID(), getTitle(), getAuthor(), getDay(), getMonth(), getYearPublished(),
								// getNumberOfPages(), getStockLevel(), isCheckedOut()
				break;

			case "user": // getID(), getName(), getAge(), items.toString()
				break;

			}

		} catch (ArrayIndexOutOfBoundsException outOfRange) {
			throw new Exception("Not enough information");
		} catch (NumberFormatException wrongType) {
			throw new Exception("Data in wrong format");
		}

		return obj;
	}
}