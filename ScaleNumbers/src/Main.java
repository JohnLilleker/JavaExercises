import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		System.out.println("Number formatter!");
		System.out.println("Type quit to end this madness!\n");

		Scanner in = new Scanner(System.in);

		String num;
		do {
			System.out.print("Enter a number: ");
			num = in.nextLine();
			System.out.println(Namer.nameNumber(num));
			System.out.println();
		} while (!num.equalsIgnoreCase("quit"));

		System.out.println("Bye!");
		in.close();

	}
}
