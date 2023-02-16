package task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {
    @Test
    void EventText() {
        Event Event = new Event("testEvent", LocalDate.of(2023, 1, 1), LocalDate.of(2023, 2, 1));
        assertEquals("testEvent", Event.getDescription(), "getNameOfTask() works");
        assertFalse(Event.isDone(), "task done initiated to false");
        Event.markDone();
        assertTrue(Event.isDone(), "taskDone() works");
        Event.markNotDone();
        assertFalse(Event.isDone(), "taskNotDone() works");
        assertEquals("E|testEvent|0|2023-01-01|2023-02-01", Event.toText(), "toText() when not done works");
        Event.markDone();
        assertEquals("E|testEvent|1|2023-01-01|2023-02-01", Event.toText(), "toText() when done works");
        Event.markNotDone();
        assertEquals("[E][ ] testEvent (from: Jan-01-2023 to: Feb-01-2023)", Event.toString(), "toString() when not done works");
        Event.markDone();
        assertEquals("[E][X] testEvent (from: Jan-01-2023 to: Feb-01-2023)", Event.toString(), "toString() when done works");
    }

}