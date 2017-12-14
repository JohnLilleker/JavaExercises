package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import logic.Book;
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
		fail("Not yet implemented");
	}

	@Test
	public void testCheckIn() {
		fail("Not yet implemented");
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
		fail("Not yet implemented");
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
		fail("Not yet implemented");
	}

	@Test
	public void testUpdatePerson() {
		fail("Not yet implemented");
	}

	@Test
	public void testFileIO() {
		fail("Not yet implemented");
	}

}