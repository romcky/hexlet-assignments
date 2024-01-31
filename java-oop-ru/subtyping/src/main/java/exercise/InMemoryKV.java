package exercise;

import java.util.Map;
import java.util.HashMap;

// BEGIN
public class InMemoryKV implements KeyValueStorage {
    private final HashMap<String, String> map = new HashMap<>();
    
    public InMemoryKV(Map<String, String> map) {
        this.map.putAll(map);
    }
    
    @Override
    public void set(String key, String value) {
        map.put(key, value);
    }
    
    @Override
    public void unset(String key) {
        map.remove(key);
    }
    
    @Override
    public String get(String key, String defaultValue) {
        return map.getOrDefault(key, defaultValue);
    }
    
    @Override
    public Map<String, String> toMap() {
        var result = new HashMap<String, String>();
        result.putAll(map);
        return result;
    }
}
// END
