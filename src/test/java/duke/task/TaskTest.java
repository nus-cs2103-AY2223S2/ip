package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void toStringTest() {
        Task task = new Task("test");
        assertEquals("[ ] test", task.toString());
    }

    @Test
    public void markUnmarkTest() {
        Task task = new Task("test");
        task.markTask();
        assertEquals("[X] test", task.toString());

        task.unmarkTask();
        assertEquals("[ ] test", task.toString());
    }

    @Test
    public void exportDataTest() {
        Task task = new Task("test");
        assertEquals("Task | marked: 0 ; description: test", task.toData());

        task.markTask();
        assertEquals("Task | marked: 1 ; description: test", task.toData());
    }
}
