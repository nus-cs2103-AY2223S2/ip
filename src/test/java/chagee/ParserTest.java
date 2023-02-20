package chagee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import chagee.command.Chagee;
import chagee.parser.ChageeParser;


public class ParserTest {

    @Test
    public void testParseCommand() {
        assertEquals(ChageeParser.parseCommand(Chagee.BYE.text), Chagee.BYE);
        assertEquals(ChageeParser.parseCommand(Chagee.DEADLINE.text), Chagee.DEADLINE);
        assertEquals(ChageeParser.parseCommand(Chagee.DELETE.text), Chagee.DELETE);
        assertEquals(ChageeParser.parseCommand(Chagee.EVENT.text), Chagee.EVENT);
        assertEquals(ChageeParser.parseCommand(Chagee.FIND.text), Chagee.FIND);
        assertEquals(ChageeParser.parseCommand(Chagee.LIST.text), Chagee.LIST);
        assertEquals(ChageeParser.parseCommand(Chagee.MARK.text), Chagee.MARK);
        assertEquals(ChageeParser.parseCommand(Chagee.TODO.text), Chagee.TODO);
        assertEquals(ChageeParser.parseCommand(Chagee.UNMARK.text), Chagee.UNMARK);
    }

    @Test
    public void testParseCommandArg() {
        // TODO : Add more test cases
        String inpuString = "todo something";
        Chagee cmd = ChageeParser.parseCommand("todo something");
        String description = ChageeParser.parseCommandArgs(cmd, inpuString)[0];
        assertEquals(description, "something");
    }
}
