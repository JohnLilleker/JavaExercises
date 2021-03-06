package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import logic.User;

public class UserTest {

	User subject;

	@Before
	public void setup() {
		subject = new User("Alice", 12);
	}

	@Test
	public void testAddBook() {
		subject.addItem(1);
		subject.addItem(3);
		ArrayList<Integer> expected = new ArrayList<>();
		expected.add(1);
		expected.add(3);
		assertEquals(expected, subject.getCurrentItems());
	}

	@Test
	public void testRemoveBookCorrect() {
		subject.addItem(1);
		subject.addItem(3);
		boolean worked = subject.removeItem(3);
		assertTrue(worked);
		ArrayList<Integer> expected = new ArrayList<>();
		expected.add(1);
		assertEquals(expected, subject.getCurrentItems());
	}

	@Test
	public void testRemoveBookWrong() {
		subject.addItem(1);
		subject.addItem(3);
		boolean worked = subject.removeItem(7);
		assertFalse(worked);
		ArrayList<Integer> expected = new ArrayList<>();
		expected.add(1);
		expected.add(3);
		assertEquals(expected, subject.getCurrentItems());
	}

}
