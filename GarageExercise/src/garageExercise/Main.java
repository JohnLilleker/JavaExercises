package garageExercise;

public class Main {
	public static void main(String[] args) {
		System.out.println("\n Garage exercise\n");

		Garage g = new Garage(new Car("Morris minor", 4, 5, 750, "IJ90 TOK"), new Bike(false, 1, 10, "The pink one"));

		g.printVehicles();
		g.addVehicle(new Tank(25, 20, 100000, "Scary one in the corner"));
		System.out.println();

		g.calculateBill();
		g.printVehicles();
		System.out.println();

		g.removeVehiclesByID("The pink one");
		g.printVehicles();
		System.out.println();

		g.addVehicle(new Tank(25, 20, 100000, "Scary blue one in the corner"));
		g.addVehicle(new Tank(25, 20, 100000, "Scary red one in the corner"));
		g.addVehicle(new Tank(25, 20, 100000, "Scary green one in the corner"));
		g.addVehicle(new Tank(25, 20, 100000, "Scary black one in the corner"));
		g.addVehicle(new Tank(25, 20, 100000, "Scary orange one in the corner"));
		g.addVehicle(new Tank(25, 20, 100000, "Scary pink one in the corner"));
		g.printVehicles();
		g.removeVehiclesByType("Tank");
		g.printVehicles();
		System.out.println();

		g.emptyGarage();
		g.printVehicles();
	}
}
