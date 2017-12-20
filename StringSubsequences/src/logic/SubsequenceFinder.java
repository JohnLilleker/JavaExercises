package logic;

public class SubsequenceFinder {
	public String longestCommonSequence(String str1, String str2) {

		// same string
		if (str1.equals(str2))
			return str2;

		// one string actually contains the other
		if (str1.contains(str2))
			return str2;
		if (str2.contains(str1))
			return str1;

		return null;

	}
}
