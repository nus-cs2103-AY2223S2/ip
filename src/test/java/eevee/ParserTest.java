package eevee;

import eevee.exception.TaskNoContentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import eevee.task.Task;
import eevee.task.Event;


public class ParserTest {
    @Test
    public void testMakeEvent() {
        try {
            Task expectedTask = new Event("japan trip", "2022-12-01", "2022-12-16");
            String input = "event japan trip /from 2022-12-01 /to 2022-12-16";
            assertEquals(expectedTask.getDescription(), Parser.makeEventFromCommand(input).getDescription());
        } catch (TaskNoContentException e) {
            System.out.println("failed");
        }
    }

    @Test
    public void testConvertTask() {
        Task expectedTask = new Event("japan trip", "2022-12-01", "2022-12-16");
        String input = "E | 0 | japan trip | 2022-12-01 | 2022-12-16";
        assertEquals(expectedTask.getDescription(),
                Parser.convertTaskFromLineInTaskList(input).getDescription());
    }
}
