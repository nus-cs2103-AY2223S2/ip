package dude.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void toString_success() {
        Todo todo = new Todo("Todo");
        assertEquals("[T][ ] Todo", todo.toString());
    }

    @Test
    public void toRaw_success() {
        Todo todo = new Todo("Todo");
        assertEquals("T | 0 | Todo\n", todo.toRaw());
    }
}
