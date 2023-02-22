package chagee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import chagee.command.ChageeCommand;
import chagee.parser.ChageeParser;


public class ParserTest {

    @Test
    public void testParseCommand() {
        assertEquals(ChageeParser.parseCommand(ChageeCommand.BYE.text), ChageeCommand.BYE);
        assertEquals(ChageeParser.parseCommand(ChageeCommand.DEADLINE.text),
                ChageeCommand.DEADLINE);
        assertEquals(ChageeParser.parseCommand(ChageeCommand.DELETE.text), ChageeCommand.DELETE);
        assertEquals(ChageeParser.parseCommand(ChageeCommand.EVENT.text), ChageeCommand.EVENT);
        assertEquals(ChageeParser.parseCommand(ChageeCommand.FIND.text), ChageeCommand.FIND);
        assertEquals(ChageeParser.parseCommand(ChageeCommand.LIST.text), ChageeCommand.LIST);
        assertEquals(ChageeParser.parseCommand(ChageeCommand.MARK.text), ChageeCommand.MARK);
        assertEquals(ChageeParser.parseCommand(ChageeCommand.TODO.text), ChageeCommand.TODO);
        assertEquals(ChageeParser.parseCommand(ChageeCommand.UNMARK.text), ChageeCommand.UNMARK);
    }

    @Test
    public void testParseCommandArg() {
        // TODO : Add more test cases
        String inpuString = "todo something";
        ChageeCommand cmd = ChageeParser.parseCommand("todo something");
        String description = ChageeParser.parseCommandArgs(cmd, inpuString)[0];
        assertEquals(description, "something");
    }
}
