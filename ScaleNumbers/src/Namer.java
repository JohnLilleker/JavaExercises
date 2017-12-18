public class Namer {

	public static String nameNumber(String number) {
		if (!number.matches("\\d+"))
			return "Not a number!";

		String[] short_scale_key = { "", " thousand", " million", " billion", " trillion", " quadrillion",
				" quintillion", " sextillion" };
		String[] long_scale_key = { "", " thousand", " million", " milliard", " billion", " billiard", " trillion",
				" trilliard" };

		int tooBig = number.length() - short_scale_key.length * 3;
		if (tooBig > 0) {
			String msg = "I cannot deal with numbers that big, here is what I can do\n";
			return msg + nameNumber(number.substring(tooBig));
		}

		StringBuilder shortScale = new StringBuilder();
		StringBuilder longScale = new StringBuilder();

		for (int i = short_scale_key.length - 1; i >= 0; i--) {
			int diff = number.length() - 3 * i;

			if (diff > 0) {

				if (diff == 0)
					diff = 3;

				String substr = number.substring(0, diff);

				while (substr.startsWith("0"))
					substr = substr.substring(1);

				if (!substr.equals("")) {

					if (shortScale.length() > 0) {
						if (i == 0) {
							shortScale.append(" and ");
							longScale.append(" and ");
						} else {
							shortScale.append(", ");
							longScale.append(", ");
						}
					}

					shortScale.append(substr).append(short_scale_key[i]);
					longScale.append(substr).append(long_scale_key[i]);
				}
				number = number.substring(diff);
			}
		}

		if (shortScale.length() == 0) {
			shortScale.append(0);
			longScale.append(0);
		}

		return shortScale.insert(0, "Short scale: ") + "\n" + longScale.insert(0, "Long scale: ");
	}
}
