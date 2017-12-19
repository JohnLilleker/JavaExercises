package logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Library {
	private ArrayList<Storable> objects;

	public Library() {
		objects = new ArrayList<>();
	}

	public int getNumberOfItems() {
		return (int) objects.stream().filter(obj -> obj instanceof Item).count();
	}

	public int getNumberOfUsers() {
		return (int) objects.stream().filter(obj -> obj instanceof User).count();
	}

	public User getUserByID(int id) {
		User toReturn = null;
		for (Storable object : objects) {
			if (object.getID() == id && object instanceof User) {
				toReturn = (User) object;
				break;
			}
		}
		return toReturn;
	}

	public Item getItemByID(int id) {
		Item toReturn = null;
		for (Storable object : objects) {
			if (object.getID() == id && object instanceof Item) {
				toReturn = (Item) object;
				break;
			}
		}
		return toReturn;
	}

	public boolean checkOut(int bookID, int userID) {
		return checkOut(getItemByID(bookID), getUserByID(userID));
	}

	public boolean checkOut(Item book, User user) {

		// sanity check
		if (book == null || user == null)
			return false;

		int bookStockLevel = book.getStockLevel();
		// can't give books if none left
		if (bookStockLevel < 1)
			return false;

		// give the user the book
		user.addItem(book.getID());
		// reduce stock
		book.setStockLevel(bookStockLevel - 1);

		return true;
	}

	public boolean checkIn(int bookID, int userID) {
		return checkIn(getItemByID(bookID), getUserByID(userID));
	}

	public boolean checkIn(Item book, User user) {

		// sanity check
		if (book == null || user == null)
			return false;

		// hasn't got the book
		if (!user.getCurrentItems().contains(book.getID()))
			return false;

		// take the book
		user.removeItem(book.getID());
		// increment stock level
		book.setStockLevel(book.getStockLevel() + 1);

		return true;
	}

	public boolean addItem(Item item) {
		if (objects.add(item)) {
			return true;
		}
		return false;
	}

	public boolean removeItem(int id) {
		return removeItem(getItemByID(id));

	}

	public boolean removeItem(Item item) {
		int i;
		for (i = 0; i < objects.size(); i++) {
			if (item == objects.get(i))
				break;
		}
		if (i == objects.size())
			return false;

		objects.remove(i);
		return true;
	}

	// book
	public boolean updateItem(int index, String title, String author, String publisher, int yearPublished,
			int numberOfPages, boolean isHardBack, int copies) {

		for (Storable storable : objects) {
			if (storable.getID() == index && storable instanceof Book)
				return storable.update(title, author, publisher, String.valueOf(yearPublished),
						String.valueOf(numberOfPages), String.valueOf(isHardBack), String.valueOf(copies));
		}

		return false;
	}

	// dissertation
	public boolean updateItem(int index, String title, String author, int yearPublished, int numberOfPages,
			String university, String course, int copies) {
		for (Storable storable : objects) {
			if (storable.getID() == index && storable instanceof Dissertation)
				return storable.update(title, author, String.valueOf(yearPublished), String.valueOf(numberOfPages),
						university, course, String.valueOf(copies));
		}
		return false;
	}

	// newspaper
	public boolean updateItem(int index, String title, String author, int day, int month, int yearPublished,
			int numberOfPages, int copies) {
		for (Storable storable : objects) {
			if (storable.getID() == index && storable instanceof Newspaper)
				return storable.update(title, author, String.valueOf(day), String.valueOf(month),
						String.valueOf(yearPublished), String.valueOf(numberOfPages), String.valueOf(copies));
		}
		return false;
	}

	public boolean registerUser(User person) {
		if (objects.add(person)) {
			return true;
		}
		return false;
	}

	public boolean deleteUser(int index) {
		int i;
		for (i = 0; i < objects.size(); i++) {
			if (index == objects.get(i).getID() && objects.get(i) instanceof User)
				break;
		}
		if (i == objects.size())
			return false;
		objects.remove(i);
		return true;
	}

	public boolean updateUser(int index, String name, int age) {

		for (Storable storable : objects) {
			if (storable.getID() == index && storable instanceof User) {
				return storable.update(name, String.valueOf(age));
			}
		}

		return false;
	}

	public ArrayList<Item> getAllItems() {
		ArrayList<Item> items = new ArrayList<>();

		for (Storable s : objects) {
			if (s instanceof Item) {
				items.add((Item) s);
			}
		}
		return items;
	}

	public ArrayList<User> getAllUsers() {
		ArrayList<User> users = new ArrayList<>();

		for (Storable s : objects) {
			if (s instanceof User) {
				users.add((User) s);
			}
		}
		return users;
	}

	public boolean toFile(String file) {
		boolean worked = true;
		BufferedWriter bwriter = null;

		try {
			bwriter = new BufferedWriter(new FileWriter(file));

			for (Storable s : objects) {
				bwriter.write(s.toFileFormat());
				bwriter.newLine();
			}

		} catch (IOException e) {
			worked = false;
		} finally {
			if (bwriter != null)
				try {
					bwriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return worked;
	}

	// This needs to be static, creating a new Library from a file doesn't depend on
	// another being created
	// This can throw an exception, if something goes wrong, i.e. wrong format or
	// file not found, the higher user NEEDS to know
	public static Library fromFile(String file) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader breader = null;
		Library lib = null;
		int line = 1;

		try {
			breader = new BufferedReader(new FileReader(file));

			lib = new Library();

			String input;

			while ((input = breader.readLine()) != null) {
				try {

					Storable s = StringToStorable.parseToStorable(input);
					lib.objects.add(s);
				} catch (Exception e) {
					throw new IOException("Error in parsing line " + line + "\n" + e.getMessage());
				}

				line++;
			}

		} catch (IOException ex) {
			// re-throw exception
			throw ex;
		} finally { // the whole reason for the try-catch
			if (breader != null) {
				System.out.println("in finally");
				breader.close();
			}
		}

		return lib;
	}
}
