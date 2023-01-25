package chattime.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class DeadlineTest {
    @Test
    public void toDataStringTest() {
        Deadline testTask = new Deadline("Test", LocalDate.now(), null);
        assertEquals(String.format("D @ 0 @ Test @ %s @ 0", LocalDate.now()), testTask.toDataString());
    }

    @Test
    public void onDateTest() {
        Deadline testTask = new Deadline("Test", LocalDate.now(), null);
        assertEquals(true, testTask.onDate(LocalDate.now()));
    }

    @Test
    public void toStringTest() {
        Deadline testTask = new Deadline("Test",
                LocalDate.of(2023, 1, 1), null);
        assertEquals("[D][ ] Test (by: Jan 01 2023 )", testTask.toString());
    }
}