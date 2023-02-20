package botanic.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Tests the behavior of ToDo class.
 */
public class ToDoTest {
    /**
     * Test the behaviour of toString().
     */
    @Test
    public void testStringConversion() {
        assertEquals("[T][ ] get food",
                new ToDo("get food").toString());
    }

    /**
     * Test the behaviour of formatForStorage().
     */
    @Test
    public void testFormatStore() {
        assertEquals("T | 0 | get food",
                new ToDo("get food").formatForStorage());
    }

    /**
    * Tests the behaviour of mark().
    */
    @Test
    public void testMark() {
        ToDo todo = new ToDo("get food");
        todo.markIsDone();
        assertEquals("[T][X] get food", todo.toString());
    }

    /**
     * Tests the behaviour of unmark().
     */
    @Test
    public void testUnmark() {
        ToDo todo = new ToDo("get food");
        todo.markIsDone();
        todo.unmarkIsDone();
        assertEquals("[T][ ] get food", todo.toString());
    }

    /**
     * Tests the behaviour of isDone() when a task is not marked.
     */
    @Test
    public void isDone_unmarked_whiteSpace() {
        assertEquals(" ", new ToDo("a").isDone());
    }

    /**
     * Tests the behaviour of isDone() when a task is marked.
     */
    @Test
    public void isDone_marked_markedX() {
        ToDo todo = new ToDo("a");
        todo.markIsDone();
        assertEquals("X", todo.isDone());
    }

    /**
     * Tests the behaviour of containKeyword() by searching for
     * a keyword that the task name is expected to completely match with.
     */
    @Test
    public void containKeyword_allMatchingName_true() {
        assertTrue(new ToDo("get food").containKeyword("get food"));
    }

    /**
     * Tests the behaviour of containKeyword() by searching for
     * a keyword that the task name is expected to partially match with.
     */
    @Test
    public void containKeyword_partialMatchingName_true() {
        assertTrue(new ToDo("get food").containKeyword("foo"));
    }

    /**
     * Tests the behaviour of containKeyword() by searching for
     * a keyword that the task name is expected to not match with.
     */
    @Test
    public void containKeyword_noMatchingName_true() {
        assertFalse(new ToDo("get food").containKeyword("gt food"));
    }

    /**
     * Tests the behaviour of containDate().
     */
    @Test
    public void testContainDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate ld = LocalDate.parse("2024/11/11", dtf);
        assertFalse(new ToDo("get food").containDate(ld));
    }
}
