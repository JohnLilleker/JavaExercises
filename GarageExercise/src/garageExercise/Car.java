package garageExercise;
public class Car extends Vehicle {

	private String make;

	public Car(String make, int wheels, int length, int weight, String id) {
		super(id, wheels, length, weight);
		this.make = make;
	}

	public String toString() {
		return "Car [make=" + make + ", wheels=" + wheels + ", length=" + length + ", weight=" + weight + ", id=" + id
				+ "]";
	}

	public String getMake() {
		return make;
	}

}
