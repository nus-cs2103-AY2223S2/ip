package willy;

import willy.task.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testTodoCreation() {
        assertEquals("[T][ ]todo borrow book", new Todo("todo borrow book").toString());
    }
}