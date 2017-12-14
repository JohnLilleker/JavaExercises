package fileIO;

public class Main {

	public static void main(String[] args) {
		PeopleHandler people = new PeopleHandler();

		people.addPerson(new Person("Jeff", 27, "Professional Cinema Frequentee"));
		people.addPerson(new Person("Bob", 36, "Police officer"));
		people.addPerson(new Person("Tom", 60, "Night Watchmen"));
		people.addPerson(new Person("Dave", 50, "Second Technician on the JMC Red Dwarf"));
		people.addPerson(new Person("Nick", 22, "Wanderer and Survivor"));

		System.out.println(people);

		people.toFile("people.txt");

		PeopleHandler readFromFile = PeopleHandler.fromFile("people.txt");

		System.out.println(readFromFile);
	}

}
