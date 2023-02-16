package task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
class EventTest {

    @Test
    void testInitiateEvent() {
        Event newEvent = new Event("testEvent ", LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 2, 1));
        assertEquals("testEvent ", newEvent.getDescription(), "getNameOfTask()");
        assertFalse(newEvent.isDone(), "task done initiated to false");
    }

    @Test
    void testMarkTaskDone() {
        Event newEvent = new Event("testEvent ", LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 2, 1));
        newEvent.markDone();
        assertTrue(newEvent.isDone(), "taskDone()");
        assertEquals("E|1| |testEvent |2023-01-01|2023-02-01", newEvent.toText(), "toText() when done");
        assertEquals("[E][X][ ] testEvent (from: Jan-01-2023 to: Feb-01-2023)", newEvent.toString(),
                "toString() when done");
    }
    @Test
    void testMarkTaskNotDone() {
        Event newEvent = new Event("testEvent ", LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 2, 1));
        newEvent.markNotDone();
        assertFalse(newEvent.isDone(), "taskNotDone()");
        assertEquals("E|0| |testEvent |2023-01-01|2023-02-01", newEvent.toText(), "toText() when done");
        assertEquals("[E][ ][ ] testEvent (from: Jan-01-2023 to: Feb-01-2023)", newEvent.toString(),
                "toString() when not done");
    }
    @Test
    void testRecurrence() {
        Event newEvent = new Event("testEvent ", LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 2, 1));
        newEvent.setRecurrence("weekly");
        assertEquals(newEvent.getRecurrence(), "W", "set recurrence of task to weekly");
        assertEquals("E|0|W|testEvent |2023-01-01|2023-02-01", newEvent.toText(), "toText() when not done");
        assertEquals("[E][ ][W] testEvent (from: Jan-01-2023 to: Feb-01-2023)", newEvent.toString(),
                "toString() when not done");
    }
}
