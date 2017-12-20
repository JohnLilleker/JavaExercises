package main;

import logic.Book;
import logic.Item;
import logic.Library;
import logic.User;

public class Main {
	public static void main(String[] args) {

		User u = new User("Subject", 0);

		Item i1 = new Book("A book", "A human", "Place", 2070, 3, true, 3),
				i2 = new Book("Another book", "Another human", "Place", 2070, 30, true, 0),
				i3 = new Book("And Another book", "And Another human", "Place", 2070, 300, true, 2);

		Library lib = new Library();

		lib.addItem(i3);
		lib.addItem(i2);
		lib.addItem(i1);
		lib.registerUser(u);

		u.addItem(i1.getID());
		u.addItem(i2.getID());

		lib.toFile("lib.txt");

	}

}
