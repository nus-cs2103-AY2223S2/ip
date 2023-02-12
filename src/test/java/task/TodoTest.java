package task;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.DukeException;

public class TodoTest {
    @Test
    public void testTodo() {
        assertThrows(DukeException.class, () -> new Todo(" "));
        assertThrows(DukeException.class, () -> new Todo("todo  "));
        assertDoesNotThrow(() -> new Todo("todo todo todo"));
    }

    @Test
    public void testToString() throws DukeException {
        Todo todo = new Todo("finish homework");
        assertEquals("[T][ ] finish homework", todo.toString());

        todo.markDone();
        assertEquals("[T][X] finish homework", todo.toString());
    }
}
