package garageExercise;

public abstract class Vehicle {
	protected int wheels;
	protected int length;
	protected int weight;

	protected String id;

	public Vehicle(String id, int wheels, int length, int weight) {
		this.wheels = wheels;
		this.length = length;
		this.weight = weight;
		this.id = id;
	}

	@Override
	public String toString() {
		return "Vehicle [wheels=" + wheels + ", length=" + length + ", weight=" + weight + ", id=" + id + "]";
	}

	public String getID() {
		return id;
	}

	public int getWheels() {
		return wheels;
	}

	public int getLength() {
		return length;
	}

	public int getWeight() {
		return weight;
	}

}
