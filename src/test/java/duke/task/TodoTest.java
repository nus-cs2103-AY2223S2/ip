package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
