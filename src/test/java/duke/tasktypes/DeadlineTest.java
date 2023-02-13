package duke.tasktypes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;

public class DeadlineTest {
    @Test
    public void testSaveFormatConversion_deadlineTaskUndone_success() {
        Deadline task = null;
        try {
            task = new Deadline("This is another test Deadline task.", "2023/01/01 1000");
        } catch (DukeException e) {
            fail();
        }
        task.setDone();
        task.setUndone();
        String actualOutput = task.getSaveFormat();
        assertEquals("D,,0,,This is another test Deadline task.,,2023-01-01 1000", actualOutput);
    }

    @Test
    public void testStringConversion_incorrectTimeFormat_exceptionThrown() {
        Deadline task = null;
        try {
            task = new Deadline("This is a test Deadline task.", "2022/10/10 10am");
            fail();
        } catch (DukeException e) {
            assertEquals("Please enter your date and time in this format: yyyy/mm/dd HHMM",
                    e.getMessage());
        }
    }

    @Test
    public void testStringConversion_incorrectDateFormat_exceptionThrown() {
        Deadline task = null;
        try {
            task = new Deadline("This is a test Deadline task.", "20221010 1000");
            fail();
        } catch (DukeException e) {
            assertEquals("Please enter your date and time in this format: yyyy/mm/dd HHMM",
                    e.getMessage());
        }
    }

}
