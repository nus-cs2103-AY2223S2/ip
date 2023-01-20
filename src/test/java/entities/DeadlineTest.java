package entities;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {
    @Test
    public void markTest(){
        Task tsk = new Deadline("Hello World", LocalDate.parse("2023-01-22"));
        tsk.markTask();
        assertTrue(tsk.isDone);
    }

    @Test
    public void unmarkTest(){
        Task tsk = new Deadline("Hello World", LocalDate.parse("2023-01-22"));
        tsk.unmarkTask();
        assertFalse(tsk.isDone);
    }

    @Test
    public void toStringTest() {
        Task task = new Deadline("Hello World",  LocalDate.parse("2023-01-22"));
        assertEquals("[D][ ] Hello World (by: sunday, Jan 22 2023)", task.toString());
    }
}
