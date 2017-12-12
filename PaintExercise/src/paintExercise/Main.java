package paintExercise;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		PaintWizard paintWizard = new PaintWizard();

		Scanner scan = new Scanner(System.in);

		System.out.print("Enter size of room: ");

		double size = scan.nextDouble();

		System.out.println(paintWizard.bestForFloor(size));

		scan.close();
	}

}
