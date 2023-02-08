package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.command.DukeCommand;
import duke.parser.DukeParser;


public class ParserTest {

    @Test
    public void testParseCommand() {
        assertEquals(DukeParser.parseCommand(DukeCommand.BYE.text), DukeCommand.BYE);
        assertEquals(DukeParser.parseCommand(DukeCommand.DEADLINE.text), DukeCommand.DEADLINE);
        assertEquals(DukeParser.parseCommand(DukeCommand.DELETE.text), DukeCommand.DELETE);
        assertEquals(DukeParser.parseCommand(DukeCommand.EVENT.text), DukeCommand.EVENT);
        assertEquals(DukeParser.parseCommand(DukeCommand.FIND.text), DukeCommand.FIND);
        assertEquals(DukeParser.parseCommand(DukeCommand.LIST.text), DukeCommand.LIST);
        assertEquals(DukeParser.parseCommand(DukeCommand.MARK.text), DukeCommand.MARK);
        assertEquals(DukeParser.parseCommand(DukeCommand.TODO.text), DukeCommand.TODO);
        assertEquals(DukeParser.parseCommand(DukeCommand.UNMARK.text), DukeCommand.UNMARK);
    }

    @Test
    public void testParseCommandArg() {
        // TODO : Add more test cases
        String inpuString = "todo something";
        DukeCommand cmd = DukeParser.parseCommand("todo something");
        String description = DukeParser.parseCommandArgs(cmd, inpuString)[0];
        assertEquals(description, "something");
    }
}
