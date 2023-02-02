package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testMakeEvent() {
        Task expectedTask = new Events("japan trip", "2022-12-01", "2022-12-16");
        String input = "event japan trip /from 2022-12-01 /to 2022-12-16";
        assertEquals(expectedTask.description(),
                Parser.makeEventFromCommand(input).description());
    }

    @Test
    public void testConvertTask() {
        Task expectedTask = new Events("japan trip", "2022-12-01", "2022-12-16");
        String input = "E | 0 | japan trip | 2022-12-01 | 2022-12-16";
        assertEquals(expectedTask.description(),
                Parser.convertTaskFromLineInTaskList(input).description());
    }
}
