package duke.task;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import duke.exception.DukeBadInstructionFormatException;

/**
 * Test class for the <code>Deadline</code> class.
 *
 * @author Bo Kuan (LG17)
 * @version CS2103T AY22/23 Semester 2
 */
public class DeadlineTest {
    /**
     * Instantiate <code>Deadline</code> instance with wrong date format.
     * @result <code>DukeBadInstructionFormatException</code> will be thrown.
     */
    @Test
    @DisplayName("Deadline bad date handling test")
    public void wrongDateTest() {
        assertThrows(DukeBadInstructionFormatException.class, () -> {
            Task deadline = new Deadline("dl A", "Sunday");
        });
    }
}
