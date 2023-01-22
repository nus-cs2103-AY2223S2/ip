package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import book.task.Deadline;

public class DeadlineTest {
    @Test
    public void instanceTest() {
        Deadline deadline = new Deadline("test", LocalDateTime.parse("01/01/00-1200",
                DateTimeFormatter.ofPattern("dd/MM/yy-HHmm")));
        assertEquals("[D][ ] test (by: 01 Jan (Sat) - 12:00PM)", deadline.toString());
    }
    @Test
    public void saveStringTest() {
        Deadline deadline = new Deadline("test", LocalDateTime.parse("01/01/00-1200",
                DateTimeFormatter.ofPattern("dd/MM/yy-HHmm")));
        assertEquals("D;false;test;01/01/00-1200", deadline.saveString());
    }
    @Test
    public void markTest() {
        Deadline deadline = new Deadline("test", LocalDateTime.parse("01/01/00-1200",
                DateTimeFormatter.ofPattern("dd/MM/yy-HHmm")));
        deadline.mark();
        assertEquals("[D][X] test (by: 01 Jan (Sat) - 12:00PM)", deadline.toString());
    }
    @Test
    public void unmarkTest() {
        Deadline deadline = new Deadline("test", LocalDateTime.parse("01/01/00-1200",
                DateTimeFormatter.ofPattern("dd/MM/yy-HHmm")));
        deadline.mark();
        deadline.unmark();
        assertEquals("[D][ ] test (by: 01 Jan (Sat) - 12:00PM)", deadline.toString());
    }
}
