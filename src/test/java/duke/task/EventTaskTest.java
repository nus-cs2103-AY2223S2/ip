package duke.task;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class EventTaskTest {

    @Test
    public void eventTask_badDates_assertionError() {
        assertThrows(AssertionError.class, () -> new EventTask(
                "bad dates",
                LocalDate.of(2022, 2, 8),
                LocalDate.of(2022, 2, 5)
        ));
    }
}
