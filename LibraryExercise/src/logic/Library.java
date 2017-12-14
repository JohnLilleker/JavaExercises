package logic;

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

	public boolean checkOut(Item bookID, User userID) {
		return false;
	}

	public void checkIn(int bookID, int userID) {
		checkIn(getItemByID(bookID), getUserByID(userID));
	}

	public void checkIn(Item bookID, User userID) {

	}

	public boolean addItem(Item item) {
		return objects.add(item);
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
		return false;
	}

	// dissertation
	public boolean updateItem(int index, String title, String author, int yearPublished, int numberOfPages,
			String university, String course, int copies) {
		return false;
	}

	// newspaper
	public boolean updateItem(int index, String title, String author, int day, int month, int yearPublished,
			int numberOfPages, boolean isTabloid, int copies) {
		return false;
	}

	public boolean registerUser(User person) {
		return objects.add(person);
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
		return true;
	}

	/*
	 * file io
	 */
}