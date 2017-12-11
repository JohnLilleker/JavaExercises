import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

		System.out.println("\n People exercise\n");
		Person p1 = new Person("Bob", 29, "Pokemon trainer");
		Person p2 = new Person("Phil", 36, "Mail-person");
		Person p3 = new Person("Tyler", 25, "Gym Leader");
		Person p4 = new Person("Dave", 10, "Headmaster");

		ArrayList<Person> people = new ArrayList<>();
		people.add(p1);
		people.add(p2);
		people.add(p3);
		people.add(p4);

		for (Person p : people) {
			System.out.println(p);
		}

		System.out.println();

		System.out.println(findPerson(people, "Tyler"));

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

	private static Person findPerson(ArrayList<Person> people, String wanted) {

		Person toReturn = null;

		for (Person p : people) {
			if (p.getName().equals(wanted)) {
				toReturn = p;
				break;
			}
		}

		return toReturn;

	}

}
