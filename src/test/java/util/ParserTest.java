package util;

import duke.Duke;
import duke.Command;
import duke.DukeException;
import duke.util.Parser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void testParseCommand() {
        Command c = null;
        try {
            c = Parser.parseCommand("find".toUpperCase());
        } catch (DukeException e) {
            System.out.println(e.toString());
        }
        assertEquals(Command.FIND, c);
    }


}
