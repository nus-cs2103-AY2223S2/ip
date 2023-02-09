package duke.command;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void listTest() throws DukeException {
        Command listCommand = new Command("list");
        assertEquals(Parser.parse("list").toString(), listCommand.toString());
    }

    @Test
    public void deleteTest() throws DukeException {
        Command deleteCommand = new Command("delete", 1);
        assertEquals(Parser.parse("delete 1").toString(), deleteCommand.toString());
    }
}