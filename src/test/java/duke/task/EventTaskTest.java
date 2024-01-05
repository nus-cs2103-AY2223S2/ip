package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class EventTaskTest {
    @Test
    public void testEventTask_initialization_valuesAreCorrect() throws Exception {
        LocalDateTime start = LocalDateTime.of(2022, 10, 1, 9, 0);
        LocalDateTime end = LocalDateTime.of(2022, 10, 1, 18, 0);
        EventTask event = new EventTask("Conference", start, end);
        assertEquals("Conference", event.getInformation());
        assertEquals(start, event.getStartDate());
        assertEquals(end, event.getEndDate());
        assertFalse(event.getStatus());
    }

    @Test
    public void testMatchesDate_compareDates_returnsTrueIfMatching() throws Exception {
        LocalDateTime start = LocalDateTime.of(2022, 10, 1, 9, 0);
        LocalDateTime end = LocalDateTime.of(2022, 10, 1, 18, 0);
        EventTask event = new EventTask("Conference", start, end);
        LocalDate date1 = LocalDate.of(2022, 10, 1);
        LocalDate date2 = LocalDate.of(2022, 10, 2);
        assertTrue(event.matchesDate(date1));
        assertFalse(event.matchesDate(date2));
    }

    @Test
    public void testStorageString_getStorageString_returnsCorrectString() throws Exception {
        LocalDateTime start = LocalDateTime.of(2022, 10, 1, 9, 0);
        LocalDateTime end = LocalDateTime.of(2022, 10, 1, 18, 0);
        EventTask event = new EventTask("Conference", start, end);
        assertEquals("[E] | [ ] |  Conference | 2022-10-01T09:00 | 2022-10-01T18:00", event.storageString());
    }

    @Test
    public void testToString_getToString_returnsCorrectString() throws Exception {
        LocalDateTime start = LocalDateTime.of(2022, 10, 1, 9, 0);
        LocalDateTime end = LocalDateTime.of(2022, 10, 1, 18, 0);
        EventTask event = new EventTask("Conference", start, end);
        assertEquals("[E][ ] Conference ( from: Oct 01 2022 09:00 to: Oct 01 2022 18:00 )", event.toString());
    }

    @Test
    public void testEquals_compareTwoEvents_returnsTrueIfEqual() throws Exception {
        LocalDateTime start = LocalDateTime.of(2022, 10, 1, 9, 0);
        LocalDateTime end = LocalDateTime.of(2022, 10, 1, 18, 0);
        EventTask event1 = new EventTask("Conference", start, end);
        EventTask event2 = new EventTask("Conference", start, end);
        assertEquals(event1, event2);
    }

    @Test
    public void testEquals_compareTwoEventsWithDifferentInformation_returnsFalse() throws Exception {
        LocalDateTime start = LocalDateTime.of(2022, 10, 1, 9, 0);
        LocalDateTime end = LocalDateTime.of(2022, 10, 1, 18, 0);
        EventTask event1 = new EventTask("Conference", start, end);
        EventTask event2 = new EventTask("Meeting", start, end);
        assertNotEquals(event1, event2);
    }

    @Test
    public void testEquals_compareTwoEventsWithDifferentStartDates_returnsFalse() throws Exception {
        LocalDateTime start1 = LocalDateTime.of(2022, 10, 1, 9, 0);
        LocalDateTime end1 = LocalDateTime.of(2022, 10, 1, 18, 0);
        EventTask event1 = new EventTask("Conference", start1, end1);
        LocalDateTime start2 = LocalDateTime.of(2022, 10, 2, 9, 0);
        LocalDateTime end2 = LocalDateTime.of(2022, 10, 2, 18, 0);
        EventTask event2 = new EventTask("Conference", start2, end2);
        assertNotEquals(event1, event2);
    }

    @Test
    public void testEquals_compareTwoEventsWithDifferentEndDates_returnsFalse() throws Exception {
        LocalDateTime start1 = LocalDateTime.of(2022, 10, 1, 9, 0);
        LocalDateTime end1 = LocalDateTime.of(2022, 10, 1, 18, 0);
        EventTask event1 = new EventTask("Conference", start1, end1);
        LocalDateTime start2 = LocalDateTime.of(2022, 10, 1, 9, 0);
        LocalDateTime end2 = LocalDateTime.of(2022, 10, 1, 19, 0);
        EventTask event2 = new EventTask("Conference", start2, end2);
        assertNotEquals(event1, event2);
    }
}
