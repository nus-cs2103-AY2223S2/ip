package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DukeException;

/**
 * The type Parser test.
 */
public class ParserTest {

    /**
     * Tests if Parser extracts task number from user input correctly.
     *
     * @throws DukeException the duke exception
     */
    @Test
    public void numberedCommandTest() throws DukeException {
        int actual = Parser.processMarkUnmarkDel("delete 3");
        int expected = 2;
        assertEquals(expected, actual);
    }

    /**
     * Tests if Parser parses deadline commands correctly.
     *
     * @throws DukeException the duke exception
     */
    @Test
    public void parseDeadlineTest() throws DukeException {
        String[] actual = Parser.processDeadline("deadline hw /by 2023-01-01");
        String[] expected = new String[]{"hw ", "2023-01-01"};
        assertEquals(expected.length, actual.length);
        assertEquals(expected[0], actual[0]);
        assertEquals(expected[1], expected[1]);
    }

}
