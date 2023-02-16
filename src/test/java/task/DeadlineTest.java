package task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
class DeadlineTest {
    @Test
    void testInitiateDeadline() {
        Deadline newDeadline = new Deadline("testDeadline", LocalDate.of(2023, 1, 1));
        assertEquals("testDeadline", newDeadline.getDescription(), "getNameOfTask()");
        assertFalse(newDeadline.isDone(), "task done initiated to false");
    }

    @Test
    void testMarkTaskDone() {
        Deadline newDeadline = new Deadline("testDeadline", LocalDate.of(2023, 1, 1));
        newDeadline.markDone();
        assertTrue(newDeadline.isDone(), "taskDone()");
        assertEquals("D|1| |testDeadline|2023-01-01", newDeadline.toText(), "toText() when done");
        assertEquals("[D][X][ ] testDeadline (by: Jan-01-2023)", newDeadline.toString(), "toString() when done");
    }

    @Test
    void testMarkDone() {
        Deadline newDeadline = new Deadline("testDeadline", LocalDate.of(2023, 1, 1));
        newDeadline.markNotDone();
        assertFalse(newDeadline.isDone(), "taskNotDone()");
        assertEquals("D|0| |testDeadline|2023-01-01", newDeadline.toText(), "toText() when not done");
        assertEquals("[D][ ][ ] testDeadline (by: Jan-01-2023)", newDeadline.toString(), "toString() when not done");
    }

    @Test
    void testRecurrence() {
        Deadline newDeadline = new Deadline("testDeadline", LocalDate.of(2023, 1, 1));
        newDeadline.setRecurrence("daily");
        assertEquals(newDeadline.getRecurrence(), "D", "set recurrence of task to daily");
        assertEquals("D|0|D|testDeadline|2023-01-01", newDeadline.toText(), "toText() when not done");
        assertEquals("[D][ ][D] testDeadline (by: Jan-01-2023)", newDeadline.toString(), "toString() when not done");
    }
}
