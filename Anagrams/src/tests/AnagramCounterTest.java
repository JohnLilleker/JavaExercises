package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import anagrams.AnagramCounter;

public class AnagramCounterTest {

	@Test
	public void testSortString() {

		assertEquals("abcdef", (new AnagramCounter()).sortString("dfebac"));
	}

}
