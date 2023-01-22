package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void testToDo() {
        ToDo todo = new ToDo("test");

        assertEquals("test", todo.getTask());
        assertFalse(todo.isDone());
    }

    @Test
    public void testToDoDone() {
        ToDo todo = new ToDo("test");
        todo.markAsDone();

        assertEquals("test", todo.getTask());
        assertTrue(todo.isDone());
    }

    @Test
    public void testToDoToString() {
        ToDo todo = new ToDo("test");
        assertEquals("[T][ ] test", todo.toString());
    }
}
