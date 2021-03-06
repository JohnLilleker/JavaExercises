package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;

import fileIO.PeopleHandler;
import fileIO.Person;

public class PeopleHandlerTests {

	@Test
	public void testNoPeople() {
		PeopleHandler handler = new PeopleHandler();
		assertEquals(0, handler.howManyPeople());
	}

	@Test
	public void testAddingAPerson() {
		PeopleHandler handler = new PeopleHandler();

		handler.addPerson(new Person("Bob", 23, "A job"));
		assertEquals(1, handler.howManyPeople());
		assertNotNull(handler.findPerson("Bob"));
		assertEquals("Bob", handler.findPerson("Bob").getName());
		assertEquals(23, handler.findPerson("Bob").getAge());
		assertEquals("A job", handler.findPerson("Bob").getOccupation());
	}

	@Test
	public void testAddingMultiplePeople() {
		PeopleHandler handler = new PeopleHandler();

		handler.addPerson(new Person("Dave", 64, "Teacher"));
		handler.addPerson(new Person("Bob", 23, "A job"));
		handler.addPerson(new Person("Jane", 29, "Swimmer"));
		assertEquals(3, handler.howManyPeople());
		// can still find bob
		assertNotNull(handler.findPerson("Bob"));
		assertEquals("Bob", handler.findPerson("Bob").getName());
		assertEquals(23, handler.findPerson("Bob").getAge());
		assertEquals("A job", handler.findPerson("Bob").getOccupation());
	}

	@Test
	public void testWriteToFile() {
		fail("Not yet implemented, don't know how either");
	}

	@Test
	public void testReadFromFile() {
		PeopleHandler handler = PeopleHandler.fromFile("src/tests/TestRead.txt");
		assertNotEquals(null, handler);
		assertEquals(4, handler.howManyPeople());
		assertNotNull(handler.findPerson("Stu"));
	}

}
