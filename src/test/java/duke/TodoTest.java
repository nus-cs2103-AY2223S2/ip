package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void testTodoCreation() {
        Todo todo = new Todo("todo buy chicken rice");
        assertEquals("buy chicken rice", todo.getDescription());
        assertFalse(todo.isCompleted());
    }

    @Test
    public void testTodoMark() {
        Todo todo = new Todo("BUY CHICKEN RICE");
        todo.setDone();
        assertTrue(todo.isCompleted());
    }

    @Test
    public void testTodoUnmark() {
        Todo todo = new Todo("BUY CHICKEN RICE");
        todo.setDone();
        todo.setUndone();
        assertFalse(todo.isCompleted());
    }
}
