package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testGetStatusOfTaskInString() {
        ToDo newToDoTask = new ToDo("Task 1");
        assertEquals("[T][ ] Task 1", newToDoTask.getStatusOfTaskInString());
    }

}
