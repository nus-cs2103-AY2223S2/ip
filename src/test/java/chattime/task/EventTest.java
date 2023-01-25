package chattime.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventTest {
    @Test
    public void toDataStringTest() {
        LocalDate from = LocalDate.of(2023, 1, 1);
        LocalDate to = LocalDate.of(2023, 2, 2);
        LocalTime time = LocalTime.of(10, 0);
        Event testTask = new Event("Test", from, time, to, time);
        assertEquals("E @ 0 @ Test @ 2023-01-01 @ 10:00 @ 2023-02-02 @ 10:00", testTask.toDataString());
    }

    @Test
    public void onDateTest() {
        LocalDate from = LocalDate.of(2023, 1, 1);
        LocalDate to = LocalDate.of(2023, 2, 2);
        LocalTime time = LocalTime.of(10, 0);
        Event testTask = new Event("Test", from, time, to, time);
        assertFalse(testTask.onDate(LocalDate.of(2023, 3, 3)));
    }

    @Test
    public void toStringTest() {
        LocalDate from = LocalDate.of(2023, 1, 1);
        LocalDate to = LocalDate.of(2023, 2, 2);
        Event testTask = new Event("Test", from, null, to, null);
        assertEquals("[E][ ] Test (from: Jan 01 2023 to: Feb 02 2023 )", testTask.toString());
    }
}