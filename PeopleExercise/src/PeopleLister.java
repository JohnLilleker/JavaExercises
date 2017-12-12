import java.util.ArrayList;

public class PeopleLister {
	ArrayList<Person> people = new ArrayList<Person>();

	public void printList() {
		for (Person p : people) {
			System.out.println(p);
		}
	}

	public Person findPerson(String wanted) {

		Person toReturn = null;

		for (Person person : people) {
			if (person.getName().equals(wanted)) {
				toReturn = person;
				break;
			}
		}

		return toReturn;

	}
}
