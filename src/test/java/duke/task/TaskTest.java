package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testSetDoneStatus() {
        ToDo newTask = new ToDo("Task 1");
        newTask.setDoneStatus();
        assertEquals(true, newTask.isDone);
    }

    @Test
    public void testSetUndoneStatus() {
        ToDo newTask = new ToDo("Task 1");
        newTask.setUndoneStatus();
        assertEquals(false, newTask.isDone);
    }

    @Test
    public void testGetNameOfTask() {
        ToDo newTask = new ToDo("Task 1");
        assertEquals("Task 1", newTask.taskName);
    }


}
