package entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;



public class DeadlineTest {
    @Test
    public void markTest_testIsMarked_nullReturned() {
        Task tsk = new Deadline("Hello World", LocalDate.parse("2023-01-22"));
        tsk.markTask();
        assertTrue(tsk.isDone);
    }

    @Test
    public void unmarkTest_testIsUnmarked_nullReturned() {
        Task tsk = new Deadline("Hello World", LocalDate.parse("2023-01-22"));
        tsk.unmarkTask();
        assertFalse(tsk.isDone);
    }

    @Test
    public void toStringTest_printString_validRepresentationReturned() {
        Task task = new Deadline("Hello World", LocalDate.parse("2023-01-22"));
        assertEquals("[D][ ] Hello World (by: sunday, Jan 22 2023)", task.toString());
    }
}
