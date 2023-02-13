package jarvis.parser;

import org.junit.jupiter.api.Test;

import jarvis.command.ByeCommand;
import jarvis.command.IntroCommand;
import jarvis.exception.command.InvalidActionException;

public class ParserTest {
    private static final String COMMAND_INTRO = "hi";
    private static final String COMMAND_BYE = "bye";

    @Test
    public void introTest() throws InvalidActionException {
        assert Parser.parse(COMMAND_INTRO) instanceof IntroCommand;
    }

    @Test
    public void byeTest() throws InvalidActionException {
        assert Parser.parse(COMMAND_BYE) instanceof ByeCommand;
    }
}
