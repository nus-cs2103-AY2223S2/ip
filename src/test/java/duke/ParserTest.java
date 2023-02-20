package duke;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void getMarkNumTest() {
        try {
            assertEquals(new Parser().getMarkNum("mark 2", true),
                    2);
        }
        catch (Exception ignored) {
        }
    }

    @Test
    public void getTodoNameTest() {
        try {
            assertEquals(new Parser().getTodoName("todo hello"),
                    "hello");
        }
        catch (Exception ignored) {
        }
    }
}