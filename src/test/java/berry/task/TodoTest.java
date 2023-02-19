package berry.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import berry.exception.IncorrectDateException;

public class TodoTest {
    @Test
    public void toString_checkTodoTask_success() throws IncorrectDateException {
        Todo todo = new Todo("some todo");
        assertEquals("[T][ ] some todo", todo.toString());
    }

    @Test
    public void interpretTaskToString_todoTask_success() throws IncorrectDateException {
        Todo todo = new Todo("some todo");
        assertEquals("T |   | some todo", todo.interpretTaskToText());
    }
}
