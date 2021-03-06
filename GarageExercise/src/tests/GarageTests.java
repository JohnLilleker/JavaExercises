package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import garageExercise.*;

public class GarageTests {

	@Test
	public void testCreateEmptyGarage() {
		Garage g = new Garage();
		assertEquals(0, g.numberOfVehiclesInGarage());
	}

	@Test
	public void testGarageWith1Car() {
		Garage g = new Garage(new Bike(false, 2, 10, "1234"));
		assertEquals(1, g.numberOfVehiclesInGarage());
	}

	@Test
	public void testGarageWith3Cars() {
		Garage g = new Garage(new Bike(false, 2, 10, "1234"), new Tank(12, 10, 1000, "1235"),
				new Car("Mini", 4, 3, 27, "1236"));
		assertEquals(3, g.numberOfVehiclesInGarage());
	}

	@Test
	public void testAddVehicleToGarage() {
		Garage g = new Garage();
		g.addVehicle(new Tank(12, 10, 1000, "1235"));
		assertEquals(1, g.numberOfVehiclesInGarage());
	}

	@Test
	public void testGetVehicle() {
		Garage g = new Garage(new Bike(false, 2, 10, "1234"), new Car("Mini", 4, 3, 27, "1236"));
		Vehicle v = g.getVehicle("1234");
		assertNotNull(v);
		assertEquals(2, g.numberOfVehiclesInGarage());
		// check we have actually got back what we expect
		assertEquals("1234", v.getID());
		assertEquals(2, v.getWheels());
		assertEquals(2, v.getLength());
		assertEquals(10, v.getWeight());
		assertTrue(v instanceof Bike);
	}

	@Test
	public void testRemoveVehicleById() {
		Garage g = new Garage(new Bike(false, 2, 10, "1234"), new Tank(12, 10, 1000, "1235"),
				new Car("Mini", 4, 3, 27, "1236"));
		g.removeVehiclesByID("1234");

		assertEquals(2, g.numberOfVehiclesInGarage());
		assertNull(g.getVehicle("1234"));
		assertNotNull(g.getVehicle("1235"));
		assertNotNull(g.getVehicle("1236"));
	}

	@Test
	public void testRemoveVehicleByType() {
		Garage g = new Garage(new Bike(false, 2, 10, "1234"), new Tank(12, 10, 1000, "1235"),
				new Car("Mini", 4, 3, 27, "1236"), new Car("Robin Reliant", 3, 4, 30, "1237"),
				new Bike(true, 3, 30, "1238"), new Tank(6, 7, 750, "1239"));
		g.removeVehiclesByType("Tank");
		assertEquals(4, g.numberOfVehiclesInGarage());
		// tanks are gone
		assertNull(g.getVehicle("1235"));
		assertNull(g.getVehicle("1239"));
		// all else same
		assertNotNull(g.getVehicle("1236"));
		assertNotNull(g.getVehicle("1234"));
		assertNotNull(g.getVehicle("1237"));
		assertNotNull(g.getVehicle("1238"));
	}

	@Test
	public void testEmptyGarage() {
		Garage g = new Garage(new Bike(false, 2, 10, "1234"), new Tank(12, 10, 1000, "1235"),
				new Car("Mini", 4, 3, 27, "1236"), new Car("Robin Reliant", 3, 4, 30, "1237"),
				new Bike(true, 3, 30, "1238"), new Tank(6, 7, 750, "1239"));
		assertEquals(6, g.numberOfVehiclesInGarage());
		g.emptyGarage();
		assertEquals(0, g.numberOfVehiclesInGarage());
		assertNull(g.getVehicle("1234"));
		assertNull(g.getVehicle("1235"));
		assertNull(g.getVehicle("1236"));
		assertNull(g.getVehicle("1237"));
		assertNull(g.getVehicle("1238"));
		assertNull(g.getVehicle("1239"));
	}

	@Test
	public void testCalcBill() {
		Garage g = new Garage();
		// bike = weight * 25
		assertEquals(250, g.calculateBill(new Bike(false, 2, 10, "1234")));
		assertEquals(1000, g.calculateBill(new Car("Some made up type", 3, 2, 10, "1240")));
		assertEquals(75000, g.calculateBill(new Tank(1, 2, 10, "1241")));
	}

}
