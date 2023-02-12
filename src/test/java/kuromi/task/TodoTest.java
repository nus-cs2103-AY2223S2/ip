package kuromi.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
