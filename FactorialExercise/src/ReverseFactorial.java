
public class ReverseFactorial {

	public String reFactorial(int x) {

		// can check for error if set to null
		String toReturn = null;

		// possible factorial
		int i = 1;

		while (x != 1) {
			// inc. i
			i++;

			// if x will not divide by i, it isn't a factorial
			if (x % i != 0) {
				// return NONE
				toReturn = "NONE";
				break;
			}
			// divide
			x = x / i;
		}

		// if no errors, i is the factorial
		if (toReturn == null) {
			toReturn = i + "!";
		}

		return toReturn;
	}
}
