import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateParse {

    public List<Segment> parseSegments(String template) {
        return parse(template);
    }

    private List<Segment> parse(String template) {
        List<Segment> segments = new ArrayList<>();
        int index = collectSegments(segments, template);
        addTail(segments, template, index);
        addEmptyStringIfTemplateWasEmpty(segments);
        return segments;
    }

    private int collectSegments(List<Segment> segments, String src) {
        Pattern pattern = Pattern.compile("\\$\\{[^}]*}");
        Matcher matcher = pattern.matcher(src);
        int index = 0;
        while (matcher.find()) {
            addPrecedingPlainText(segments, src, matcher, index);
            addVariable(segments, src, matcher);
            index = matcher.end();
        }
        return index;
    }

    private void addPrecedingPlainText(List<Segment> segments, String src, Matcher m, int index) {
        if (index != m.start()) {
            segments.add(new PlainText(src.substring(index, m.start())));
        }
    }

    private void addVariable(List<Segment> segments, String src, Matcher m) {
        String name = src.substring(m.start() + 2, m.end() - 1);
        segments.add(new Variable(name));
    }

    private void addTail(List<Segment> segments, String src, int index) {
        if (index < src.length()) {
            segments.add(new PlainText(src.substring(index)));
        }
    }

    private void addEmptyStringIfTemplateWasEmpty(List<Segment> segments) {
        if (segments.isEmpty()) {
            segments.add(new PlainText(""));
        }
    }
}
