package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TaskTest {
    @DisplayName("Task toString Test")
    @Test
    public void toStringTest() {
        Task task = new Task("test");
        assertEquals("[ ] test", task.toString());
    }

    @DisplayName("Task (un)marking Test")
    @Test
    public void markUnmarkTest() {
        Task task = new Task("test");
        task.markTask();
        assertEquals("[X] test", task.toString());

        task.unmarkTask();
        assertEquals("[ ] test", task.toString());
    }

    @DisplayName("Task Export Test")
    @Test
    public void exportDataTest() {
        Task task = new Task("test");
        assertEquals("Task | marked: 0 ; description: test", task.toData());

        task.markTask();
        assertEquals("Task | marked: 1 ; description: test", task.toData());
    }
}
