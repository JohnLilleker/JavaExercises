package fileIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PeopleHandler {
	private ArrayList<Person> people;

	public PeopleHandler() {
		people = new ArrayList<>();
	}

	public void addPerson(Person subject) {
		people.add(subject);
	}

	public boolean toFile(String file) {
		boolean successful = true;

		BufferedWriter bWriter = null;
		FileWriter fWriter = null;

		try {
			fWriter = new FileWriter(file);
			bWriter = new BufferedWriter(fWriter);

			for (Person person : people) {

				bWriter.write(person.toCSV());
				bWriter.newLine();

			}

		} catch (IOException e) {
			successful = false;
		} finally {
			try {
				if (bWriter != null)
					bWriter.close();
				if (fWriter != null)
					fWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return successful;

	}

	public static PeopleHandler fromFile(String file) {
		PeopleHandler toReturn = null;
		BufferedReader bReader = null;
		FileReader fReader = null;

		try {
			toReturn = new PeopleHandler();
			fReader = new FileReader(file);
			bReader = new BufferedReader(fReader);

			String input;
			while ((input = bReader.readLine()) != null) {
				String[] values = input.split(",");

				String name = values[0];
				int age = Integer.parseInt(values[1]);
				String occupation = values[2];

				toReturn.addPerson(new Person(name, age, occupation));
			}

		} catch (IOException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
			toReturn = null;
		} finally {
			try {
				if (bReader != null)
					bReader.close();
				if (fReader != null)
					fReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return toReturn;
	}

	public Person findPerson(String name) {
		Person wanted = null;
		for (Person person : people) {
			if (person.getName().equalsIgnoreCase(name)) {
				wanted = person;
				break;
			}
		}
		return wanted;
	}

	public int howManyPeople() {
		return people.size();
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (Person person : people) {
			builder.append(person.toString() + "\n");
		}
		return builder.toString();
	}
}
