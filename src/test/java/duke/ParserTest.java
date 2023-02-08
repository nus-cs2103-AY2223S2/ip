package duke;

import duke.command.Command;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void testParse_deadline_success() throws DukeException {
        try {
            Command c = Parser.parse("deadline CS2103T Week 3 iP/2023-01-27");
            System.out.println(c.toString());
            assertEquals("Command: Add task [D][ ] CS2103T Week 3 iP (Jan 27 2023)", c.toString());
        } catch (Exception e) {
            fail();
        }

    }
}
