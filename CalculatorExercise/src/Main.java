
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calculator calc = new Calculator();
		System.out.println(calc.calcuate(15, 3, 5));
		System.out.println(calc.calcuate(4, 2, 8));
		System.out.println(calc.calcuate(6, 2, 12));
		System.out.println(calc.calcuate(6, 2, 3));
		System.out.println(calc.calcuate(9, 12, 108));
		System.out.println(calc.calcuate(4, 16, 64));
		System.out.println(calc.calcuate(4, 2, 6));
		System.out.println();
		System.out.println(calc.calculate(2, 4, 6, 3));
		System.out.println(calc.calculate(1, 1, 2, 3));
		System.out.println(calc.calculate(4, 4, 3, 4));
		System.out.println(calc.calculate(8, 4, 3, 6));
		System.out.println(calc.calculate(9, 3, 1, 7));
	}

}
