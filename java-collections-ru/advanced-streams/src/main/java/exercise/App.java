package exercise;

import java.util.stream.Collectors;
import java.util.Arrays;

// BEGIN
public class App {
        public static String getForwardedVariables(String content) {
                return content.lines()
                        .filter(s -> s.startsWith("environment="))
                        .map(s -> s.replace("environment=", "").replace("\"", ""))
                        .flatMap(s -> Arrays.stream(s.split(",")))
                        .filter(s -> s.startsWith("X_FORWARDED_"))
                        .map(s -> s.replace("X_FORWARDED_", ""))
                        .collect(Collectors.joining(","));
        }
}
//END
