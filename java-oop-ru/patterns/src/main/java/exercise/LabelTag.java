package exercise;

// BEGIN
public class LabelTag implements TagInterface {
    private final String value;
    private final TagInterface tag;
    
    public LabelTag(String value, TagInterface tag) {
        this.value = value;
        this.tag = tag;
    }
    
    @Override
    public String render() {
        return "<label>" + value + tag.render() + "</label>";
    }
}
// END
