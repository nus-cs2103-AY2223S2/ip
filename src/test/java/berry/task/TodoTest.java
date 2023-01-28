package berry.task;

import berry.exception.IncorrectDateException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toString_success() throws IncorrectDateException {
        Todo todo = new Todo("some todo");
        assertEquals("[T][ ] some todo", todo.toString());
    }

    @Test
    public void interpretTaskToString() throws IncorrectDateException {
        Todo todo = new Todo("some todo");
        assertEquals("T |   | some todo", todo.interpretTaskToString());
    }
}
