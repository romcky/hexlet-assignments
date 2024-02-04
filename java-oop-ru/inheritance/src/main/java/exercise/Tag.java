package exercise;

import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
public abstract class Tag {
    protected String name;
    protected Map<String, String> attributes;

    @Override
    public String toString() {
        return "<" + name
            + (attributes == null  || attributes.size() == 0 ? "" : " " 
                + attributes
                    .entrySet()
                    .stream()
                    .map(e -> e.getKey() + "=\"" + e.getValue() + "\"")
                    .collect(Collectors.joining(" ")))
            + ">";
    }
}

// END
