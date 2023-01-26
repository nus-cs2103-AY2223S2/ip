package duke;

import command.Command;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertFalse;

public class ParserTest {

    @Test
    public void isExitTest() {
        try {
            Parser parser = new Parser(new DukeList(), new Storage(""));
            Command c = parser.parse("todo false");

            assertFalse(c.isExit());
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
