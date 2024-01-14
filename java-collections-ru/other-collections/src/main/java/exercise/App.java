package exercise;

import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Objects;

// BEGIN
public class App {
        public static LinkedHashMap<String, String> genDiff(Map<String, Object> map1, Map<String, Object> map2) {
                var unsorted = new HashMap<String, String>();
                var addedSet = new HashSet<String>(map2.keySet());
                addedSet.removeAll(map1.keySet());
                addedSet.forEach(key -> unsorted.put(key, "added"));
                var deletedSet = new HashSet<String>(map1.keySet());
                deletedSet.removeAll(map2.keySet());
                deletedSet.forEach(key -> unsorted.put(key, "deleted"));
                var shareSet = new HashSet<String>(map1.keySet());
                shareSet.retainAll(map2.keySet());
                shareSet.forEach(key -> {
                        if (Objects.equals(map1.get(key), map2.get(key))) {
                                unsorted.put(key, "unchanged");
                        } else {
                                unsorted.put(key, "changed");
                        }
                });
                var sorted = new LinkedHashMap<String, String>();
                unsorted.keySet().stream()
                        .sorted(String::compareTo)
                        .forEach(key -> sorted.put(key, unsorted.get(key)));
                return sorted;
        }
}
//END
