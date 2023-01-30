package sam.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void toString_todo_success() {
        Task todo = new ToDo("a todo");
        assertEquals("[T][ ] a todo", todo.toString());
    }

    @Test
    public void toSaveFormat_todo_success() {
        Task todo = new ToDo("a todo");
        assertEquals("T | 0 | a todo", todo.toSaveFormat());
    }
}
