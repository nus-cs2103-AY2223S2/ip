package cluck.parser;

import cluck.commands.Command;
import cluck.commands.ExitCommand;
import cluck.exceptions.CluckException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;


public class ParserTest {

    @Test
    public void exitCommandTest() {
        try {
            Command exitCommand = new ExitCommand();
            Command testCommand = Parser.commandFactory("bye");
            assertEquals(exitCommand, testCommand);
        } catch (CluckException e) {
            fail();
        }
    }


}
