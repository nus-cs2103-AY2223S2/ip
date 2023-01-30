package duke;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void simpleTest() {
        try {
            assertArrayEquals(Parser.parse("mark 5", Parser.ParseFunctions.TODO),
                    new String[]{"mark", "5"});
        }
        catch (Exception ignored) {
        }
    }

    @Test
    public void moreComplexTest() {
        try {
            assertArrayEquals(Parser.parse("event go to school /from 2019-01-03 /to 2019-02-04",
                    Parser.ParseFunctions.EVENT),
                    new String[]{"event", "go to school", "2019-01-03", "2019-02-04"});
        }
        catch (Exception ignored) {
        }
    }
}