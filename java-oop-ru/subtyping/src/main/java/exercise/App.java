package exercise;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

// BEGIN
public class App {
    public static void swapKeyValue(KeyValueStorage db) {
        var tmpMap = new HashMap<String, String>(db.toMap());
        for (var key : tmpMap.keySet()) {
            db.unset(key);
        }
        for (var entry : tmpMap.entrySet()) {
            db.set(entry.getValue(), entry.getKey());
        }
    }
}
// END
