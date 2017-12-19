package anagrams;

public class Main {

	public static void main(String[] args) {
		AnagramCounter ac = new AnagramCounter();

		System.out.println(ac.findAnagrams("src/anagrams/words.txt"));
	}

}
