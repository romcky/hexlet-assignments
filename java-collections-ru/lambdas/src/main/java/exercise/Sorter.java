package exercise;

import java.util.Comparator;
import java.util.Map;
import java.util.List;
import java.time.LocalDate;
import java.util.stream.Collectors;

// BEGIN
public class Sorter {
    public static List<String> takeOldestMans(List<Map<String, String>> users) {
        return users.stream()
            .filter(m -> "male".equals(m.get("gender")))
            .sorted((m1, m2) -> m1.get("birthday").compareTo(m2.get("birthday")))
            .map(m -> m.get("name"))
            .collect(Collectors.toList());
    }
}
// END
