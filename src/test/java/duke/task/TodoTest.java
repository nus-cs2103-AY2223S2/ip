package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    Todo todo;
    @Test
    public void getTaskTest() {
        todo = new Todo(1, false, "buy food", 1);
        assertEquals("buy food", todo.getTask());
    }

    @Test
    public void getTaskStatusTest() {
        todo = new Todo(1, false, "buy food", 1);
        assertEquals("[ ]", todo.getTaskStatus());
    }
}
