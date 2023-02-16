package task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {
            @Test
        void DeadlineText() {
            Deadline Deadline = new Deadline("testDeadline", LocalDate.of(2023, 1,1));
            assertEquals("testDeadline", Deadline.getDescription(), "getNameOfTask() works");
            assertFalse(Deadline.isDone(), "task done initiated to false");
            Deadline.markDone();
            assertTrue(Deadline.isDone(), "taskDone() works");
            Deadline.markNotDone();
            assertFalse(Deadline.isDone(), "taskNotDone() works");
            assertEquals("D|testDeadline|0|2023-01-01", Deadline.toText(), "toText() when not done works");
            Deadline.markDone();
            assertEquals("D|testDeadline|1|2023-01-01", Deadline.toText(), "toText() when done works");
            Deadline.markNotDone();
            assertEquals("[D][ ] testDeadline (by: Jan-01-2023)", Deadline.toString(), "toString() when not done works");
            Deadline.markDone();
            assertEquals("[D][X] testDeadline (by: Jan-01-2023)", Deadline.toString(), "toString() when done works");
        }
        

}