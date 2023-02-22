package duke.tasks;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


class TodoTest {
    @Test
    void hasCorrectDescription() {
        Todo todo = new Todo(1, "read book");
        assertEquals("[T][ ] read book", todo.toString());
    }

    @Test
    void hasCorrectStatus() {
        Todo todo = new Todo(1, "read book");
        assertEquals(false, todo.isCompleted());
        todo.markCompleted();
        assertEquals(true, todo.isCompleted());
        assertEquals("[T][X] read book", todo.toString());
    }
}
