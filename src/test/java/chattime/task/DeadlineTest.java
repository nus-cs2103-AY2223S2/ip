package chattime.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    private Deadline testTask = new Deadline("Test", LocalDate.now());

    @Test
    public void toDataStringTest() {
        assertEquals(String.format("D @ 0 @ Test @ %s @ 0", LocalDate.now()), testTask.toDataString());
    }

    @Test
    public void taskWithCodeTest() {
        assertEquals("[D] Test", testTask.taskWithCode());
    }

    @Test
    public void isOnDateTest() {
        assertEquals(true, testTask.isOnDate(LocalDate.now()));
    }

    @Test
    public void toStringTest() {
        Deadline testTask = new Deadline("Test",
                LocalDate.of(2023, 1, 1));
        assertEquals("[D][ ] Test (by: Jan 01 2023)", testTask.toString());
    }
}
