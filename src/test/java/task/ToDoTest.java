package task;

import dukeexception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;



public class ToDoTest {
    @Test
    public void toDoSuccessTest() {
        String[] testInput = {"todo", "Test Task"};
        try {
            ToDo toDo = ToDo.generate(testInput);
            assertEquals("T", toDo.getTaskType());
            assertEquals("T | 0 | Test Task", toDo.getStoreTaskString());
            assertEquals("[T][ ] Test Task", toDo.toString());
        } catch (DukeException e) {
            fail("Should not have thrown any exceptions");
        }
    }

    @Test
    public void toDoFailTest() {
        assertThrows(DukeException.class, () -> {
            String[] testInput = {"todo"};
            ToDo.generate(testInput);
        });
    }
}
