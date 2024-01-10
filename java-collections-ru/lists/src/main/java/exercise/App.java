package exercise;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

// BEGIN
import java.util.Map;
import java.util.stream.Collectors;

public class App {

	public static Map<Integer, Long> index(String str) {
		return str.chars().boxed().map(Character::toLowerCase)
			.collect(Collectors.groupingBy(ch -> ch, Collectors.counting()));
	}

	public static boolean scrabble(String strOfAvailable, String strOfRequired) {
		var indexOfAvailable = index(strOfAvailable);
		var indexOfRequiredEntries = index(strOfRequired).entrySet();
		for (var entry : indexOfRequiredEntries) {
			var required = entry.getValue();
			var available = indexOfAvailable.getOrDefault(entry.getKey(), 0L);
			if (required > available) {
				return false;
			}
		}
		return true;
	}
}
//END
