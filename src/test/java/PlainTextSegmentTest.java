import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PlainTextSegmentTest {
    @Test
    public void plainTextEvaluatesAsIs() {
        Map<String, String> variables = new HashMap<>();
        String text = "abc def";
        assertEquals(text, new PlainText(text).evaluate(variables));
    }
}