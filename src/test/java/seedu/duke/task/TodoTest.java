package seedu.duke.task;

import duke.task.Todo;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void markDone() {
        Todo todo = new Todo("eat");
        todo.markDone();
        assertEquals("[T][X] eat", todo.toString());
    }

    @Test
    public void markUndone() {
        Todo todo = new Todo("eat");
        todo.markDone();
        todo.markUndone();
        assertEquals("[T][ ] eat", todo.toString());
    }

}
