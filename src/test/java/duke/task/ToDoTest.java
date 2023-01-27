package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void testGetDescription() {
        ToDo todo = new ToDo("Todo Name");
        assertEquals("Todo Name", todo.getDescription());
    }

    @Test
    public void testGetStatus() {
        ToDo todo = new ToDo("Test", true);
        assertEquals("true", todo.getStatus());
    }

}
