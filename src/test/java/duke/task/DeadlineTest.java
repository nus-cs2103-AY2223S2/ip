package duke.task;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import duke.exception.DukeBadInstructionFormatException;

public class DeadlineTest {
    @Test
    @DisplayName("Deadline bad date handling test")
    public void wrongDateTest() {
        assertThrows(DukeBadInstructionFormatException.class, () -> {
            Task deadline = new Deadline("dl A", "Sunday");
        });
    }
}
