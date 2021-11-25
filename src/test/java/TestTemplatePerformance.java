import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestTemplatePerformance {

    private Template template;

    @BeforeEach
    public void setUp() throws Exception {
        template = new Template("${one}, ${two}, ${three}, ${four}, ${five}, " +
                "${six}, ${seven}, ${eight}, ${nine}, ${ten}, " +
                "${eleven}, ${twelve}, ${thirteen}, ${fourteen}, ${fifteen}, " +
                "${sixteen}, ${seventeen}, ${eighteen}, ${nineteen}, ${twenty}, " +
                "${one}, ${two}, ${three}, ${four}, ${five}, " +
                "${six}, ${seven}, ${eight}, ${nine}, ${ten}, " +
                "${eleven}, ${twelve}, ${thirteen}, ${fourteen}, ${fifteen}, " +
                "${sixteen}, ${seventeen}, ${eighteen}, ${nineteen}, ${twenty}, " +
                "${one}, ${two}, ${three}, ${four}, ${five}, " +
                "${six}, ${seven}, ${eight}, ${nine}, ${ten}, " +
                "${eleven}, ${twelve}, ${thirteen}, ${fourteen}, ${fifteen}, " +
                "${sixteen}, ${seventeen}, ${eighteen}, ${nineteen}, ${twenty}, " +
                "${one}, ${two}, ${three}, ${four}, ${five}, " +
                "${six}, ${seven}, ${eight}, ${nine}, ${ten}, " +
                "${eleven}, ${twelve}, ${thirteen}, ${fourteen}, ${fifteen}, " +
                "${sixteen}, ${seventeen}, ${eighteen}, ${nineteen}, ${twenty}, " +
                "${one}, ${two}, ${three}, ${four}, ${five}, " +
                "${six}, ${seven}, ${eight}, ${nine}, ${ten}, " +
                "${eleven}, ${twelve}, ${thirteen}, ${fourteen}, ${fifteen}," +
                " ${sixteen}, ${seventeen}, ${eighteen}, ${nineteen}, ${twenty}");
        template.set("one", "123456789012345");
        template.set("two", "123456789012345");
        template.set("three", "123456789012345");
        template.set("four", "123456789012345");
        template.set("five", "123456789012345");
        template.set("six", "123456789012345");
        template.set("seven", "123456789012345");
        template.set("eight", "123456789012345");
        template.set("nine", "123456789012345");
        template.set("ten", "123456789012345");
        template.set("eleven", "123456789012345");
        template.set("twelve", "123456789012345");
        template.set("thirteen", "123456789012345");
        template.set("fourteen", "123456789012345");
        template.set("fifteen", "123456789012345");
        template.set("sixteen", "123456789012345");
        template.set("seventeen", "123456789012345");
        template.set("eighteen", "123456789012345");
        template.set("nineteen", "123456789012345");
        template.set("twenty", "123456789012345");
    }

    @Test
    public void templateWith100WordsAnd20Variables() {
        long expected = 200L;
        long time = System.currentTimeMillis();
        template.evaluate();
        time = System.currentTimeMillis() -time;
        assertTrue(time <= expected,
                "Rendering the template took " + time
                        + "ms while the target was " + expected + "ms");
    }
}
