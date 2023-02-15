package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

/**
 * Tests the behavior of Event class.
 */
public class EventTest {
    /**
     * Tests the behaviour of toString().
     */
    @Test
    public void testStringConversion() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate start = LocalDate.parse("2024/11/11", dtf);
        LocalDate end = LocalDate.parse("2024/11/25", dtf);
        assertEquals("[E][ ] school (from: 11 NOVEMBER 2024 to: 25 NOVEMBER 2024)",
                new Event("school", start, end).toString());
    }

    /**
     * Tests the behaviour of formatForStorage().
     */
    @Test
    public void testFormatStore() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate start = LocalDate.parse("2024/11/11", dtf);
        LocalDate end = LocalDate.parse("2024/11/25", dtf);
        assertEquals("E | 0 | school event | 2024/11/11 | 2024/11/25",
                new Event("school event", start, end).formatForStorage());
    }

    /**
     * Tests the behaviour of mark().
     */
    @Test
    public void testMark() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate start = LocalDate.parse("2024/11/11", dtf);
        LocalDate end = LocalDate.parse("2024/11/25", dtf);
        Event event = new Event("school", start, end);
        event.markIsDone();
        assertEquals("[E][X] school (from: 11 NOVEMBER 2024 to: 25 NOVEMBER 2024)",
                event.toString());
    }

    /**
     * Tests the behaviour of unmark().
     */
    @Test
    public void testUnmark() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate start = LocalDate.parse("2024/11/11", dtf);
        LocalDate end = LocalDate.parse("2024/11/25", dtf);
        Event event = new Event("school", start, end);
        event.markIsDone();
        event.unmarkIsDone();
        assertEquals("[E][ ] school (from: 11 NOVEMBER 2024 to: 25 NOVEMBER 2024)",
                event.toString());
    }

    /**
     * Tests the behaviour of isDone() when a task is not marked.
     */
    @Test
    public void isDone_unmarked_whiteSpace() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate start = LocalDate.parse("2024/11/11", dtf);
        LocalDate end = LocalDate.parse("2024/11/25", dtf);
        assertEquals(" ", new Event("school event", start, end).isDone());
    }

    /**
     * Tests the behaviour of isDone() when a task is marked.
     */
    @Test
    public void isDone_marked_markedX() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate start = LocalDate.parse("2024/11/11", dtf);
        LocalDate end = LocalDate.parse("2024/11/25", dtf);
        Event event = new Event("school event", start, end);
        event.markIsDone();
        assertEquals("X", event.isDone());
    }

    /**
     * Tests the behaviour of containDate() by checking for the same start date
     * that was used to instantiate the deadline object.
     */
    @Test
    public void containDate_startDateContainsDate_true() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate start = LocalDate.parse("2024/11/11", dtf);
        LocalDate end = LocalDate.parse("2024/11/25", dtf);
        assertTrue(new Event("school event", start, end).containDate(start));
    }

    /**
     * Tests the behaviour of containDate() by checking for the same end date
     * that was used to instantiate the deadline object.
     */
    @Test
    public void containDate_endDateContainsDate_true() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate start = LocalDate.parse("2024/11/11", dtf);
        LocalDate end = LocalDate.parse("2024/11/25", dtf);
        assertTrue(new Event("school event", start, end).containDate(end));
    }

    /**
     * Tests the behaviour of containDate() by checking for a date
     * that the task does not contain.
     */
    @Test
    public void containDate_diffDate_false() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate start = LocalDate.parse("2024/11/11", dtf);
        LocalDate end = LocalDate.parse("2024/11/25", dtf);
        LocalDate diffDate = LocalDate.parse("2023/04/05", dtf);
        assertFalse(new Event("get food", start, end).containDate(diffDate));
    }

    /**
     * Tests the behaviour of containKeyword() by searching for
     * a keyword that the task name is expected to completely match with.
     */
    @Test
    public void containKeyword_allMatchingName_true() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate start = LocalDate.parse("2024/11/11", dtf);
        LocalDate end = LocalDate.parse("2024/11/25", dtf);
        assertTrue(new Event("get food", start, end).containKeyword("get food"));
    }

    /**
     * Tests the behaviour of containKeyword() by searching for
     * a keyword that the task name is expected to partially match with.
     */
    @Test
    public void containKeyword_partialMatchingName_true() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate start = LocalDate.parse("2024/11/11", dtf);
        LocalDate end = LocalDate.parse("2024/11/25", dtf);
        assertTrue(new Event("get food", start, end).containKeyword("foo"));
    }

    /**
     * Tests the behaviour of containKeyword() by searching for
     * a keyword that the task name is expected to not match with.
     */
    @Test
    public void containKeyword_noMatchingName_true() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate start = LocalDate.parse("2024/11/11", dtf);
        LocalDate end = LocalDate.parse("2024/11/25", dtf);
        assertFalse(new Event("get food", start, end).containKeyword("gt food"));
    }
}
