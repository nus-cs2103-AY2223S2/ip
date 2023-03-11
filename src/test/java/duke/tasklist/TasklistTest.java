package duke.tasklist;

import duke.enums.TaskTypes;
import duke.exceptions.DukeException;
import duke.stubs.StorageStub;
import duke.task.Todo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TasklistTest {

    @Test
    public void addTask_addTodo_success() {
        Tasklist tasklist = new Tasklist(new StorageStub());
        tasklist.addTask(new Todo("test todo 2"), TaskTypes.TODO);
        assertEquals(4, tasklist.size());
    }

    @Test
    public void deleteTask_invalidIndex_exceptionThrown() {
        Tasklist tasklist = new Tasklist(new StorageStub());
        try {
            tasklist.deleteTask(55);
            fail();
        } catch (DukeException e) {
            assertEquals(
                    "Invalid task number provided. " +
                            "Given task number is 56 but there " +
                            "are only 3 task(s) in the list",
                    e.getMessage());
        }
    }

    @Test
    public void mark_validIndex_true() {
        Tasklist tasklist = new Tasklist(new StorageStub());
        try {
            assertTrue(tasklist.mark(1));
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void mark_markedIndex_false() {
        Tasklist tasklist = new Tasklist(new StorageStub());
        try {
            assertFalse(tasklist.mark(0));
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void unmark_invalidIndex_exceptionThrown() {
        Tasklist tasklist = new Tasklist(new StorageStub());
        try {
            tasklist.unmark(24);
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid task number provided. " +
                            "Given task number is 25 but there " +
                            "are only 3 task(s) in the list",
                    e.getMessage());
        }
    }
}
