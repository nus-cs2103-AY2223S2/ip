package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void creatingTodo() {
        Todo todo = new Todo("Content");
        assertNotNull(todo);
        assertEquals(todo.getDescription(), "Content");
        assertEquals(todo.getStatusIcon(), " ");
    }

    @Test
    public void changeStatusOfTodo() {
        Todo todo = new Todo("Content");
        assertEquals(todo.getStatusIcon(), " ");
        todo.markAsDone();
        assertEquals(todo.getStatusIcon(), "X");
        todo.markAsUndone();
        assertEquals(todo.getStatusIcon(), " ");
    }
}
