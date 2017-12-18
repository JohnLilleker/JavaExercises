package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import logic.Book;
import logic.Dissertation;
import logic.Item;
import logic.Library;
import logic.Newspaper;
import logic.User;

public class LibraryTest {

	Library library;

	@Before
	public void setup() {
		library = new Library();
	}

	@Test
	public void testEmptyLibrary() {
		assertEquals(0, library.getNumberOfItems());
		assertEquals(0, library.getNumberOfUsers());
	}

	@Test
	public void testRightNumbersOfUsersAndItems() {
		for (int i = 0; i < 3; i++)
			library.registerUser(new User("bob", i));
		for (int j = 0; j < 5; j++)
			library.addItem(new Book("dfn", "njgmnhf", "jgnjuj", j + 24, j * 10, false, j));

		assertEquals(5, library.getNumberOfItems());
		assertEquals(3, library.getNumberOfUsers());
	}

	@Test
	public void testCheckOut() {
		User u = new User("Subject", 0);

		Item i1 = new Book("A book", "A human", "Place", 2070, 3, true, 3),
				i2 = new Book("Another book", "Another human", "Place", 2070, 30, true, 0);

		library.registerUser(u);
		library.addItem(i1);
		library.addItem(i2);

		boolean success = library.checkOut(i1.getID(), u.getID());
		// successful transaction
		assertTrue(success);
		// book registered to "Subject"
		assertTrue(u.getCurrentItems().contains(i1.getID()));
		// stock updated
		assertEquals(2, i1.getStockLevel());

		// bad transaction
		// no stock
		assertFalse(library.checkOut(i2.getID(), u.getID()));
		// bad user id (safe as can never be -1)
		assertFalse(library.checkOut(i1.getID(), -1));
		// bad book id (safe as can never be -1)
		assertFalse(library.checkOut(-1, u.getID()));
	}

	@Test
	public void testCheckIn() {
		User u = new User("Subject", 0);

		Item i1 = new Book("A book", "A human", "Place", 2070, 3, true, 3),
				i2 = new Book("Another book", "Another human", "Place", 2070, 30, true, 0),
				i3 = new Book("And Another book", "And Another human", "Place", 2070, 300, true, 2);

		library.addItem(i1);
		library.addItem(i2);
		library.addItem(i3);
		library.registerUser(u);

		// already has i2 and an i3
		u.addItem(i2.getID());
		u.addItem(i3.getID());

		// successful transaction
		assertTrue(library.checkIn(i2.getID(), u.getID()));
		// has no longer got i2
		assertFalse(u.getCurrentItems().contains(i2.getID()));

		// hasn't checked in all books
		assertTrue(u.getCurrentItems().contains(i3.getID()));

		// number of i2's have gone up
		assertEquals(1, i2.getStockLevel());

		// user hasn't got i1, so cannot check it in
		assertFalse(library.checkIn(i1.getID(), u.getID()));
		// bad user id (safe as can never be -1)
		assertFalse(library.checkIn(i3.getID(), -1));
		// bad book id (safe as can never be -1)
		assertFalse(library.checkIn(-1, u.getID()));
	}

	@Test
	public void testAddItem() {
		Item book = new Book("A book", "A human", "Place", 2070, 3, true, 3);
		assertTrue(library.addItem(book));
		assertEquals(1, library.getNumberOfItems());
		assertEquals(0, library.getNumberOfUsers());
	}

	@Test
	public void testFindItemByID() {
		Item toFind = new Newspaper("The day", "editor", 32, 2, 19010, -17, 4);
		library.addItem(toFind);
		assertEquals(toFind, library.getItemByID(toFind.getID()));
	}

	@Test
	public void testRemoveItem() {
		Item toDelete = new Book("A book", "A human", "Place", 2070, 3, true, 3);
		library.addItem(new Book("Another book", "A human", "Place", 2070, 3, true, 3));
		library.addItem(new Book("And another book", "A human", "Place", 2070, 3, true, 3));
		library.addItem(toDelete);
		library.addItem(new Newspaper("The day", "editor", 32, 2, 19010, -17, 4));

		assertTrue(library.removeItem(toDelete.getID()));
		assertNull(library.getItemByID(toDelete.getID()));
		assertFalse(library.removeItem(toDelete.getID()));
	}

	@Test
	public void testUpdateItem() {
		// TODO
		Book book = new Book("A book", "A human", "Place", 2070, 3, true, 3);
		Newspaper news = new Newspaper("The day", "editor", 32, 2, 19010, 17, 4);
		Dissertation diss = new Dissertation("I did work", "A. Student", 90, 5, "University", "Principles of Stuff", 2);

		library.addItem(book);
		library.addItem(news);
		library.addItem(diss);

		assertTrue("Expected correct update",
				library.updateItem(book.getID(), "Book", book.getAuthor(), book.getPublisher(), 2015, 90, true, 5));
		assertTrue("Expected correct update", library.updateItem(news.getID(), "The Evening", news.getAuthor(), 12, 2,
				2014, 30, news.getStockLevel()));

		// only change year and stock
		assertTrue("Expected correct update", library.updateItem(diss.getID(), diss.getTitle(), diss.getAuthor(), 2017,
				diss.getNumberOfPages(), diss.getUniversity(), diss.getCourse(), 5));

		assertEquals("Book", book.getTitle());
		assertEquals("A human", book.getAuthor());
		assertEquals(2015, book.getYearPublished());

		assertEquals("The Evening", news.getTitle());
		assertEquals("editor", news.getAuthor());
		assertEquals(30, news.getNumberOfPages());

		assertEquals("I did work", diss.getTitle());
		assertEquals(2017, diss.getYearPublished());
		assertEquals(5, diss.getStockLevel());

		// using wrong update on something...
		// the overload is expecting a newspaper
		assertFalse("Expected bad update", library.updateItem(book.getID(), null, null, 1, 13, 19732, 958, 4));

		assertEquals("Book", book.getTitle());
		assertEquals("A human", book.getAuthor());
		assertEquals(2015, book.getYearPublished());

	}

	@Test
	public void testRegisterPerson() {

		User subject = new User("Human", -73);
		assertTrue(library.registerUser(subject));
		assertEquals(0, library.getNumberOfItems());
		assertEquals(1, library.getNumberOfUsers());
	}

	@Test
	public void testFindPersonByID() {
		User subject = new User("Human", -73);
		library.registerUser(subject);
		assertEquals(subject, library.getUserByID(subject.getID()));
	}

	@Test
	public void testDeletePerson() {
		User u1 = new User("A", 1), u2 = new User("B", 2);

		library.registerUser(u1);
		library.registerUser(u2);

		assertTrue(library.deleteUser(u1.getID()));
		assertNull(library.getUserByID(u1.getID()));
		assertFalse(library.deleteUser(u1.getID()));
		assertNotNull(library.getUserByID(u2.getID()));
	}

	@Test
	public void testDifferentLists() {
		Item i1 = new Book("A book", "A human", "Place", 2070, 3, true, 3),
				i2 = new Book("Another book", "A human", "Place", 2070, 3, true, 3),
				i3 = new Book("And another book", "A human", "Place", 2070, 3, true, 3);
		User u1 = new User("A", 1), u2 = new User("B", 2);

		library.addItem(i1);
		library.addItem(i2);
		library.addItem(i3);
		library.registerUser(u1);
		library.registerUser(u2);

		ArrayList<Item> items = new ArrayList<Item>();
		items.add(i1);
		items.add(i2);
		items.add(i3);

		ArrayList<User> users = new ArrayList<User>();
		users.add(u1);
		users.add(u2);

		assertEquals(items, library.getAllItems());
		assertEquals(users, library.getAllUsers());
	}

	@Test
	public void testUpdatePerson() {

		User u1 = new User("Person One", 9);
		User u2 = new User("Person Two", 6);

		library.registerUser(u1);
		library.registerUser(u2);

		assertTrue(library.updateUser(u1.getID(), "Bob", 26));

		assertEquals("Bob", u1.getName());
		assertEquals(26, u1.getAge());

		assertEquals("Person Two", u2.getName());
		assertEquals(6, u2.getAge());

	}

	@Test
	public void testFileIO() {

		User u = new User("Subject", 0);

		Item i1 = new Book("A book", "A human", "Place", 2070, 3, true, 3),
				i2 = new Book("Another book", "Another human", "Place", 2070, 30, true, 0),
				i3 = new Book("And Another book", "And Another human", "Place", 2070, 300, true, 2);

		library.addItem(i1);
		library.addItem(i2);
		library.addItem(i3);
		library.registerUser(u);

		// test the check out thing
		library.checkOut(i1.getID(), u.getID());

		assertTrue(library.toFile("lib.txt"));

		Library libraryRecovered = null;
		try {
			libraryRecovered = Library.fromFile("lib.txt");
		} catch (IOException e) {
			fail("Shouldn't have caused an exception, but did\n" + e.toString());
		}

		assertNotNull(libraryRecovered);

		ArrayList<Item> items = new ArrayList<Item>(Arrays.asList(i1, i2, i3));

		ArrayList<User> users = new ArrayList<User>(Arrays.asList(u));

		assertEquals(items, libraryRecovered.getAllItems());
		assertEquals(users, libraryRecovered.getAllUsers());

	}

}
