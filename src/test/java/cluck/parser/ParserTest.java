package cluck.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import cluck.commands.Command;
import cluck.commands.ExitCommand;
import cluck.exceptions.CluckException;


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
