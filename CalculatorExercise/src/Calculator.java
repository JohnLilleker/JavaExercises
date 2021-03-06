import java.util.ArrayList;
import java.util.Collections;

public class Calculator {

	public ArrayList<String> calcuate(int a, int b, int c) {
		ArrayList<String> combinations = new ArrayList<String>();

		ArrayList<Integer> sorted = new ArrayList<>();
		sorted.add(a);
		sorted.add(b);
		sorted.add(c);

		Collections.sort(sorted);

		int lowest = sorted.get(0);
		int middle = sorted.get(1);
		int highest = sorted.get(2);

		if (highest == lowest * middle) {
			combinations.add(lowest + " * " + middle + " = " + highest);
			combinations.add(middle + " * " + lowest + " = " + highest);
			combinations.add(highest + " / " + middle + " = " + lowest);
			combinations.add(highest + " / " + lowest + " = " + middle);

		} else if (highest == lowest + middle) {
			combinations.add(lowest + " + " + middle + " = " + highest);
			combinations.add(middle + " + " + lowest + " = " + highest);
			combinations.add(highest + " - " + middle + " = " + lowest);
			combinations.add(highest + " - " + lowest + " = " + middle);
		}

		return combinations;
	}

	public ArrayList<String> calculate(int a, int b, int c, int d) {
		ArrayList<String> combinations = new ArrayList<String>();

		ArrayList<Integer> sorted = new ArrayList<>();
		sorted.add(a);
		sorted.add(b);
		sorted.add(c);
		sorted.add(d);

		// not so scary brute force
		for (int i = 0; i < sorted.size(); i++) {
			for (int j = 0; j < sorted.size(); j++) {
				if (i == j)
					continue;
				for (int k = 0; k < sorted.size(); k++) {
					if (k == j || k == i)
						continue;
					int x_i = sorted.get(i), x_j = sorted.get(j), x_k = sorted.get(k);
					if (x_i + x_j == x_k) {
						combinations.add(x_i + " + " + x_j + " = " + x_k);
					}
					if (x_i - x_j == x_k) {
						combinations.add(x_i + " - " + x_j + " = " + x_k);
					}
					if (x_i * x_j == x_k) {
						combinations.add(x_i + " * " + x_j + " = " + x_k);
					}
					if (x_i / x_j == x_k && x_i % x_j == 0) {
						combinations.add(x_i + " / " + x_j + " = " + x_k);
					}
				}
			}
		}

		Collections.sort(sorted);

		return combinations;
	}
}
