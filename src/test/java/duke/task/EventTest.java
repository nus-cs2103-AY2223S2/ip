package duke.task;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.enums.Views;

public class EventTest {
    @Test
    public void testEventString() throws DukeException {
        String expected = "[E][ ] TEST (from: Jan 2 2023 1030 to: Jan 2 2023 1035)";
        Task test = new Event("TEST", "/from2023-01-02T10:30", "/to2023-01-02T10:35");
        Assertions.assertEquals(expected, test.toString());
    }

    @Test
    public void testEventMarkString() throws DukeException {
        String expected = "[E][X] TEST (from: Jan 2 2023 1030 to: Jan 2 2023 1035)";
        Task test = new Event("TEST", "/from2023-01-02T10:30", "/to2023-01-02T10:35");
        test.markAsDone();
        Assertions.assertEquals(expected, test.toString());
    }

    @Test
    public void testEventTitleFail() throws DukeException {
        try {
            new Event("", "", "");
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
    public void testEventDateParseFail() {
        try {
            new Event("TEST", "", "");
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

    @Test
    public void testEventDateOrderFail() throws DukeException {
        try {
            new Event("TEST", "2023-01-20T19:00", "2023-01-20T18:00");
        } catch (DukeException e) {
            // Check if assert message is expected
            String expected = "Hey, ☹ you seem to have ordered the /to and /from wrongly";
            Assertions.assertEquals(expected, e.getMessage());
            // Assertion failed, as expected
            return;
        }
        // If the code above didn't throw an DukeException, this line will be reached
        fail("Expected an DukeException to be thrown");
    }
}
