package logic;

import java.util.ArrayList;

public class User implements Storable {
	private String name;
	private int id;
	// items the user has. Store ids as no real need to give objects I feel
	private ArrayList<Integer> items;
	private int age;

	public User(String name, int age) {
		super();
		this.name = name;
		this.age = age;
		items = new ArrayList<>();

		id = -1;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public ArrayList<Integer> getCurrentItems() {
		return items;
	}

	public void addItem(int index) {
		items.add(index);
	}

	public boolean removeItem(int index) {
		int i;
		for (i = 0; i < items.size(); i++) {
			if (items.get(i) == index)
				break;
		}
		if (i == items.size()) {
			return false;
		} else {
			items.remove(i);
			return true;
		}

	}

	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	@Override
	public boolean update(String... params) {

		// not enough parameters
		if (params.length != 2)
			return false;

		// second parameter must be a number
		if (!params[1].matches("^\\d+$"))
			return false;

		this.setName(params[0]);
		this.setAge(Integer.parseInt(params[1]));
		return true;

	}

	@Override
	public String toFileFormat() {
		return String.format("user@%d@%s@%d@%s", getID(), getName(), getAge(), items.toString());
	}
}
