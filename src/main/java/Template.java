import java.util.HashMap;
import java.util.Map;

public class Template {

    private final String templateText;
    private Map<String, String> variableMap = new HashMap<>();

    public Template(String templateText) {
        this.templateText = templateText;
    }

    public void set(String variable, String value) {
        this.variableMap.put(variable, value);
    }

    public String evaluate() {
        String result = templateText;
        for (Map.Entry<String, String> entry : variableMap.entrySet()) {
            String regex = "\\$\\{" + entry.getKey() + "\\}";
            result = result.replaceAll(regex, entry.getValue());
        }
        return result;
    }
}
