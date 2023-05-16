import duke.duke.Parser;
import duke.duke.TaskList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseTodoTest() {
        TaskList data = new TaskList();
        Parser parser = new Parser(data);
        String msg = parser.parse("todo abc");
        assertEquals(data.getSize(), 1);
        assertEquals(msg, "added: abc\nNow you have 1 tasks in the list");
    }

    @Test
    public void parseEventTest() {
        TaskList data = new TaskList();
        Parser parser = new Parser(data);
        String msg = parser.parse("event abc /from 2023-02-15 1800 /to 2023-02-26 1800");
        assertEquals(data.getSize(), 1);
        assertEquals(msg, "added: abc (from: Feb 15 2023 1800 to: Feb 26 2023 1800)\nNow you have 1 tasks in the list");
    }
}
