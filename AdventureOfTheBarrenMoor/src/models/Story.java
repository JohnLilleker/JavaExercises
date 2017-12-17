package models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

// A way to customise the story / cut-scenes during the Adventure of the Barren Moor
public class Story {
	private HashMap<String, String> variables = new HashMap<>();
	// may or may not be Strings for the key
	private HashMap<String, ArrayList<String>> scenes = new HashMap<>();

	public Story(String file) {
		FileReader fReader = null;
		BufferedReader bReader = null;

		try {
			fReader = new FileReader(file);
			bReader = new BufferedReader(fReader);

			String line;
			String tag = "";
			ArrayList<String> scene = new ArrayList<>();
			boolean readingScene = false;

			while ((line = bReader.readLine()) != null) {
				line = line.trim();

				// start of a scene, also the tag to identify the scene on recall
				if (line.startsWith(":")) {
					tag = line.substring(1);
					readingScene = true;
					continue;
				}

				// end the current scene
				if (line.equalsIgnoreCase("#end")) {
					scenes.put(tag, scene);
					readingScene = false;
					scene = new ArrayList<>();
					continue;
				}

				// build the scene
				if (readingScene) {
					scene.add(line);
				}

				// anything else ignored
				// could make it more robust, but nah

			}

		} catch (IOException e) {
			System.out.println("Error reading story file " + file + "\n" + e.getMessage());
		} finally {
			try {
				if (fReader != null) {
					fReader.close();
				}
				if (bReader != null) {
					bReader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void setVariable(String variable, String value) {
		// change variable to the format used in scenes
		this.variables.put("%" + variable + "%", value);
	}

	public ArrayList<String> getScene(String sceneTag) {
		ArrayList<String> scene = new ArrayList<>();

		String tagToUse = sceneTag;

		// can be used to search for tags that are useful, but not exactly the same
		// for example 'endGame' instead of 'endGame-win'
		while (!scenes.containsKey(tagToUse)) {
			if (!tagToUse.contains("-")) {
				tagToUse = "";
				break;
			}
			// strip off the string the last '-' onwards
			tagToUse = tagToUse.substring(0, tagToUse.lastIndexOf('-'));
		}

		// no tag found
		if (tagToUse.equals(""))
			return scene;

		for (String line : scenes.get(tagToUse)) {
			// now replace the variables with the values
			String str = line;
			for (Entry<String, String> var : variables.entrySet()) {
				str = str.replace(var.getKey(), var.getValue());
			}

			scene.add(str);
		}

		return scene;
	}

}
