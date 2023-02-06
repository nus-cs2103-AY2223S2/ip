package duke;

import duke.command.Command;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseCommand_todoCommand() {
        Command command = Parser.parse("todo buy bread /by 2023-02-01");

        assertEquals("todo buy bread /by 2023-02-01", command.getFullCommand());
    }

    @Test
    public void parseCommand_byeCommand() {
        Command command = Parser.parse("bye");

        assertEquals(true, command.isExit());
    }
}
