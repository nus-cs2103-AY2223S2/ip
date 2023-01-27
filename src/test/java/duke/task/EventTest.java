package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class EventTest {
    @Test
    public void getDescription() {
        try {
            String description = "unit test celebrations";
            Task task = new Event(description, false, "26/1/2023 2303", "26/1/2023 2359");
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
    public void hasIcon_eventIcon_returnsTrue() {
        assertEquals(true, Event.hasIcon("E"));
    }

    @Test
    public void hasIcon_notEventIcon_returnsFalse() {
        assertEquals(false, Event.hasIcon("not E"));
    }

    @Test
    public void dateValidity_invalidFrom_exceptionThrown() {
        try {
            new Event("unit test celebrations", false, "oh no this is invalid", "26/1/2023 2359");
            fail();
        } catch (DukeException e) {
            assertEquals("Could not parse 'from' as date time", e.getMessage());
        }
    }

    @Test
    public void dateValidity_invalidTo_exceptionThrown() {
        try {
            new Event("unit test celebrations", false, "26/1/2023 2359", "oh no this is invalid");
            fail();
        } catch (DukeException e) {
            assertEquals("Could not parse 'to' as date time", e.getMessage());
        }
    }

    @Test
    public void markAndUnmark() {
        try {
            Task task = new Event("unit test celebrations", false, "26/1/2023 2303", "26/1/2023 2359");
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
