package exercise;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

// BEGIN
public class App {
    public static int getCountOfFreeEmails(List<String> emails) {
        var freeDomains = List.of("gmail.com", "yandex.ru", "hotmail.com");
        return emails.stream()
            .filter(str -> !str.isEmpty())
            .map(str -> str.split("@"))
            .filter(arr -> arr.length == 2)
            .map(arr -> arr[1].toLowerCase())
            .filter(freeDomains::contains)
            .collect(Collectors.counting())
            .intValue();
    }
}
// END
