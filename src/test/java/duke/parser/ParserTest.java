package duke.parser;

import duke.command.ExitCommand;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void parse_emptyCommand_exceptionThrown() {
        try {
            assertNull(Parser.parse(""));
            fail();
        } catch (Exception e) {
            assertEquals("You have not entered anything...", e.getMessage());
        }
    }

    @Test
    public void parse_invalidCommand_exceptionThrown() {
        try {
            assertNull(Parser.parse("blah"));
            fail();
        } catch (Exception e) {
            assertEquals("Sorry... I did not understand that :/", e.getMessage());
        }
    }

    @Test
    public void parse() {
        try {
            assertEquals(new ExitCommand().isExitCommand(), Parser.parse("bye").isExitCommand());
        } catch (Exception e) {
            fail();
        }
    }

}
