import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import commands.Command;
import commands.CommandType;
import exceptions.NookException;
import nook.Parser;

public class ParserTest {
    @Test
    public void addTodoCommand() throws NookException {
        Command command = Parser.parse("todo read book 1");
        assertEquals(command.getType(), CommandType.TODO);
    }

    @Test
    public void addDeadlineCommand() throws NookException {
        Command command = Parser.parse("deadline read book 2 /by 2023-02-02");
        assertEquals(command.getType(), CommandType.DEADLINE);
    }

    @Test
    public void addEventCommand() throws NookException {
        Command command = Parser.parse("event read book 3 /from 10 Feb 2pm /to 3pm");
        assertEquals(command.getType(), CommandType.EVENT);
    }
}
