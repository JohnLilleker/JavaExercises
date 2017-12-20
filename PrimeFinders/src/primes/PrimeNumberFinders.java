package primes;

import java.util.LinkedList;
import java.util.List;

public class PrimeNumberFinders {

	public List<Integer> Eratosthenes(int n) {

		// deletion will happen
		List<Integer> primes = new LinkedList<>();

		for (int i = 2; i <= n; i++) {
			primes.add(i);
		}

		for (int p_i = 0; p_i < primes.size(); p_i++) {
			int p = primes.get(p_i);

			for (int n_i = 0; n_i < primes.size(); n_i++) {
				int num = primes.get(n_i);
				if (num > p && num % p == 0) {
					primes.remove(n_i);
					n_i--;
				}
			}

		}

		return primes;
	}

	public List<Integer> Sundaram(int n) {

		n = (n - 1) / 2;

		List<Integer> numbers = new LinkedList<>();

		for (int i = 1; i <= n; i++) {
			numbers.add(i);
		}

		List<Integer> toRemove = new LinkedList<>();
		for (int j = 1; j < n; j++) {
			for (int i = 1; i <= j; i++) {
				toRemove.add(i + j + (2 * i * j));
			}
		}

		numbers.removeAll(toRemove);

		List<Integer> primes = new LinkedList<>();
		primes.add(2);
		for (Integer i : numbers) {
			primes.add((2 * i) + 1);
		}

		return primes;
	}

}
