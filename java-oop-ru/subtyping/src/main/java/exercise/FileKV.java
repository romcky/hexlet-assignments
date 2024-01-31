package exercise;

import java.util.Map;

// BEGIN
public class FileKV implements KeyValueStorage {
    private final String filePath;
    
    public FileKV(String filePath, Map<String, String> map) {
        this.filePath = filePath;
        Utils.writeFile(filePath, Utils.serialize(map));
    }
    
    @Override
    public void set(String key, String value) {
        var map = Utils.unserialize(Utils.readFile(filePath));
        map.put(key, value);
        Utils.writeFile(filePath, Utils.serialize(map));
    }
    
    @Override
    public void unset(String key) {
    	var map = Utils.unserialize(Utils.readFile(filePath));
        map.remove(key);
        Utils.writeFile(filePath, Utils.serialize(map));
    }
    
    @Override
    public String get(String key, String defaultValue) {
    	var map = Utils.unserialize(Utils.readFile(filePath));
        return map.getOrDefault(key, defaultValue);
    }
    
    @Override
    public Map<String, String> toMap() {
        return Utils.unserialize(Utils.readFile(filePath));
    }
}
// END
