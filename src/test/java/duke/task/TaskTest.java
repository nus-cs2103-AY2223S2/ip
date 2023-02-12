package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskTest {

    public Task getTaskStub(String description) {
        return new Task(description) {
            @Override
            public String getRecreateCommand(int id) {
                return "";
            }
        };
    }

    @Test
    public void task_markAsDone() {
        Task task1 = getTaskStub("");
        Task task2 = getTaskStub("");
        assertFalse(task1.getIsDone());
        task2.toggleDone();
        assertFalse(task1.getIsDone());
        assertTrue(task2.getIsDone());
        task2.toggleDone();
        assertFalse(task2.getIsDone());
    }

    @Test
    public void task_containsKeyword() {
        Task task = getTaskStub("Task with certain keyword");
        assertEquals(true, task.contains("keyword"));
        assertEquals(false, task.contains("notKeyword"));
    }

    @Test
    public void task_compareEquals() {
        Task task1 = getTaskStub("Do homework");
        Task task2 = getTaskStub("Sweep the floor");
        Task task3 = getTaskStub("Do homework");

        assertNotEquals(task1, task2);
        assertEquals(task1, task3);
        assertNotEquals(task2, task1);
        assertNotEquals(task2, task3);
    }
}
