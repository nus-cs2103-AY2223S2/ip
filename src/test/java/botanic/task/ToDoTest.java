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
    * Tests the behaviour of setDone() by trying to mark the task as done.
    */
    @Test
    public void setDone_setTrue_true() {
        ToDo todo = new ToDo("get food");
        todo.setDone(true);
        assertEquals("[T][X] get food", todo.toString());
    }

    /**
     * Tests the behaviour of setDone() by trying to mark the task as done,
     * then mark the task as not done.
     */
    @Test
    public void setDone_setTrueThenSetFalse_false() {
        ToDo todo = new ToDo("get food");
        todo.setDone(true);
        todo.setDone(false);
        assertEquals("[T][ ] get food", todo.toString());
    }

    /**
     * Tests the behaviour of getIsDone() when a task is not marked.
     */
    @Test
    public void getIsDone_unmarked_whiteSpace() {
        assertEquals(" ", new ToDo("a").getIsDone());
    }

    /**
     * Tests the behaviour of getIsDone() when a task is marked.
     */
    @Test
    public void getIsDone_marked_markedX() {
        ToDo todo = new ToDo("a");
        todo.setDone(true);
        assertEquals("X", todo.getIsDone());
    }

    /**
     * Tests the behaviour of hasKeyword() by searching for
     * a keyword that the task name is expected to completely match with.
     */
    @Test
    public void hasKeyword_allMatchingName_true() {
        assertTrue(new ToDo("get food").hasKeyword("get food"));
    }

    /**
     * Tests the behaviour of hasKeyword() by searching for
     * a keyword that the task name is expected to partially match with.
     */
    @Test
    public void hasKeyword_partialMatchingName_true() {
        assertTrue(new ToDo("get food").hasKeyword("foo"));
    }

    /**
     * Tests the behaviour of hasKeyword() by searching for
     * a keyword that the task name is expected to not match with.
     */
    @Test
    public void hasKeyword_noMatchingName_true() {
        assertFalse(new ToDo("get food").hasKeyword("gt food"));
    }

    /**
     * Tests the behaviour of hasDate().
     */
    @Test
    public void testHasDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate ld = LocalDate.parse("2024/11/11", dtf);
        assertFalse(new ToDo("get food").hasDate(ld));
    }
}
