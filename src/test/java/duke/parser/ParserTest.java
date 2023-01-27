package duke.parser;

import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static duke.parser.Parser.parse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void checkToDoClass() {
        try {
            String fullCommand = "todo read book";
            String[] s = fullCommand.split(" ");
            assertEquals(new AddCommand(fullCommand, s).getClass(), Parser.parse(fullCommand).getClass());
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkDeleteClassTest() {
        try {
            String fullCommand = "delete 1";
            String[] s = fullCommand.split(" ");
            assertEquals(new DeleteCommand(Integer.parseInt(s[1])).getClass(), Parser.parse(fullCommand).getClass());
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkExitClassTest() {
        try {
            String fullCommand = "bye";
            String[] s = fullCommand.split(" ");
            assertEquals(new ExitCommand().getClass(), Parser.parse(fullCommand).getClass());
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }
}
