package berry.parser;

import berry.command.Command;
import berry.command.ExitCommand;
import berry.command.ListCommand;
import berry.exception.BerryException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    private Parser parser;

    @BeforeEach
    public void setUp() {
        parser = new Parser();
    }

    /*
     * Tests for 0-argument commands =======================================================================
     */

    @Test
    public void parse_exitCommand_parsedCorrectly() throws BerryException {
        final String input = "bye";
        parseAndAssertCommandType(input, ExitCommand.class);
    }

    @Test
    public void parse_listCommand_parsedCorrectly() throws BerryException {
        final String input = "list";
        parseAndAssertCommandType(input, ListCommand.class);
    }

    /*
     * Utility methods ====================================================================================
     */

    /**
     * Parses input and asserts the class/type of the returned command object.
     *
     * @param input to be parsed
     * @param expectedCommandClass expected class of returned command
     * @return the parsed command object
     */
    private <T extends Command> T parseAndAssertCommandType(
            String input, Class<T> expectedCommandClass) throws BerryException {
        final Command result = parser.parseInput(input);
        assertTrue(result.getClass().isAssignableFrom(expectedCommandClass));
        return (T) result;
    }

}
