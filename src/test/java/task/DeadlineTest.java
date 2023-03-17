package task;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void shouldCountDaysToDeadlineCorrectly() {
        Deadline deadline = new Deadline("demo", LocalDate.parse("2023-01-01"));
        long[] actual = {
                deadline.daysToDeadline(LocalDate.parse("2022-12-30")),
                deadline.daysToDeadline(LocalDate.parse("2022-12-31")),
                deadline.daysToDeadline(LocalDate.parse("2023-01-01")),
                deadline.daysToDeadline(LocalDate.parse("2023-01-02")),
                deadline.daysToDeadline(LocalDate.parse("2023-01-03"))
        };

        long[] expected = {2, 1, 0, -1, -2};

        assertArrayEquals(expected, actual);
    }
}
