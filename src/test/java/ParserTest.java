
import duke.commands.AddToDoCommand;
import duke.exception.DukeException;
import duke.parser.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void parser_UnknownCommandException_thrown() throws DukeException {
        try {
            assertEquals(new AddToDoCommand(""),
                    new Parser().parse("This is a test to bring about a better work", 1));
            fail(); //Should not reach here
        } catch (DukeException e) {
            assertEquals(e.getLocalizedMessage(),
                    "\n" + "    ____________________________________________________________\n" +
                    "Sorry I don't think there's a command like that!" + "\n" +
                    "    ____________________________________________________________\n");
        }
    }

}
