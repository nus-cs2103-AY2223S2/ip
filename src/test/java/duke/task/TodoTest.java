package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void mark_whenMarkingTodo_thenIsDoneIsTrue() {
        Todo todo = new Todo("test");
        todo.mark();
        assertTrue(todo.isDone());
    }

    @Test
    public void statusIcon_givenIsDoneTrue_thenReturnX() {
        Todo todo = new Todo("test");
        todo.mark();
        assertEquals("X", todo.getStatusIcon());
    }

    @Test
    public void unmark_whenUnmarkingTodo_thenIsDoneIsFalse() {
        Todo todo = new Todo("test", true);
        todo.unmark();
        assertFalse(todo.isDone());
    }
}
