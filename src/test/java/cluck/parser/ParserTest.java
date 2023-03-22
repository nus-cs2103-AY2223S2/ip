package cluck.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import cluck.commands.Command;
import cluck.commands.ExitCommand;
import cluck.exceptions.CluckException;
import cluck.exceptions.MissingArgumentException;
import cluck.messages.Messages;


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

    @Test
    public void missingDeadlineDescriptionThrowsError() {
        CluckException exception = assertThrows(MissingArgumentException.class, ()
                -> Parser.commandFactory("deadline /by 23 12 2023 2359"));
        assertEquals(Messages.MESSAGE_DESCRIPTION_MISSING, exception.getMessage());
    }


}
