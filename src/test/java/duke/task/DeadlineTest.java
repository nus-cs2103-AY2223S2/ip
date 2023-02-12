package duke.task;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.enums.Views;

public class DeadlineTest {
    @Test
    public void testDeadlineString() throws DukeException {
        String expected = "[D][ ] TEST (by: Jan 2 2023 1030)";
        Task test = new Deadline("TEST", "/by2023-01-02T10:30");
        Assertions.assertEquals(expected, test.toString());
    }

    @Test
    public void testDeadlineMarkString() throws DukeException {
        String expected = "[D][X] TEST (by: Jan 2 2023 1030)";
        Task test = new Deadline("TEST", "/by2023-01-02T10:30");
        test.markAsDone();
        Assertions.assertEquals(expected, test.toString());
    }

    @Test
    public void testDeadlineTitleFail() {
        try {
            new Deadline("", "");
        } catch (DukeException e) {
            // Check if assert message is expected
            String expected = Views.EMPTY_ERR_STRING.str();
            Assertions.assertEquals(expected, e.getMessage());
            // Assertion failed, as expected
            return;
        }
        // If the code above didn't throw an DukeException, this line will be reached
        fail("Expected an DukeException to be thrown");
    }

    @Test
    public void testDeadlineDateParseFail() {
        try {
            new Deadline("TEST", "");
        } catch (DukeException e) {
            // Check if duke message is expected
            String expected = "Hey, ☹ please enter the date in this format YYYY-MM-DDTHH:MM "
                    + "like this: '2023-01-20T18:00'";
            Assertions.assertEquals(expected, e.getMessage());
            // Assertion failed, as expected
            return;
        }
        // If the code above didn't throw an DukeException, this line will be reached
        fail("Expected an DukeException to be thrown");
    }
}
