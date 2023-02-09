package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class TodoTaskTest {

    @Test
    public void storageString_taskCompleted_returnsCorrectString() {
        TodoTask task = new TodoTask("Buy milk");
        task.markAsDone();
        String expected = "[T] | [X] | Buy milk";
        assertEquals(expected, task.storageString());
    }

    @Test
    public void storageString_taskNotCompleted_returnsCorrectString() {
        TodoTask task = new TodoTask("Buy milk");
        task.unmark();
        String expected = "[T] | [ ] | Buy milk";
        assertEquals(expected, task.storageString());
    }

    @Test
    public void matchesDate_todoTask_returnsFalse() {
        TodoTask task = new TodoTask("Buy milk");
        LocalDate date = LocalDate.now();
        assertFalse(task.matchesDate(date));
    }

    @Test
    public void toString_taskCompleted_returnsCorrectString() {
        TodoTask task = new TodoTask("Buy milk");
        task.markAsDone();
        String expected = "[T][X] Buy milk";
        assertEquals(expected, task.toString());
    }

    @Test
    public void toString_taskNotCompleted_returnsCorrectString() {
        TodoTask task = new TodoTask("Buy milk");
        task.unmark();
        String expected = "[T][ ] Buy milk";
        assertEquals(expected, task.toString());
    }

    @Test
    public void equals_todoTaskWithSameInfo_returnsTrue() {
        TodoTask task1 = new TodoTask("Buy milk");
        TodoTask task2 = new TodoTask("Buy milk");
        assertEquals(task1, task2);
    }

    @Test
    public void equals_todoTaskWithDifferentInfo_returnsFalse() {
        TodoTask task1 = new TodoTask("Buy milk");
        TodoTask task2 = new TodoTask("Buy eggs");
        assertNotEquals(task1, task2);
    }

    @Test
    public void equals_todoTaskWithDifferentStatus_returnsFalse() {
        TodoTask task1 = new TodoTask("Buy milk");
        task1.markAsDone();
        TodoTask task2 = new TodoTask("Buy milk");
        task2.unmark();
        assertNotEquals(task1, task2);
    }
}
