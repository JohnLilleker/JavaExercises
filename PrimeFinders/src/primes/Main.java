package primes;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PrimeNumberFinders primes = new PrimeNumberFinders();
		List<Integer> pr = primes.Sundaram(1000000);
		System.out.println("There are " + pr.size() + " primes");
		pr.forEach(num -> System.out.println(num));
	}

}
