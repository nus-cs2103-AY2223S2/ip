package eevee;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import eevee.task.Task;
import eevee.task.Events;


public class ParserTest {
    @Test
    public void testMakeEvent() {
        Task expectedTask = new Events("japan trip", "2022-12-01", "2022-12-16");
        String input = "event japan trip /from 2022-12-01 /to 2022-12-16";
        assertEquals(expectedTask.getDescription(),
                Parser.makeEventFromCommand(input).getDescription());
    }

    @Test
    public void testConvertTask() {
        Task expectedTask = new Events("japan trip", "2022-12-01", "2022-12-16");
        String input = "E | 0 | japan trip | 2022-12-01 | 2022-12-16";
        assertEquals(expectedTask.getDescription(),
                Parser.convertTaskFromLineInTaskList(input).getDescription());
    }
}
