package chad.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import chad.parser.DateTimeParser;

public class DeadlineTest {
    @Test
    public void testDeadlineString() {
        Deadline deadline = new Deadline("test", DateTimeParser.parse("2023-01-30 2311"));
        String expected = "[D][ ] test (by: Jan 30 2023, 11:11PM)";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void testDeadlineData() {
        Deadline deadline = new Deadline("test", DateTimeParser.parse("2023-01-30 2311"));
        String expected = "D | 0 | test | 2023-01-30 2311";
        assertEquals(expected, deadline.toData());
    }
}
