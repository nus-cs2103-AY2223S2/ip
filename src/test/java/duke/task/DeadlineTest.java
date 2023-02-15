package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

/**
 * Tests the behavior of Deadline class.
 */
public class DeadlineTest {
    /**
     * Tests the behaviour of toString().
     */
    @Test
    public void testStringConversion() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate ld = LocalDate.parse("2024/11/11", dtf);
        assertEquals("[D][ ] get food (by: 11 NOVEMBER 2024)",
                new Deadline("get food", ld).toString());
    }

    /**
     * Tests the behaviour of formatForStorage().
     */
    @Test
    public void testFormatStore() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate ld = LocalDate.parse("2024/11/11", dtf);
        assertEquals("D | 0 | get food | 2024/11/11",
                new Deadline("get food", ld).formatForStorage());
    }

    /**
    * Tests the behaviour of mark().
    */
    @Test
    public void testMark() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate ld = LocalDate.parse("2024/11/11", dtf);
        Deadline task = new Deadline("get food", ld);
        task.markIsDone();
        assertEquals("[D][X] get food (by: 11 NOVEMBER 2024)", task.toString());
    }

    /**
     * Tests the behaviour of unmark().
     */
    @Test
    public void testUnmark() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate ld = LocalDate.parse("2024/11/11", dtf);
        Deadline deadline = new Deadline("get food", ld);
        deadline.markIsDone();
        deadline.unmarkIsDone();
        assertEquals("[D][ ] get food (by: 11 NOVEMBER 2024)", deadline.toString());
    }

    /**
     * Tests the behaviour of isDone() when a task is not marked.
     */
    @Test
    public void isDone_unmarked_whiteSpace() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate ld = LocalDate.parse("2024/11/11", dtf);
        assertEquals(" ", new Deadline("get food", ld).isDone());
    }

    /**
     * Tests the behaviour of isDone() when a task is marked.
     */
    @Test
    public void isDone_marked_markedX() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate ld = LocalDate.parse("2024/11/11", dtf);
        Deadline deadline = new Deadline("get food", ld);
        deadline.markIsDone();
        assertEquals("X", deadline.isDone());
    }

    /**
     * Tests the behaviour of containDate() by instantiating a Deadline
     * with a date ld and checking if task contains the same date ld,
     * which is expected to make the method return true.
     */
    @Test
    public void containDate_sameDate_true() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate ld = LocalDate.parse("2024/11/11", dtf);
        assertTrue(new Deadline("get food", ld).containDate(ld));
    }

    /**
     * Tests the behaviour of containDate() by instantiating a Deadline
     * with a date ld and checking if task contains a different date,
     * which is expected to make the method return false.
     */
    @Test
    public void containDate_diffDate_false() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate ld = LocalDate.parse("2024/11/11", dtf);
        LocalDate diffDate = LocalDate.parse("2023/04/05", dtf);
        assertFalse(new Deadline("get food", ld).containDate(diffDate));
    }

    /**
     * Tests the behaviour of containKeyword() by searching for
     * a keyword that the task name is expected to completely match with.
     */
    @Test
    public void containKeyword_allMatchingName_true() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate ld = LocalDate.parse("2024/11/11", dtf);
        assertTrue(new Deadline("get food", ld).containKeyword("get food"));
    }

    /**
     * Tests the behaviour of containKeyword() by searching for
     * a keyword that the task name is expected to partially match with.
     */
    @Test
    public void containKeyword_partialMatchingName_true() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate ld = LocalDate.parse("2024/11/11", dtf);
        assertTrue(new Deadline("get food", ld).containKeyword("foo"));
    }

    /**
     * Tests the behaviour of containKeyword() by searching for
     * a keyword that the task name is expected to not match with.
     */
    @Test
    public void containKeyword_noMatchingName_false() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate ld = LocalDate.parse("2024/11/11", dtf);
        assertFalse(new Deadline("get food", ld).containKeyword("gt food"));
    }
}
