import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Template {

    private final String templateText;
    private Map<String, String> variables = new HashMap<>();

    public Template(String templateText) {
        this.templateText = templateText;
    }

    public void set(String variable, String value) {
        this.variables.put(variable, value);
    }

    public String evaluate() {
        TemplateParse parser = new TemplateParse();
        List<String> segments = parser.parse(templateText);
        return concatenate(segments);
    }

    private String concatenate(List<String> segments) {
        StringBuilder result = new StringBuilder();
        for (String segment : segments) {
            append(segment, result);
        }
        return result.toString();
    }

    private void append(String segment, StringBuilder result) {
        if(isVariable(segment)) {
            evaluateVariable(segment, result);
        } else {
            result.append(segment);
        }
    }

    private boolean isVariable(String segment) {
        return segment.startsWith("${") && segment.endsWith("}");
    }

    private void evaluateVariable(String segment, StringBuilder result) {
        String var = segment.substring(2, segment.length() - 1);
        if (!variables.containsKey(var)) {
            throw new MissingValueException("No value for " + segment);
        }
        result.append(variables.get(var));
    }
}
