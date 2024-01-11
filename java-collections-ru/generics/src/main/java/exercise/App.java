package exercise;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.stream.Collectors;

// BEGIN
class App {
	public static <K, V> List<Map<K, V>> findWhere(List<Map<K, V>> books, Map<K, V> where) {
		return books.stream()
			.filter(b -> b.entrySet().containsAll(where.entrySet()))
			.collect(Collectors.toList());
	}
}
//END
