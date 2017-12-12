public class Main {
	public static void main(String[] args) {
		System.out.println("\n People exercise\n");

		PeopleLister manager = new PeopleLister();

		Person p1 = new Person("Bob", 29, "Pokemon trainer");
		Person p2 = new Person("Phil", 36, "Mail-person");
		Person p3 = new Person("Tyler", 25, "Gym Leader");
		Person p4 = new Person("Dave", 10, "Headmaster");

		manager.people.add(p1);
		manager.people.add(p2);
		manager.people.add(p3);
		manager.people.add(p4);

		manager.printList();

		System.out.println();

		System.out.println(manager.findPerson("Tyler"));
	}

}
