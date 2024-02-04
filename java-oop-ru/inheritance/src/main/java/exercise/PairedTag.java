package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag {
    protected String body;
    protected List<Tag> childs;

    public PairedTag(String name, Map<String, String> attributes, String body, List<Tag> childs) {
        super.name = name;
        super.attributes = attributes;
        this.body = body;
        this.childs = childs;
    }

    @Override
    public String toString() {
        return super.toString() + body
            + (childs == null ? "" : childs.stream().map(Tag::toString).collect(Collectors.joining()))
            + "</" + super.name + ">";
    }
}

// END
