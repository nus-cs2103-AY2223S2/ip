package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
/**
 * Test Class for testing the Todo class.
 *
 * @author owen-yap
 */
public class TodoTest {
    /**
     * Test the marking of a Todo and check that isDone is true.
     */
    @Test
    public void mark_whenMarkingTodo_thenIsDoneIsTrue() {
        Todo todo = new Todo("test");
        todo.mark();
        assertTrue(todo.isDone());
    }
    /**
     * Test the status icon of a marked Todo and check that it returns 'X'.
     */
    @Test
    public void statusIcon_givenIsDoneTrue_thenReturnX() {
        Todo todo = new Todo("test");
        todo.mark();
        assertEquals("X", todo.getStatusIcon());
    }
    /**
     * Test the unmarking of a Todo and check that isDone is false.
     */
    @Test
    public void unmark_whenUnmarkingTodo_thenIsDoneIsFalse() {
        Todo todo = new Todo("test", true);
        todo.unmark();
        assertFalse(todo.isDone());
    }
}
