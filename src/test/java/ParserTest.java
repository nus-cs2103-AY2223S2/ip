import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import data.TaskManager;
import errors.DukeInvalidCommandException;
import ui.Response;
import utils.Parser;


public class ParserTest {


    @Test
    public void getSelectionTest() {
        // Valid input
        Parser parser = new Parser(new TaskManager());
        int expected = 2;
        try {
            int result = parser.getSelection("select 3");
            assertEquals(expected, result);
        } catch (DukeInvalidCommandException e) {
            fail();
        }

        // invalid integer format
        String expectedStr = Response.INVALID_COMMAND.toString();
        try {
            int result = parser.getSelection("select three");
            fail();
        } catch (DukeInvalidCommandException e) {
            assertEquals(expectedStr, e.getMessage());
        }

        // invalid input (out of bounds)
        expectedStr = Response.INVALID_COMMAND.toString();
        try {
            int result = parser.getSelection("3");
            fail();
        } catch (DukeInvalidCommandException e) {
            assertEquals(expectedStr, e.getMessage());
        }
    }

    @Test
    public void testCreateEvent() {
        TaskManager taskManager = new TaskManager();
        Parser parser = new Parser(taskManager);

        // valid input
        String input = "event meeting with Alice /from 23/1/2023 1800 /to 30/2/2024 1645";
        String expectedOutput = Response.EVENT_ADDED
                + "\n1. EVENT: meeting with Alice (From Jan 23 2023 6:00PM to Mar 01 2024 4:45PM)[ ]\n";
        String actualOutput = parser.createEvent(input);
        assertEquals(expectedOutput, actualOutput);

        // missing details
        input = "event meeting with Alice /from 30/2/2024";
        expectedOutput = Response.MISSING_EVENT_DETAILS.toString();
        actualOutput = parser.createEvent(input);
        assertEquals(expectedOutput, actualOutput);

        // invalid date format
        input = "event meeting with John /from 23/1/202 1800 /to 30/02/2024 45";
        expectedOutput = Response.DATE_FORMAT_INCORRECT.toString();
        actualOutput = parser.createEvent(input);
        assertEquals(expectedOutput, actualOutput);

        // swapped /from and /to
        input = "event meeting with Peter /to 23/1/2023 1800 /from 30/2/2024 1645";
        expectedOutput = Response.INVALID_COMMAND.toString();
        actualOutput = parser.createEvent(input);
        assertEquals(expectedOutput, actualOutput);
    }

}
