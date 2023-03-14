package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.command.DeadlineCommand;
import duke.exception.DukeException;




public class DeadlineCommandTest {
    @Test
    public void deadlinePassedTest() throws DukeException {
        DeadlineCommand t = new DeadlineCommand("deadline do hw /by 2010-12-01 18:00");
        assertEquals(true, t.isDeadlineOver());
    }

    @Test
    public void formatCorrectTest() throws DukeException {
        DeadlineCommand t = new DeadlineCommand("deadline do hw /by 20101201 18:00");
        assertEquals(false, t.isFormatCorrect());
    }
}
