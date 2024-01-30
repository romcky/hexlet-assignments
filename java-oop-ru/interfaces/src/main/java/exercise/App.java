package exercise;

import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class App {
    public static List<String> buildApartmentsList(List<Home> lst, int cnt) {
        return lst.stream()
            .sorted(Home::compareTo)
            .limit(cnt)
            .map(h -> h.toString())
            .collect(Collectors.toList());
    }
}
// END
