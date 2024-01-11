package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

// BEGIN
public class App {

	public static Map<String, Integer> getWordCount(String text) {
		var words = List.of(text.split(" ")); 
		var index = new HashMap<String, Integer>();
		for (var w : words) {
			Integer count = index.getOrDefault(w, 0) + 1;
			index.put(w, count);
		}
		index.remove("");
		return index;
	}

	public static String toString(Map<String, Integer> index) {
		var builder = new StringBuilder("{");
		var entries = index.entrySet();
		if (entries.size() > 0) {
			builder.append("\n");
		}
		for (var e : entries) {
			builder.append("  ")
				.append(e.getKey())
				.append(": ")
				.append(e.getValue())
				.append("\n");
        }
		return builder.append("}").toString();
	}
}
//END
