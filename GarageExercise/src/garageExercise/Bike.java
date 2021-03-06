package garageExercise;
public class Bike extends Vehicle {

	private boolean hasEngine;

	public Bike(boolean hasEngine, int length, int weight, String id) {
		super(id, 2, length, weight);
		this.hasEngine = hasEngine;
	}

	@Override
	public String toString() {
		return "Bike [hasEngine=" + hasEngine + ", length=" + length + ", weight=" + weight + ", id=" + id + "]";
	}

	public boolean isEnginePowered() {
		return hasEngine;
	}
}
