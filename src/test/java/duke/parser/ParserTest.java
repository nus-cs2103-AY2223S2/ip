package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class ParserTest {
    private Parser parser = new Parser();

    @Test
    public void getTaskNum_validTaskNum_taskNumReturned() {
        String[] command = new String[] { "mark", "5" };
        try {
            assertEquals(parser.getTaskNum(command), 4);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void getTaskNum_noTaskNum_exceptionThrown() {
        String[] command = new String[] { "mark" };
        try {
            parser.getTaskNum(command);
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "Task number required");
        }
    }

    @Test
    public void getTaskToAdd_validDeadline_deadlineObjectReturned() {
        String[] command = new String[] { "deadline", "project 2 /by 2023-01-03 12:00" };
        try {
            assertEquals(parser.getTaskToAdd(command).toString(),
                    "[D][ ] project 2 (by: Jan 03 2023 12:00)");
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void getTaskToAdd_invalidEvent_exceptionThrown() {
        String[] command = new String[] { "event", "meeting /by 2023-01-03 12:00" };
        try {
            parser.getTaskToAdd(command);
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "invalid format");
        }
    }
}
