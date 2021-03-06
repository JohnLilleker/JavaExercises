
public class Exercises {

	public static void main(String[] args) {
		Exercises ex = new Exercises();
		System.out.println("Exercise 1 -----------------------------");
		ex.ex1();
		System.out.println("Exercise 2 -----------------------------");
		ex.ex2();
		System.out.println("Exercise 3 -----------------------------");
		ex.ex3("Parameter passing");
		System.out.println("Exercise 4 -----------------------------");
		System.out.println(ex.ex4());
		System.out.println("Exercise 5 -----------------------------");
		System.out.println(ex.ex5(3, 4));
		System.out.println("Exercise 6 -----------------------------");
		System.out.println(ex.ex6(2, 3, true));
		System.out.println(ex.ex6(3, 3, false));
		System.out.println("Exercise 7 -----------------------------");
		System.out.println(ex.ex7(1, 0));
		System.out.println(ex.ex7(1, 2));
		System.out.println("Exercise 8 -----------------------------");
		ex.ex8(5);
		System.out.println("Exercise 9 -----------------------------");
		ex.ex9();
		System.out.println("Exercise 10 -----------------------------");
		ex.ex10();
		System.out.println("Exercise 11 -----------------------------");
		ex.ex11();
	}

	public void ex1() {
		System.out.println("Hello World!");
	}

	public void ex2() {
		String message = "Hello World!";
		System.out.println(message);
	}

	public void ex3(String message) {
		System.out.println(message);
	}

	public String ex4() {
		return "Hello World!";
	}

	public int ex5(int x, int y) {
		return x + y;
	}

	public int ex6(int x, int y, boolean sum) {
		if (sum)
			return x + y;
		else
			return x * y;
	}

	public int ex7(int x, int y) {
		if (x == 0)
			return y;
		if (y == 0)
			return x;
		return ex6(x, y, true);
	}

	public void ex8(int x) {
		for (int i = 0; i < 10; i++) {
			System.out.println(ex7(x, i));
		}
	}
	
	public void ex9() {
		int[] array = {1, 2 ,3 ,4, 5, 6, 7};

		System.out.println(ex7(array[0], array[3]));
		System.out.println(ex7(array[1], array[4]));
		System.out.println(ex7(array[2], array[5]));
	}
	
	public void ex10() {
		int[] array = {1, 2 ,3 ,4, 5, 6, 7};
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + ", ");
		}
		System.out.println();
	}
	
	public void ex11() {
		int[] array = new int[10];
		for (int i = 0; i < array.length; i++) {
			array[i] = i;
			System.out.print(array[i] + ", ");
		}
		System.out.println();
		
		for (int i = 0; i < array.length; i++) {
			array[i] *= 10;
			System.out.print(array[i] + ", ");
		}
		System.out.println();
	}
}
