package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DukeException;

public class TodoTest {
    @Test
    public void testToString() throws DukeException {
        Todo todo = new Todo("finish homework");
        assertEquals(todo.toString(), "[T][ ] finish homework");

        todo.markDone();
        assertEquals(todo.toString(), "[T][X] finish homework");
    }
}
