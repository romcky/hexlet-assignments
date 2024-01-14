package exercise;

import java.util.Arrays;

// BEGIN
public class App {
        public static String[][] enlargeArrayImage(String[][] image) {
                return Arrays.stream(image).flatMap(arr -> {
                        String[] line = Arrays.stream(arr)
                                .flatMap(s -> Arrays.stream(new String[] {s, s}))
                                .toArray(String[]::new);
                        return Arrays.stream(new String[][] {line, line});
                }).toArray(String[][]::new);
        }
}
// END
