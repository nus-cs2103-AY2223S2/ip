package seedu.duke;

import duke.Deadline;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {
    @Test
    public void deadline_emptyDescription_exceptionThrown() {
        try {
            new Deadline("   ", "1");
            fail();
        } catch (Exception e) {
            assertEquals("The description of a deadline cannot be empty.", e.getMessage());
        }
    }
    @Test
    public void deadline_emptyByTime_exceptionThrown() {
        try {
            new Deadline("1", "   ");
            fail();
        } catch (Exception e) {
            assertEquals("The 'by' date of a deadline cannot be empty.", e.getMessage());
        }
    }
}
