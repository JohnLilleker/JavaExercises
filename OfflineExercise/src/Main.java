
public class Main {

	public static void main(String[] args) {
		System.out.println("Ex 1");
		System.out.println(doubleChar("The"));
		System.out.println(doubleChar("AAbb"));
		System.out.println(doubleChar("Hi-There"));

		System.out.println("\nEx 2");
		System.out.println(getSandwich("breadjambread"));
		System.out.println(getSandwich("xxbreadjambreadyy"));
		System.out.println(getSandwich("xxbreadyy"));

		System.out.println("\nEx 3");
		System.out.println(evenlySpaced(2, 4, 6));
		System.out.println(evenlySpaced(4, 6, 2));
		System.out.println(evenlySpaced(4, 6, 3));

		System.out.println("\nEx 4");
		System.out.println(nTwice("Hello", 2));
		System.out.println(nTwice("Chocolate", 3));
		System.out.println(nTwice("Chocloate", 1));

		System.out.println("\nEx 5");
		System.out.println(endsLy("oddly"));
		System.out.println(endsLy("y"));
		System.out.println(endsLy("oddy"));

		System.out.println("\nEx 6");
		System.out.println(stringClean("yyzzza"));
		System.out.println(stringClean("abbbcdd"));
		System.out.println(stringClean("Hello"));

		System.out.println("\nEx 7");
		System.out.println(fibonacci(0));
		System.out.println(fibonacci(1));
		System.out.println(fibonacci(2));
		System.out.println(fibonacci(7));

		System.out.println("\nEx 8");
		System.out.println(bunnyEars(0));
		System.out.println(bunnyEars(1));
		System.out.println(bunnyEars(2));

		System.out.println("\nEx 9");
		diamond(4);

	}

	public static String doubleChar(String msg) {
		StringBuilder builder = new StringBuilder(msg.length() * 2);
		for (int i = 0; i < msg.length(); i++) {
			builder.append(msg.charAt(i)).append(msg.charAt(i));
		}
		return builder.toString();
	}

	public static String getSandwich(String msg) {
		if (!msg.matches(".*bread.+bread.*"))
			return "";

		int firstBread = msg.indexOf("bread");

		int lastIndex = msg.lastIndexOf("bread");

		return msg.substring(firstBread + 5, lastIndex);
	}

	public static boolean evenlySpaced(int a, int b, int c) {
		int highest = 0, middle = 0, lowest = 0;

		if (a > b) {
			if (a > c) {
				highest = a;
				if (b > c) {
					middle = b;
					lowest = c;
				} else {
					middle = c;
					lowest = b;
				}
			} else {
				highest = c;
				middle = a;
				lowest = b;
			}
		} else if (b > c) {
			if (b > a) {
				highest = b;
				if (a > c) {
					middle = a;
					lowest = c;
				} else {
					middle = c;
					lowest = a;
				}
			} else {
				highest = a;
				middle = b;
				lowest = c;
			}
		} else if (c > a) {
			if (c > b) {
				highest = c;
				if (a > b) {
					middle = a;
					lowest = b;
				} else {
					middle = b;
					lowest = a;
				}
			} else {
				highest = b;
				middle = c;
				lowest = a;
			}
		}

		return highest - middle == middle - lowest;
	}

	public static String nTwice(String msg, int len) {
		StringBuilder builder = new StringBuilder(len * 2);

		for (int i = 0; i < len; i++) {
			builder.append(msg.charAt(i));
		}

		for (int i = msg.length() - len; i < msg.length(); i++) {
			builder.append(msg.charAt(i));
		}

		return builder.toString();
	}

	public static boolean endsLy(String msg) {
		return msg.endsWith("ly");
	}

	public static String stringClean(String msg) {
		if (msg.length() == 1) {
			return msg;
		}
		if (msg.charAt(0) != msg.charAt(1)) {
			return msg.charAt(0) + stringClean(msg.substring(1));
		} else {
			return stringClean(msg.charAt(0) + msg.substring(2));
		}
	}

	public static int fibonacci(int n) {
		if (n <= 0)
			return 0;
		if (n == 1)
			return 1;
		return fibonacci(n - 1) + fibonacci(n - 2);
	}

	public static int bunnyEars(int bunnies) {
		if (bunnies <= 0)
			return 0;
		return 2 + bunnyEars(bunnies - 1);
	}

	public static void diamond(int height) {
		// use \/ and +
		/*
		 * /\ /++\ \++/ \/
		 */
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < height; j++) {
				if (j == (height / 2 - i - 1)) {
					if (j < height / 2) {
						System.out.print("/");
					}
				} else if (j == (height / 2 + i)) {
					System.out.print("\\");
				} else if (j < (height / 2 - i) || j > (height / 2)) {
					System.out.print(" ");
				} else {
					System.out.print("+");
				}
			}
			System.out.println();
		}

	}
}
