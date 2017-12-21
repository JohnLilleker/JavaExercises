package primes;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PrimeNumberFinders primes = new PrimeNumberFinders();
		System.out.println("Starting");
		List<Integer> pr = primes.Sundaram(1000);
		System.out.println("There are " + pr.size() + " primes");
		pr.forEach(num -> System.out.println(num));
	}

}
