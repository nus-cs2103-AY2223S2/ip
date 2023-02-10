package kuromi.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void taskSize() {
        Todo todo = new Todo("borrow book");
        assertEquals("T", todo.getSymbol());
    }

    @Test
    public void string() {
        Todo todo = new Todo("borrow book");
        assertEquals("[T][ ] borrow book", todo.toString());
    }
}
