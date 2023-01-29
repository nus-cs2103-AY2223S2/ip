package duke;

import duke.command.ListCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void parse_listInput_ListCommand() throws Exception {
        Parser parser = new Parser();
        assertEquals(parser.parse("list"), new ListCommand());
    }

    @Test public void parse_invalidInput_DukeException() {
        Parser parser = new Parser();
        try {
            assertNull(parser.parse("randomness :)"));
            fail();
        } catch (DukeException de) {
            assertEquals(de.getMessage(), "Invalid command");
        }
    }
}
