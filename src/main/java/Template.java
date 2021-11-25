import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        String result = replaceVariables();
        checkForMissingValues(result);

        return result;
    }

    private String replaceVariables() {
        String result = templateText;
        for (Map.Entry<String, String> entry : variableMap.entrySet()) {
            String regex = "\\$\\{" + entry.getKey() + "\\}";
            result = result.replaceAll(regex, entry.getValue());
        }
        return result;
    }

    private void checkForMissingValues(String result) {
        Matcher m = Pattern.compile("\\$\\{.+\\}").matcher(result);
        if (m.find()) {
            throw new MissingValueException("No value for " + m.group());
        }
    }
}
