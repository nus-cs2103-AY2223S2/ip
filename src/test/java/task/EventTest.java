package task;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void shouldCountDaysToEventCorrectly() {
        Event event = new Event("demo", LocalDate.parse("2023-01-01"), LocalDate.parse("2023-01-03"));
        long[] actual =
                {event.daysToEvent(LocalDate.parse("2022-12-30")), event.daysToEvent(LocalDate.parse("2022-12-31")),
                        event.daysToEvent(LocalDate.parse("2023-01-01")),
                        event.daysToEvent(LocalDate.parse("2023-01-02")),
                        event.daysToEvent(LocalDate.parse("2023-01-03")),
                        event.daysToEvent(LocalDate.parse("2023-01-04")),
                        event.daysToEvent(LocalDate.parse("2023-01-05"))};

        long[] expected = {2, 1, 0, 0, 0, -1, -2};

        assertArrayEquals(expected, actual);
    }
}
