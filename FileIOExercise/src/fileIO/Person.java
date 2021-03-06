package fileIO;

public class Person {

	private String name;
	private int age;
	private String occupation;

	public Person(String name, int age, String occupation) {
		this.name = name;
		this.age = age;
		this.occupation = occupation;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public int getAge() {
		return age;
	}

	public void growOlder() {
		this.age++;
	}

	public String getName() {
		return name;
	}

	public String toCSV() {
		return name + "," + age + "," + occupation;
	}

	public String toString() {
		return String.format("This is %s. %s is %d years old and works as a %s", name, name, age, occupation);
	}
}
