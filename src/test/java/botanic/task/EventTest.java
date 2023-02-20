package botanic.task;

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
     * Tests the behaviour of setDone() by trying to mark the task as done.
     */
    @Test
    public void setDone_setTrue_true() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate start = LocalDate.parse("2024/11/11", dtf);
        LocalDate end = LocalDate.parse("2024/11/25", dtf);
        Event event = new Event("school", start, end);
        event.setDone(true);
        assertEquals("[E][X] school (from: 11 NOVEMBER 2024 to: 25 NOVEMBER 2024)",
                event.toString());
    }

    /**
     * Tests the behaviour of setDone() by trying to mark the task as done,
     * then mark the task as not done.
     */
    @Test
    public void setDone_setTrueThenSetFalse_false() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate start = LocalDate.parse("2024/11/11", dtf);
        LocalDate end = LocalDate.parse("2024/11/25", dtf);
        Event event = new Event("school", start, end);
        event.setDone(true);
        event.setDone(false);
        assertEquals("[E][ ] school (from: 11 NOVEMBER 2024 to: 25 NOVEMBER 2024)",
                event.toString());
    }

    /**
     * Tests the behaviour of getIsDone() when a task is not marked.
     */
    @Test
    public void getIsDone_unmarked_whiteSpace() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate start = LocalDate.parse("2024/11/11", dtf);
        LocalDate end = LocalDate.parse("2024/11/25", dtf);
        assertEquals(" ", new Event("school event", start, end).getIsDone());
    }

    /**
     * Tests the behaviour of getIsDone() when a task is marked.
     */
    @Test
    public void getIsDone_marked_markedX() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate start = LocalDate.parse("2024/11/11", dtf);
        LocalDate end = LocalDate.parse("2024/11/25", dtf);
        Event event = new Event("school event", start, end);
        event.setDone(true);
        assertEquals("X", event.getIsDone());
    }

    /**
     * Tests the behaviour of hasDate() by checking for the same start date
     * that was used to instantiate the deadline object.
     */
    @Test
    public void hasDate_startDateContainsDate_true() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate start = LocalDate.parse("2024/11/11", dtf);
        LocalDate end = LocalDate.parse("2024/11/25", dtf);
        assertTrue(new Event("school event", start, end).hasDate(start));
    }

    /**
     * Tests the behaviour of hasDate() by checking for the same end date
     * that was used to instantiate the deadline object.
     */
    @Test
    public void hasDate_endDateContainsDate_true() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate start = LocalDate.parse("2024/11/11", dtf);
        LocalDate end = LocalDate.parse("2024/11/25", dtf);
        assertTrue(new Event("school event", start, end).hasDate(end));
    }

    /**
     * Tests the behaviour of hasDate() by checking for a date
     * that the task does not contain.
     */
    @Test
    public void hasDate_diffDate_false() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate start = LocalDate.parse("2024/11/11", dtf);
        LocalDate end = LocalDate.parse("2024/11/25", dtf);
        LocalDate diffDate = LocalDate.parse("2023/04/05", dtf);
        assertFalse(new Event("get food", start, end).hasDate(diffDate));
    }

    /**
     * Tests the behaviour of hasKeyword() by searching for
     * a keyword that the task name is expected to completely match with.
     */
    @Test
    public void hasKeyword_allMatchingName_true() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate start = LocalDate.parse("2024/11/11", dtf);
        LocalDate end = LocalDate.parse("2024/11/25", dtf);
        assertTrue(new Event("get food", start, end).hasKeyword("get food"));
    }

    /**
     * Tests the behaviour of hasKeyword() by searching for
     * a keyword that the task name is expected to partially match with.
     */
    @Test
    public void hasKeyword_partialMatchingName_true() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate start = LocalDate.parse("2024/11/11", dtf);
        LocalDate end = LocalDate.parse("2024/11/25", dtf);
        assertTrue(new Event("get food", start, end).hasKeyword("foo"));
    }

    /**
     * Tests the behaviour of hasKeyword() by searching for
     * a keyword that the task name is expected to not match with.
     */
    @Test
    public void hasKeyword_noMatchingName_true() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate start = LocalDate.parse("2024/11/11", dtf);
        LocalDate end = LocalDate.parse("2024/11/25", dtf);
        assertFalse(new Event("get food", start, end).hasKeyword("gt food"));
    }
}
