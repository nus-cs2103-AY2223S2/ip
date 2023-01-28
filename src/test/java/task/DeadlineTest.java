package task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testDeadlineString() {
        Deadline deadline = new Deadline("test", LocalDate.parse("2023-01-30"));
        String expected = "[D][ ] test (by: Jan 30 2023)";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void testDeadlineData() {
        Deadline deadline = new Deadline("test", LocalDate.parse("2023-01-30"));
        String expected = "D | 0 | test | 2023-01-30";
        assertEquals(expected, deadline.toData());
    }
}
