import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Template {

    private final String templateText;
    private final Map<String, String> variables = new HashMap<>();

    public Template(String templateText) {
        this.templateText = templateText;
    }

    public void set(String variable, String value) {
        this.variables.put(variable, value);
    }

    public String evaluate() {
        TemplateParse parser = new TemplateParse();
        List<Segment> segments = parser.parseSegments(templateText);
        return concatenate(segments);
    }

    private String concatenate(List<Segment> segments) {
        StringBuilder result = new StringBuilder();
        for (Segment segment : segments) {
            result.append(segment.evaluate(variables));
        }
        return result.toString();
    }
}
