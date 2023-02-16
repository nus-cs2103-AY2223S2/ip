package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;



public class TodoTest {

    @Test
    public void testTodoCreation() {
        Todo todo = new Todo("Buy milk");
        assertEquals("Buy milk", todo.getDescription());
        assertFalse(todo.getIsDone());
    }

    @Test
    public void testTodoCompletion() {
        Todo todo = new Todo("Buy milk");
        todo.markAsDone();
        assertTrue(todo.getIsDone());
    }

    @Test
    public void testTodoToString() {
        Todo todo = new Todo("Buy milk");
        assertEquals("[T][ ] Buy milk", todo.toString());
    }

    @Test
    public void testTodoToStringCompletion() {
        Todo todo = new Todo("Buy milk");
        todo.markAsDone();
        assertEquals("[T][X] Buy milk", todo.toString());
    }

}
