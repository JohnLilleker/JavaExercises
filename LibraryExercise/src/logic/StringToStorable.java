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
				Item book = new Book(contents[2], contents[3], contents[4], Integer.parseInt(contents[5]),
						Integer.parseInt(contents[6]), contents[7].equalsIgnoreCase("true"),
						Integer.parseInt(contents[8]));
				obj = book;
				break;

			case "dissertation": // getID(), getTitle(), getAuthor(), getYearPublished(), getNumberOfPages(),
									// getUniversity(), getCourse(), getStockLevel(), isCheckedOut()
				Item diss = new Dissertation(contents[2], contents[3], Integer.parseInt(contents[4]),
						Integer.parseInt(contents[5]), contents[6], contents[7], Integer.parseInt(contents[8]));
				obj = diss;
				break;

			case "newspaper": // getID(), getTitle(), getAuthor(), getDay(), getMonth(), getYearPublished(),
								// getNumberOfPages(), getStockLevel(), isCheckedOut()
				Item news = new Newspaper(contents[2], contents[3], Integer.parseInt(contents[4]),
						Integer.parseInt(contents[5]), Integer.parseInt(contents[6]), Integer.parseInt(contents[7]),
						Integer.parseInt(contents[8]));
				obj = news;
				break;

			case "user": // getID(), getName(), getAge(), items.toString()
				User user = new User(contents[2], Integer.parseInt(contents[3]));
				for (String bookID : contents[4].substring(1, contents[4].length() - 1).split(", "))
					user.addItem(Integer.parseInt(bookID));
				obj = user;
				break;

			}
			obj.setID(Integer.parseInt(contents[1]));

		} catch (ArrayIndexOutOfBoundsException outOfRange) {
			throw new Exception("Not enough information");
		} catch (NumberFormatException wrongType) {
			throw new Exception("Data in wrong format");
		}

		return obj;
	}
}