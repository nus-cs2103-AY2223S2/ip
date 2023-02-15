package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class DeadlineTest {
    @Test
    public void getDescription() {
        try {
            String description = "complete unit tests";
            Task task = new Deadline(description, false, "26/1/2023 2303");
            assertEquals(description, task.getDescription());
        } catch (DukeException e) {
            fail(e);
        }
    }

    @Test
    public void init_noName_exceptionThrown() {
        try {
            new Event("", false, "26/1/2023 2303", "26/1/2023 2359");
            fail();
        } catch (DukeException e) {
            assertEquals("The description of a task cannot be empty", e.getMessage());
        }
    }

    @Test
    public void hasIcon_deadlineIcon_returnsTrue() {
        assertEquals(true, Deadline.hasIcon("D"));
    }

    @Test
    public void hasIcon_notDeadlineIcon_returnsFalse() {
        assertEquals(false, Todo.hasIcon("not D"));
    }

    @Test
    public void dateValidity_invalidBy_exceptionThrown() {
        try {
            new Deadline("complete unit tests", false, "oh no this is invalid");
            fail();
        } catch (DukeException e) {
            assertEquals("Could not parse 'by' as date time", e.getMessage());
        }
    }

    @Test
    public void markAndUnmark() {
        try {
            Task task = new Deadline("complete unit tests", false, "26/1/2023 2303");
            assertEquals(false, task.isCompleted());
            task.mark();
            assertEquals(true, task.isCompleted());
            task.unmark();
            assertEquals(false, task.isCompleted());
        } catch (Exception e) {
            fail(e);
        }
    }
}
