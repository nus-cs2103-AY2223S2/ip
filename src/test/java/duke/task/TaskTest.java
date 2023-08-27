package duke.task;

import duke.exception.DukeException;
import duke.exception.ERROR;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskTest {
    @Test
    public void testValidInput(){
        try {
            Task task = Task.parseTaskFromInput("event Tiktok interview /from 2023-02-25 11:00 " +
                    "/to 25 Feb 2023 11:30");
            String expected = "[E][ ] Tiktok interview (from: Feb 25 2023 11:00 to: Feb 25 2023 11:30)";
            assertEquals(expected, task.toString());
        } catch (DukeException e) {
            fail("DukeException thrown");
        }
    }

    @Test
    public void testInvalidInput(){
        try {
            Task task = Task.parseTaskFromInput("event Tiktok interview /from 2023-02-25 11:00 " +
                    "/from 25 Feb 2023 11:30");
            fail("Did not throw DukeException when it is expected.");
        } catch (DukeException e) {
            String expected = ERROR.EVENT_WRONG_FORMAT.getMessage();
            assertEquals(expected, e.getMessage());
        }
    }
}
