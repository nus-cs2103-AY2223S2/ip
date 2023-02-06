package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class DoAfterTest {
    @Test
    public void getDescription() {
        try {
            String description = "return book about unit test";
            Task task = new DoAfter(description, false, "26/1/2023 2303");
            assertEquals(description, task.getDescription());
        } catch (DukeException e) {
            fail(e);
        }
    }

    @Test
    public void init_noName_exceptionThrown() {
        try {
            new DoAfter("", false, "26/1/2023 2303");
            fail();
        } catch (DukeException e) {
            assertEquals("The description of a task cannot be empty", e.getMessage());
        }
    }

    @Test
    public void hasIcon_doAfterIcon_returnsTrue() {
        assertEquals(true, DoAfter.hasIcon("A"));
    }

    @Test
    public void hasIcon_notEventIcon_returnsFalse() {
        assertEquals(false, DoAfter.hasIcon("not A"));
    }

    @Test
    public void dateValidity_invalidAfter_exceptionThrown() {
        try {
            new DoAfter("return book about unit test", false, "oh no this is invalid");
            fail();
        } catch (DukeException e) {
            assertEquals("Could not parse 'after' as date time", e.getMessage());
        }
    }

    @Test
    public void markAndUnmark() {
        try {
            Task task = new DoAfter("return book about unit test", false, "26/1/2023 2303");
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
