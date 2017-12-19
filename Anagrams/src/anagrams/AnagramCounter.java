package anagrams;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

public class AnagramCounter {

	private HashMap<String, ArrayList<String>> anagrams = new HashMap<>();

	public ArrayList<String> getWordsFromFile(String file) throws IOException {

		ArrayList<String> words = new ArrayList<>();
		BufferedReader bReader = null;

		try {
			bReader = new BufferedReader(new FileReader(file));
			String line;

			while ((line = bReader.readLine()) != null) {
				if (line.trim().length() != 0)
					words.add(line.trim());
			}

		} catch (IOException e) {
			throw e;
		} finally {
			try {
				if (bReader != null) {
					bReader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return words;
	}

	public String sortString(String str) {
		char[] charArray = str.toCharArray();

		Arrays.sort(charArray);

		return new String(charArray);
	}

	public String findAnagrams(String file) {
		anagrams.clear();
		String mostCommon = "No words found";
		try {
			mostCommon = countAnagrams(getWordsFromFile(file));
		} catch (IOException e) {
			System.err.println("Error in file " + file);
			e.printStackTrace();
		}

		return mostCommon;

	}

	private String countAnagrams(ArrayList<String> words) {
		for (String word : words) {
			String sorted = sortString(word);
			if (anagrams.containsKey(sorted)) {
				anagrams.get(sorted).add(word);
			} else {
				anagrams.put(sorted, new ArrayList<String>(Arrays.asList(word)));
			}
		}

		// used for multiple words the same
		ArrayList<String> bigWords = new ArrayList<>();
		String common = "";
		bigWords.add(common);
		int anagramNo = 0;

		for (Entry<String, ArrayList<String>> entry : anagrams.entrySet()) {
			String key = entry.getKey();
			int value = entry.getValue().size();
			if (value > anagramNo) {
				common = key;
				bigWords.clear();
				bigWords.add(key);
				anagramNo = value;
			} else if (entry.getValue().size() == anagramNo) {
				if (key.length() > common.length()) {
					common = key;
					bigWords.clear();
					bigWords.add(key);
					anagramNo = value;
				} else if (key.length() == common.length()) {
					bigWords.add(key);
				}
			}
		}
		for (int i = 1; i < bigWords.size(); i++) {
			common += "\n" + bigWords.get(i);
		}
		return common;
	}

}
