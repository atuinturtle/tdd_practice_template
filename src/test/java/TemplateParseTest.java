import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TemplateParseTest {

    @BeforeEach
    void setUp() {
    }

    private List<String> parse(String template) {
        TemplateParse parse = new TemplateParse();
        return parse.parse(template);
    }

    private void assertSegments(List<?> actual, Object... expected) {
        assertEquals(expected.length, actual.size(), "Number of segments doesn't match.");
        assertEquals(Arrays.asList(expected), actual);
    }

    @Test
    public void emptyTemplateRendersAsEmptyString() {
        List<String> segments = parse("");
        assertSegments(segments, "");
    }

    @Test
    public void templateWithOnlyPlainText() {
        List<String> segments = parse("plain text only");
        assertSegments(segments, "plain text only");
    }

    @Test
    public void parsingMultipleVariables() throws Exception {
        List<String> segments = parse("${a}:${b}:${c}");
        assertSegments(segments, "${a}", ":", "${b}", ":", "${c}");
    }
}