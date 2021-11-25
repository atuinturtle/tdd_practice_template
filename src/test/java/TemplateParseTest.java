import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TemplateParseTest {

    @BeforeEach
    void setUp() {
    }

    private List<Segment> parse(String template) {
        TemplateParse parse = new TemplateParse();
        return parse.parseSegments(template);
    }

    private void assertSegments(List<?> actual, Object... expected) {
        assertEquals(expected.length, actual.size(), "Number of segments doesn't match.");
        assertEquals(Arrays.asList(expected), actual);
    }

    @Test
    public void emptyTemplateRendersAsEmptyString() {
        List<Segment> segments = parse("");
        assertSegments(segments, new PlainText(""));
    }

    @Test
    public void templateWithOnlyPlainText() {
        List<Segment> segments = parse("plain text only");
        assertSegments(segments, new PlainText("plain text only"));
    }

    @Test
    public void parsingMultipleVariables() {
        List<Segment> segments = parse("${a}:${b}:${c}");
        assertSegments(segments, new Variable("a"), new PlainText(":"), new Variable("b"), new PlainText(":"), new Variable("c"));
    }

    @Test
    public void parsingTemplateIntoSegmentObjects() {
        TemplateParse p = new TemplateParse();
        List<Segment> segments = p.parseSegments("a ${b} c ${d}");
        assertSegments(segments,
                new PlainText("a "), new Variable("b"),
                new PlainText(" c "), new Variable("d"));
    }
}