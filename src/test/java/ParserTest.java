import duke.Parser;
import duke.TaskList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseTodoTest() {
        TaskList data = new TaskList();
        Parser parser = new Parser(data);
        String msg = parser.parse("todo abc");
        assertEquals(data.getSize(), 1);
        assertEquals(msg, "Now you have 1 duke.tasks in the list");
    }

    @Test
    public void parseEventTest() {
        TaskList data = new TaskList();
        Parser parser = new Parser(data);
        String msg = parser.parse("event abc /from 2023-02-15 1800 /to 2023-02-26 1800");
        assertEquals(data.getSize(), 1);
        assertEquals(msg, "Now you have 1 duke.tasks in the list");
    }
}
