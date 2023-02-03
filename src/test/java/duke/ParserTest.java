package duke;

import duke.data.TypeOfTask;
import duke.parser.Parser;
import duke.exception.DukeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * duke.ParserTest class tests the functionality of parser with multiple different test cases
 *
 * @author Haiqel Bin Hanaffi (Acerizm)
 */
public class ParserTest {

    /**
     * Test if illegal inputs from the user is detected for "delete" command
     * @throws DukeException when user enters illegal inputs
     */
    @Test
    void testDeleteInputException() throws DukeException {
        Parser parser = new Parser();
        String[] fakeInput = new String[]{"Delete", "2", "3"};
        Assertions.assertThrows(DukeException.class, () -> parser.convertToUserInput(fakeInput, TypeOfTask.delete, ""));
    }

    /**
     * Test if DukeException is returned if local date cannot be converted
     * @throws DukeException when user enters illegal inputs
     */
    @Test
    void testConvertToLocalDateException() throws DukeException {
        Parser parser = new Parser();
        //String[] fakeInput = new String[]{"20/20/20"};
        String fakeInput = "20/20/20";
        Assertions.assertThrows(DukeException.class, () -> parser.covertToLocalDate(fakeInput));
    }

    /**
     * Test if DukeException is returned if local time cannot be converted
     * @throws DukeException when user enters illegal inputs
     */
    @Test
    void testConvertToLocalTimeException() throws DukeException {
        Parser parser = new Parser();
        String fakeInput = "2PM";
        Assertions.assertThrows(DukeException.class, () -> parser.convertToLocalTime(fakeInput));
    }
}
