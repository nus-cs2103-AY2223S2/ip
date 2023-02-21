package cluck.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import cluck.tasks.Deadline;
import cluck.tasks.Event;
import cluck.tasks.Task;
import cluck.tasks.ToDo;

public class TaskTest {

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Test
    public void todoFromSaveTest() {
        String workingTodoSave = "T|0|Meet Jason";
        Task savedTask = Task.buildTaskFromSave(workingTodoSave);
        Task testTask = new ToDo("Meet Jason");
        assertEquals(savedTask, testTask);
    }

    @Test
    public void todoIncorrectSave() {
        String workingTodoSave = "T|X|Meet Jason";
        Task savedTask = Task.buildTaskFromSave(workingTodoSave);
        assertNull(savedTask);
    }
}
