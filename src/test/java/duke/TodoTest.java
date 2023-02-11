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

}
