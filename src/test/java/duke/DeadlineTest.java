package duke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.exceptions.InvalidDateFormatException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class DeadlineTest {
    @Test
    public void testAttributesSuccess() throws InvalidDateFormatException {
        Deadline dl = new Deadline("meeting", "2022-05-06");
        assertEquals(dl.getTaskName(), "meeting");
        assertEquals(dl.getStatus(), "O");
    }

    @Test
    public void testAttributesInvalidDate() {
        InvalidDateFormatException thrown = assertThrows(InvalidDateFormatException.class, () -> {
            Deadline dl = new Deadline("meeting", "2pm");
        });

        Assertions.assertEquals("OOPS! Invalid date and time format. Please change to YYYY-MM-DD", thrown.getMessage());
    }
}
