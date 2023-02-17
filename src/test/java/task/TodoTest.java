package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DukeException;

public class TodoTest {
    @Test
    public void testToString() throws DukeException {
        Todo todo = new Todo("finish homework");
        assertEquals("[T][ ] finish homework", todo.toString());

        todo.markDone();
        assertEquals("[T][X] finish homework", todo.toString());
    }
}
