package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import paintExercise.PaintWizard;

public class PaintTests {

	@Test
	public void testBestForSmallestFloor() {
		PaintWizard pw = new PaintWizard();
		assertEquals("AverageJoes", pw.bestForFloor(20).getName());
	}

	@Test
	public void testBestForSmallFloor() {
		PaintWizard pw = new PaintWizard();
		assertEquals("AverageJoes", pw.bestForFloor(164).getName());
	}

	@Test
	public void testBestForMediumFloor() {
		PaintWizard pw = new PaintWizard();
		// because you would need 2 cans of AverageJoes, so this is cheapest
		assertEquals("CheapoMax", pw.bestForFloor(170).getName());
	}

	@Test
	public void testBestForLargeFloor() {
		PaintWizard pw = new PaintWizard();
		// need 3 tins of Cheap, 2 tins of Average
		assertEquals("AverageJoes", pw.bestForFloor(250).getName());
	}

	@Test
	public void testBestForLargestFloor() {
		PaintWizard pw = new PaintWizard();
		// 2 Cheap, 3 Average
		assertEquals("CheapoMax", pw.bestForFloor(400).getName());
	}

}
