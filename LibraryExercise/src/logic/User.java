package logic;

import java.util.ArrayList;

public class User implements Storable {
	private String name;
	private int id;
	private ArrayList<Integer> items;
	private int age;

	private static int userID = 0;

	public User(String name, int age) {
		super();
		this.name = name;
		this.age = age;
		items = new ArrayList<>();

		id = userID;
		userID++;
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

	public ArrayList<Integer> getCurrentBooks() {
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
}