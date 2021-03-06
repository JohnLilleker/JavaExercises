package garageExercise;

import java.util.ArrayList;

public class Garage {

	private ArrayList<Vehicle> vehicles;

	public Garage(Vehicle... initialCars) {
		vehicles = new ArrayList<>();
		for (Vehicle car : initialCars) {
			vehicles.add(car);
		}
	}

	public void calculateBill() {
		for (Vehicle vehicle : vehicles) {
			System.out.println("Calculating bill on " + vehicle.getID() + ": " + calculateBill(vehicle));
		}
	}

	public int calculateBill(Vehicle vehicle) {
		if (vehicle instanceof Car) {
			return vehicle.getWeight() * 100;
		}
		if (vehicle instanceof Bike) {
			return vehicle.getWeight() * 25;
		}
		if (vehicle instanceof Tank) {
			return vehicle.getWeight() * 7500;
		}
		return 0;
	}

	public void printVehicles() {
		System.out.println("Garage");
		vehicles.forEach(vehicle -> System.out.println(" " + vehicle));
	}

	public Vehicle getVehicle(String id) {
		Vehicle target = null;

		for (Vehicle vehicle : vehicles) {
			if (vehicle.getID().equals(id)) {
				target = vehicle;
				break;
			}
		}

		return target;
	}

	public void addVehicle(Vehicle vehicle) {
		this.vehicles.add(vehicle);
	}

	public void removeVehiclesByID(String id) {
		vehicles.removeIf(vehicle -> vehicle.getID().equals(id));
	}

	public void removeVehiclesByType(String type) {

		ArrayList<Vehicle> toRemove = new ArrayList<>();

		for (Vehicle vehicle : vehicles) {
			if (vehicle.getClass().getSimpleName().equals(type)) {
				toRemove.add(vehicle);
			}
		}

		vehicles.removeAll(toRemove);
	}

	public void emptyGarage() {
		vehicles.clear();
	}

	public int numberOfVehiclesInGarage() {
		return vehicles.size();
	}

}
