package paintExercise;

import java.util.ArrayList;

public class PaintWizard {

	private ArrayList<Paint> paints = new ArrayList<>();

	public PaintWizard() {
		paints.add(new Paint("CheapoMax", 20, 19.99f, 10));
		paints.add(new Paint("AverageJoes", 15, 17.99f, 11));
		paints.add(new Paint("DuluxourousPaints", 10, 25.f, 20));
	}

	public Paint bestForFloor(double floorSize) {
		Paint best = null;
		// store the cheapest, purposefully large so anything should
		// pass first time
		double cheapest = Double.MAX_VALUE;

		for (Paint paint : paints) {

			// how much each tin can cover
			double coverage = (double) paint.getCapacity() * paint.getCoverage();

			// minimum needed for the floor size given
			double cansNeeded = Math.ceil(floorSize / coverage);

			double price = cansNeeded * paint.getPrice();

			if (price < cheapest) {
				cheapest = price;
				best = paint;
			}
		}

		return best;
	}

	public void addPaint(Paint paint) {
		paints.add(paint);
	}
}
