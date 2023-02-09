package chattime.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

public class EventTest {
    private LocalDate from = LocalDate.of(2023, 1, 1);
    private LocalDate to = LocalDate.of(2023, 2, 2);
    private LocalTime time = LocalTime.of(10, 0);
    private Event testTask = new Event("Test", from, time, to, time);

    @Test
    public void taskWithCodeTest() {
        assertEquals("[E] Test", testTask.taskWithCode());
    }

    @Test
    public void toDataStringTest() {
        assertEquals("E @ 0 @ Test @ 2023-01-01 @ 10:00 @ 2023-02-02 @ 10:00", testTask.toDataString());
    }

    @Test
    public void isOnDateTest() {
        assertFalse(testTask.isOnDate(LocalDate.of(2023, 3, 3)));
    }

    @Test
    public void toStringTest() {
        LocalDate from = LocalDate.of(2023, 1, 1);
        LocalDate to = LocalDate.of(2023, 2, 2);
        Event testTask = new Event("Test", from, null, to, null);
        assertEquals("[E][ ] Test (from: Jan 01 2023 to: Feb 02 2023)", testTask.toString());
    }
}
