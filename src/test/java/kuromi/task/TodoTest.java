package kuromi.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void string() {
        Todo todo = new Todo("borrow book");
        assertEquals("[T][ ] borrow book", todo.toString());
    }

    @Test
    public void markTodo() {
        Todo todo = new Todo("borrow book");
        todo.mark();
        assertTrue(todo.isDone);
    }

    @Test
    public void unmarkTodo() {
        Todo todo = new Todo("borrow book");
        todo.unmark();
        assertFalse(todo.isDone);
    }

    @Test
    public void getSymbol() {
        Todo todo = new Todo("borrow book");
        assertEquals(todo.getSymbol(), "T");
    }
}
