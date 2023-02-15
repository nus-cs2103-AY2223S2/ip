package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;
import duke.parser.Parser;

public class ParserTest {
    @Test
    public void parseCommand_listCommand_success() {
        Parser parser = new Parser();
        List<String> expected = Arrays.asList("list");
        try {
            assertIterableEquals(expected, parser.parseCommand("list"));
        } catch (DukeException e) {
            fail("Parser should not throw an exception");
        }
    }

    @Test
    public void parseCommand_markCommand_success() {
        Parser parser = new Parser();
        List<String> expected = Arrays.asList("mark", "1");
        try {
            assertIterableEquals(expected, parser.parseCommand("mark 1"));
        } catch (DukeException e) {
            fail("Parser should not throw an exception");
        }
    }

    @Test
    public void parseCommand_markCommandWithSpace_success() {
        Parser parser = new Parser();
        List<String> expected = Arrays.asList("mark", "1");
        try {
            assertIterableEquals(expected, parser.parseCommand("   mark    1   "));
        } catch (DukeException e) {
            fail("Parser should not throw an exception");
        }
    }

    @Test
    public void parseCommand_deleteCommand_success() {
        Parser parser = new Parser();
        List<String> expected = Arrays.asList("delete", "1");
        try {
            assertIterableEquals(expected, parser.parseCommand("delete 1"));
        } catch (DukeException e) {
            fail("Parser should not throw an exception");
        }
    }

    @Test
    public void parseCommand_deleteCommandMultipleIndex_success() {
        Parser parser = new Parser();
        List<String> expected = Arrays.asList("delete", "1", "2", "3");
        try {
            assertIterableEquals(expected, parser.parseCommand("delete 1 2 3"));
        } catch (DukeException e) {
            fail("Parser should not throw an exception");
        }
    }

    @Test
    public void parseCommand_eventCommand_success() {
        Parser parser = new Parser();
        List<String> expected = Arrays.asList("event", "read book", "2022-02-02-02-02", "2022-02-02-02-02");
        try {
            assertIterableEquals(expected, parser.parseCommand("event read book "
                    + "/from 2022-02-02-02-02 /to 2022-02-02-02-02 "));
        } catch (DukeException e) {
            fail("Parser should not throw an exception");
        }
    }

    @Test
    public void parseCommand_unknownCommand_exceptionThrown() {
        Parser parser = new Parser();
        try {
            parser.parseCommand("hello");
            fail("Expected an exception but it was not thrown");
        } catch (DukeException e) {
            assertEquals("I have no idea what you just said.", e.getMessage());
        }
    }

    @Test
    public void parseCommand_invalidEventCommand_exceptionThrown() {
        Parser parser = new Parser();
        try {
            parser.parseCommand("event read book /from 2022-02-02-02 /to 2022-02-02-02");
            fail("Expected an exception but it was not thrown");
        } catch (DukeException e) {
            assertEquals("The command requires at least one argument in the format: yyyy-MM-dd-HH-mm. "
                    + "Example: 2023-02-02-02-09", e.getMessage());
        }
    }
}
