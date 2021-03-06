package paintExercise;

public class Paint {
	private String name;
	private int capacity;
	private float price;
	private int coverage;

	public Paint(String name, int capacity, float price, int coverage) {
		this.name = name;
		this.capacity = capacity;
		this.price = price;
		this.coverage = coverage;
	}

	public String getName() {
		return name;
	}

	public int getCapacity() {
		return capacity;
	}

	public float getPrice() {
		return price;
	}

	public int getCoverage() {
		return coverage;
	}

	public String toString() {
		return String.format("%s (%d Litre) �%.2f. Covers %dm2 per litre", name, capacity, price, coverage);
	}
}
